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
            (let* ([st   (eval-statement (car ss) rho)]
                   [rho2 (step-env st)]
                   [out? (step-out st)])
              (loop (cdr ss) rho2 (if out? (cons out? outs) outs))))))))

(define (eval-statement s rho)
  (cases statement s
    (const-stmt (id e)
      (let ([v (eval-expr e rho)])
        (step (env-extend1 rho id v) #f)))
    (expr-stmt (e)
      (step rho (eval-expr e rho)))))

;; ================= Expressions (your node names) =================

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
      (if (truthy? acc)                      ; short-circuit OR
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
      (if (truthy? acc)                      ; short-circuit AND
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

;; multiplicative-exp -> (mul-node factor mul-op-tail)
(define (eval-multiplicative mulE rho)
  (cases multiplicative-exp mulE
    (mul-node (f tail)
      (let ([acc (eval-factor f rho)])
        (eval-mul-op-tail acc tail rho)))))

(define (eval-mul-op-tail acc tail rho)
  (cases mul-op-tail tail
    (mul-done () acc)
    (mul-more (f rest)
      (eval-mul-op-tail
       (arith* acc (eval-factor f rho))
       rest rho))
    (div-more (f rest)
      (eval-mul-op-tail
       (arith/ acc (eval-factor f rho))
       rest rho))))

;; Factors
(define (eval-factor f rho)
  (cases factor f
    (const-exp (n)      (V-num n))
    (var-exp   (id)     (env-lookup rho id))
    (paren-exp (e)      (eval-expr e rho))
    (neg-exp   (f1)     (let ([v (eval-factor f1 rho)])
                          (V-num (- (expect-number 'neg v)))))
    (pos-exp   (f1)     (eval-factor f1 rho))))

;; Numeric ops on ajsvals
(define (arith+ a b) (V-num (+ (expect-number '+ a) (expect-number '+ b))))
(define (arith- a b) (V-num (- (expect-number '- a) (expect-number '- b))))
(define (arith* a b) (V-num (* (expect-number '* a) (expect-number '* b))))
(define (arith/ a b) (V-num (/ (expect-number '/ a) (expect-number '/ b))))
