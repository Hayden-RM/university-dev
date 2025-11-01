#lang eopl
(require "ajslang.rkt"
         "ajsdata-structures.rkt")
(provide (all-defined-out))

;; ========== INITIALIZATION ==========

;; Initial environment for program execution
(define init-env (empty-env))

;; ========== HELPER STRUCTURES ==========

;; Represents the result of statement evaluation: (environment . output-value)
(define (step env out?) (cons env out?))
(define (step-env st)   (car st))    ; Extract environment from step
(define (step-out st)   (cdr st))    ; Extract output value from step

;; ========== PROGRAM AND STATEMENT EVALUATION ==========

;; Evaluate a complete program, returning list of outputs
(define (eval-program pgm)
  (cases program pgm
    (a-program (stmts)
      ;; Process statements sequentially, accumulating outputs
      (let loop ((ss stmts) (rho init-env) (outs '()))
        (if (null? ss)
            (reverse outs)  ; Return outputs in original order
            (let* ([result (eval-statement (car ss) rho)])
              (if (return-exn? result)
                  ;; Return statement found: extract value and terminate
                  (list (return-exn-value result))
                  ;; Continue with updated environment and accumulated outputs
                  (let ([rho2 (step-env result)]
                        [out? (step-out result)])
                    (loop (cdr ss) rho2 (if out? (cons out? outs) outs))))))))))

;; Evaluate a single statement, return (env . output) pair
(define (eval-statement s rho)
  (cases statement s
    (const-stmt (id e)
      ;; Define constant: evaluate expression and extend environment
      (let ([v (eval-expr e rho)])
        (step (env-extend1 rho id v) #f)))
    (fun-stmt (id params body)
      ;; Define function: create recursive closure and extend environment
      (let ([closure (V-rec-clos id params body rho)])
        (step (env-extend1 rho id closure) #f)))
    (return-stmt (e)
      ;; Return statement: create return exception to propagate value
      (make-return-exn (eval-expr e rho)))
    (expr-stmt (e)
      ;; Expression statement: evaluate and return as output
      (step rho (eval-expr e rho)))))

;; ========== BLOCK EVALUATION WITH RETURN HANDLING ==========

;; Evaluate a block of statements, handling return statements
(define (eval-block b rho)
  (cases block b
    (a-block (stmts)
      (let loop ((ss stmts) (current-env rho) (last-out (V-null)))
        (if (null? ss)
            last-out  ; Return last evaluated expression value
            (let ([result (eval-statement (car ss) current-env)])
              (cond
                [(return-exn? result) 
                 (return-exn-value result)]  ; Propagate return value upward
                [else
                 ;; Continue with updated environment and track last output
                 (let ([new-env (step-env result)]
                       [out (step-out result)])
                   (loop (cdr ss) new-env (if out out last-out)))])))))))

;; ========== EXPRESSION EVALUATION HIERARCHY ==========

;; Top-level expression evaluation
(define (eval-expr e rho)
  (cases expr e
    (expr-wrap (lorE) (eval-logical-or lorE rho))))

;; Logical OR with short-circuiting and ternary operator support
(define (eval-logical-or lorE rho)
  (cases logical-or lorE
    (lor-node (landE tail)
      (eval-lor-tail (eval-logical-and landE rho) tail rho))))

;; Handle OR operations and ternary conditional expressions
(define (eval-lor-tail acc tail rho)
  (cases lor-tail tail
    (lor-done () acc)  ; No more OR operations
    (lor-more (landE rest)
      ;; Short-circuit: if true, return acc; else evaluate right side
      (if (truthy? acc)
          (eval-lor-tail acc rest rho)
          (eval-lor-tail (eval-logical-and landE rho) rest rho)))
    (cond-exp (thenE elseLOR)
      ;; Ternary operator: condition ? then-expr : else-expr
      (if (truthy? acc)
          (eval-expr thenE rho)
          (eval-logical-or elseLOR rho)))))

;; Logical AND with short-circuiting
(define (eval-logical-and landE rho)
  (cases logical-and landE
    (land-node (eqE tail)
      (eval-land-tail (eval-equality eqE rho) tail rho))))

;; Handle AND operations with short-circuiting
(define (eval-land-tail acc tail rho)
  (cases land-tail tail
    (land-done () acc)  ; No more AND operations
    (land-more (eqE rest)
      ;; Short-circuit: if false, return acc; else evaluate right side
      (if (truthy? acc)
          (eval-land-tail (eval-equality eqE rho) rest rho)
          (eval-land-tail acc rest rho)))))

;; Equality operations (== and !=)
(define (eval-equality eqE rho)
  (cases equality eqE
    (eq-node (relE tail)
      (eval-eq-tail (eval-relational relE rho) tail rho))))

;; Handle equality chain operations
(define (eval-eq-tail acc tail rho)
  (cases eq-tail tail
    (eq-done () acc)  ; End of equality chain
    (eqe-more (relE rest)
      ;; === operator: strict equality comparison
      (eval-eq-tail (ajs=== acc (eval-relational relE rho)) rest rho))
    (eqn-more (relE rest)
      ;; !== operator: strict inequality comparison
      (eval-eq-tail (ajs!== acc (eval-relational relE rho)) rest rho))))

;; Relational comparisons (>, <, >=, <=)
(define (eval-relational relE rho)
  (cases relational relE
    (rel-node (addE tail)
      (eval-rel-tail (eval-additive addE rho) tail rho))))

;; Convert numeric comparison to boolean value
(define (cmp->bool op a b)
  (V-bool (op (expect-number 'compare a)
              (expect-number 'compare b))))

;; Handle relational comparison chain
(define (eval-rel-tail acc tail rho)
  (cases rel-tail tail
    (rel-done () acc)  ; End of relational chain
    (gt-more (addE rest)
      (eval-rel-tail (cmp->bool >  acc (eval-additive addE rho)) rest rho))
    (lt-more (addE rest)
      (eval-rel-tail (cmp->bool <  acc (eval-additive addE rho)) rest rho))
    (ge-more (addE rest)
      (eval-rel-tail (cmp->bool >= acc (eval-additive addE rho)) rest rho))
    (le-more (addE rest)
      (eval-rel-tail (cmp->bool <= acc (eval-additive addE rho)) rest rho))))

;; ========== ARITHMETIC OPERATIONS ==========

;; Additive expressions (+ and -)
(define (eval-additive addE rho)
  (cases additive-exp addE
    (add-node (mulE tail)
      (let ([acc (eval-multiplicative mulE rho)])
        (eval-add-op-tail acc tail rho)))))

;; Handle addition and subtraction operations
(define (eval-add-op-tail acc tail rho)
  (cases add-op-tail tail
    (add-done () acc)  ; End of additive chain
    (add-more (mulE rest)
      (eval-add-op-tail
       (arith+ acc (eval-multiplicative mulE rho))
       rest rho))
    (sub-more (mulE rest)
      (eval-add-op-tail
       (arith- acc (eval-multiplicative mulE rho))
       rest rho))))

;; Multiplicative expressions (* and /)
(define (eval-multiplicative mulE rho)
  (cases multiplicative-exp mulE
    (mul-node (ue tail)
      (let ([acc (eval-unary-exp ue rho)])
        (eval-mul-op-tail acc tail rho)))))

;; Handle multiplication and division operations
(define (eval-mul-op-tail acc tail rho)
  (cases mul-op-tail tail
    (mul-done () acc)  ; End of multiplicative chain
    (mul-more (ue rest)
      (eval-mul-op-tail
       (arith* acc (eval-unary-exp ue rho))
       rest rho))
    (div-more (ue rest)
      (eval-mul-op-tail
       (arith/ acc (eval-unary-exp ue rho))
       rest rho))))

;; ========== UNARY EXPRESSIONS ==========

;; Handle unary operators (+ and -)
(define (eval-unary-exp ue rho)
  (cases unary-exp ue
    (simple-unary (primary) (eval-primary-exp primary rho))
    (neg-exp (ue1) 
      ;; Unary minus: negate the numeric value
      (let ([v (eval-unary-exp ue1 rho)])
        (V-num (- (expect-number 'neg v)))))
    (pos-exp (ue1) 
      ;; Unary plus: no effect, just evaluate
      (eval-unary-exp ue1 rho))))

;; ========== FUNCTION CALLS AND PRIMARY EXPRESSIONS ==========

;; Primary expressions that can include function calls
(define (eval-primary-exp pe rho)
  (cases primary-exp pe
    (call-exp (atom tail)
      (eval-call-tail (eval-atom-exp atom rho) tail rho))))

;; Handle function call chains with proper closure evaluation
(define (eval-call-tail func tail rho)
  (cases call-tail tail
    (no-call () func)  ; No function call, return the value
    (more-call (args next-tail)
      (let* ([closure-parts (expect-closure 'call func)]
             [params (car closure-parts)]
             [body (cadr closure-parts)]
             [closure-env (caddr closure-parts)]
             [arg-vals (map (lambda (arg) (eval-expr arg rho)) args)])
        (if (= (length params) (length arg-vals))
            ;; Handle recursive vs regular closures differently
            (cases ajsval func
              (rec-closure-val (func-name ps body closure-env)
                ;; Recursive function: add function to new environment for self-reference
                (let ([new-env (env-extend* closure-env params arg-vals)])
                  (let ([rec-env (env-extend1 new-env func-name func)])
                    (let ([result (eval-block body rec-env)])
                      (eval-call-tail result next-tail rho)))))
              (else
                ;; Regular closure: extend environment with arguments only
                (let ([result (eval-block body (env-extend* closure-env params arg-vals))])
                  (eval-call-tail result next-tail rho))))
            (eopl:error 'eval-call-tail "Arity mismatch: expected ~a args, got ~a" 
                       (length params) (length arg-vals)))))))

;; ========== ATOM EXPRESSIONS ==========

;; Basic expressions without function calls
(define (eval-atom-exp ae rho)
  (cases atom-exp ae
    (const-atom (n) (V-num n))          ; Numeric literal
    (var-atom (id) (env-lookup rho id)) ; Variable lookup
    (paren-atom (e) (eval-expr e rho)))) ; Parenthesized expression

;; ========== ARITHMETIC OPERATORS ON AJSVALS ==========

;; Arithmetic operations that extract numbers from ajsvals
(define (arith+ a b) (V-num (+ (expect-number '+ a) (expect-number '+ b))))
(define (arith- a b) (V-num (- (expect-number '- a) (expect-number '- b))))
(define (arith* a b) (V-num (* (expect-number '* a) (expect-number '* b))))
(define (arith/ a b) (V-num (/ (expect-number '/ a) (expect-number '/ b))))
