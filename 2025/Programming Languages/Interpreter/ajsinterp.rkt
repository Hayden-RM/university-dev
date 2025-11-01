#lang eopl
(require "ajslang.rkt"
         "ajsdata-structures.rkt")
(provide (all-defined-out))

;; initial environment
(define init-env (empty-env))

;; small helper "step" pair for statement results
(define (step env out?) (cons env out?))
(define (step-env st)   (car st))
(define (step-out st)   (cdr st))

;; ================= Program / Statements =================

(define (eval-program pgm)
  (cases program pgm
    (a-program (stmts)
      (let loop ((ss stmts) (rho init-env) (outs '()))
        (if (null? ss)
            (reverse outs)
            (let* ([result (eval-statement (car ss) rho)])
              (if (return-exn? result)
                  ;; If we got a return exception, extract the value
                  (list (return-exn-value result))
                  (let ([rho2 (step-env result)]
                        [out? (step-out result)])
                    (loop (cdr ss) rho2 (if out? (cons out? outs) outs))))))))))

(define (eval-statement s rho)
  (cases statement s
    (const-stmt (id e)
      (let ([v (eval-expr e rho)])
        (step (env-extend1 rho id v) #f)))
    (fun-stmt (id params body)
      ;; Create recursive closure
      (let ([closure (V-rec-clos id params body rho)])
        (step (env-extend1 rho id closure) #f)))
    (return-stmt (e)
      (make-return-exn (eval-expr e rho)))
    (expr-stmt (e)
      (step rho (eval-expr e rho)))))

;; ================= Blocks with Return Handling =================

(define (eval-block b rho)
  (cases block b
    (a-block (stmts)
      (let loop ((ss stmts) (current-env rho) (last-out (V-null)))
        (if (null? ss)
            last-out
            (let ([result (eval-statement (car ss) current-env)])
              (cond
                [(return-exn? result) 
                 (return-exn-value result)]  ; propagate return value
                [else
                 (let ([new-env (step-env result)]
                       [out (step-out result)])
                   (loop (cdr ss) new-env (if out out last-out)))])))))))

;; ================= Expressions =================

;; expr -> (expr-wrap logical-or)
(define (eval-expr e rho)
  (cases expr e
    (expr-wrap (lorE) (eval-logical-or lorE rho))))

;; logical-or -> (lor-node logical-and lor-tail)
(define (eval-logical-or lorE rho)
  (cases logical-or lorE
    (lor-node (landE tail)
      (eval-lor-tail (eval-logical-and landE rho) tail rho))))

;; lor-tail:
;;   - lor-more: "||" logical-and lor-tail
;;   - cond-exp: "?" expr ":" logical-or
;;   - lor-done: ε
(define (eval-lor-tail acc tail rho)
  (cases lor-tail tail
    (lor-done () acc)
    (lor-more (landE rest)
      (if (truthy? acc)
          (eval-lor-tail acc rest rho)
          (eval-lor-tail (eval-logical-and landE rho) rest rho)))
    (cond-exp (thenE elseLOR)
      (if (truthy? acc)
          (eval-expr thenE rho)
          (eval-logical-or elseLOR rho)))))

;; logical-and -> (land-node equality land-tail)
(define (eval-logical-and landE rho)
  (cases logical-and landE
    (land-node (eqE tail)
      (eval-land-tail (eval-equality eqE rho) tail rho))))

;; land-tail:
;;   - land-more: "&&" equality land-tail
;;   - land-done: ε
(define (eval-land-tail acc tail rho)
  (cases land-tail tail
    (land-done () acc)
    (land-more (eqE rest)
      (if (truthy? acc)
          (eval-land-tail (eval-equality eqE rho) rest rho)
          (eval-land-tail acc rest rho)))))

;; equality -> (eq-node relational eq-tail)
(define (eval-equality eqE rho)
  (cases equality eqE
    (eq-node (relE tail)
      (eval-eq-tail (eval-relational relE rho) tail rho))))

;; eq-tail: (===(relational) | !==(relational))*
(define (eval-eq-tail acc tail rho)
  (cases eq-tail tail
    (eq-done () acc)
    (eqe-more (relE rest)
      (eval-eq-tail (ajs=== acc (eval-relational relE rho)) rest rho))
    (eqn-more (relE rest)
      (eval-eq-tail (ajs!== acc (eval-relational relE rho)) rest rho))))

;; relational -> (rel-node additive-exp rel-tail)
(define (eval-relational relE rho)
  (cases relational relE
    (rel-node (addE tail)
      (eval-rel-tail (eval-additive addE rho) tail rho))))

;; rel-tail: chain of > < >= <=
(define (cmp->bool op a b)
  (V-bool (op (expect-number 'compare a)
              (expect-number 'compare b))))

(define (eval-rel-tail acc tail rho)
  (cases rel-tail tail
    (rel-done () acc)
    (gt-more (addE rest)
      (eval-rel-tail (cmp->bool >  acc (eval-additive addE rho)) rest rho))
    (lt-more (addE rest)
      (eval-rel-tail (cmp->bool <  acc (eval-additive addE rho)) rest rho))
    (ge-more (addE rest)
      (eval-rel-tail (cmp->bool >= acc (eval-additive addE rho)) rest rho))
    (le-more (addE rest)
      (eval-rel-tail (cmp->bool <= acc (eval-additive addE rho)) rest rho))))

;; additive-exp -> (add-node multiplicative-exp add-op-tail)
(define (eval-additive addE rho)
  (cases additive-exp addE
    (add-node (mulE tail)
      (let ([acc (eval-multiplicative mulE rho)])
        (eval-add-op-tail acc tail rho)))))

(define (eval-add-op-tail acc tail rho)
  (cases add-op-tail tail
    (add-done () acc)
    (add-more (mulE rest)
      (eval-add-op-tail
       (arith+ acc (eval-multiplicative mulE rho))
       rest rho))
    (sub-more (mulE rest)
      (eval-add-op-tail
       (arith- acc (eval-multiplicative mulE rho))
       rest rho))))

;; multiplicative-exp -> (mul-node unary-exp mul-op-tail)
(define (eval-multiplicative mulE rho)
  (cases multiplicative-exp mulE
    (mul-node (ue tail)
      (let ([acc (eval-unary-exp ue rho)])
        (eval-mul-op-tail acc tail rho)))))

(define (eval-mul-op-tail acc tail rho)
  (cases mul-op-tail tail
    (mul-done () acc)
    (mul-more (ue rest)
      (eval-mul-op-tail
       (arith* acc (eval-unary-exp ue rho))
       rest rho))
    (div-more (ue rest)
      (eval-mul-op-tail
       (arith/ acc (eval-unary-exp ue rho))
       rest rho))))

;; Unary expressions
(define (eval-unary-exp ue rho)
  (cases unary-exp ue
    (simple-unary (primary) (eval-primary-exp primary rho))
    (neg-exp (ue1) 
      (let ([v (eval-unary-exp ue1 rho)])
        (V-num (- (expect-number 'neg v)))))
    (pos-exp (ue1) 
      (eval-unary-exp ue1 rho))))

;; Primary expressions (handle function calls)
(define (eval-primary-exp pe rho)
  (cases primary-exp pe
    (call-exp (atom tail)
      (eval-call-tail (eval-atom-exp atom rho) tail rho))))

;; Call tail evaluation (handles function application)
(define (eval-call-tail func tail rho)
  (cases call-tail tail
    (no-call () func)
    (more-call (args next-tail)
      (let* ([closure-parts (expect-closure 'call func)]
             [params (car closure-parts)]
             [body (cadr closure-parts)]
             [closure-env (caddr closure-parts)]
             [arg-vals (map (lambda (arg) (eval-expr arg rho)) args)])
        (if (= (length params) (length arg-vals))
            ;; Handle recursive closures
            (cases ajsval func
              (rec-closure-val (func-name ps body closure-env)
                ;; For recursive functions: add the function itself to the new environment
                (let ([new-env (env-extend* closure-env params arg-vals)])
                  (let ([rec-env (env-extend1 new-env func-name func)])
                    (let ([result (eval-block body rec-env)])
                      (eval-call-tail result next-tail rho)))))
              (else
                ;; Regular closure
                (let ([result (eval-block body (env-extend* closure-env params arg-vals))])
                  (eval-call-tail result next-tail rho))))
            (eopl:error 'eval-call-tail "Arity mismatch: expected ~a args, got ~a" 
                       (length params) (length arg-vals)))))))

;; Atom expressions (lowest level, no function calls)
(define (eval-atom-exp ae rho)
  (cases atom-exp ae
    (const-atom (n) (V-num n))
    (var-atom (id) (env-lookup rho id))
    (paren-atom (e) (eval-expr e rho))))

;; Numeric ops on ajsvals
(define (arith+ a b) (V-num (+ (expect-number '+ a) (expect-number '+ b))))
(define (arith- a b) (V-num (- (expect-number '- a) (expect-number '- b))))
(define (arith* a b) (V-num (* (expect-number '* a) (expect-number '* b))))
(define (arith/ a b) (V-num (/ (expect-number '/ a) (expect-number '/ b))))
