#lang eopl
;; ========= AJS Interpreter (phase 1: integers, const, + - * /, unary ±) =========

(require "ajslang.rkt"              ; AST constructors from sllgen
         "ajsdata-structures.rkt")  ; env + value types/helpers
(provide (all-defined-out))

;; -----------------------------------------------------------------------------
;; Top-level driver
;; -----------------------------------------------------------------------------

(define init-env (empty-env))

;; A "step" is just a pair: (cons new-env maybe-output-or-#f)
(define (step env out?) (cons env out?))
(define (step-env st)   (car st))
(define (step-out st)   (cdr st))

;; eval-program : program -> (listof ajsval)
;; Evaluate statements left-to-right; collect values from expr-statements.
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

;; -----------------------------------------------------------------------------
;; Statements
;; -----------------------------------------------------------------------------

;; eval-statement : statement env -> step
(define (eval-statement s rho)
  (cases statement s
    ;; const x = expr ;
    (const-stmt (id e)
      (let ([v (eval-expr e rho)])
        (step (env-extend1 rho id v) #f)))
    ;; expr ;
    (expr-stmt (e)
      (step rho (eval-expr e rho)))))

;; -----------------------------------------------------------------------------
;; Expressions (match the LL(1) grammar structure)
;; -----------------------------------------------------------------------------

;; expr = (expr-wrap additive-exp)
(define (eval-expr e rho)
  (cases expr e
    (expr-wrap (addE) (eval-additive addE rho))))

;; additive-exp = (add-node multiplicative-exp add-tail)
(define (eval-additive addE rho)
  (cases additive-exp addE
    (add-node (mulE tail)
      (let ([acc (eval-multiplicative mulE rho)])
        (eval-add-tail acc tail rho)))))

;; add-tail: left-assoc fold for +/-
(define (eval-add-tail acc tail rho)
  (cases add-tail tail
    (add-done () acc)
    (add-more (mulE rest)
      (eval-add-tail
       (arith+ acc (eval-multiplicative mulE rho))
       rest rho))
    (sub-more (mulE rest)
      (eval-add-tail
       (arith- acc (eval-multiplicative mulE rho))
       rest rho))))

;; multiplicative-exp = (mul-node factor mul-tail)
(define (eval-multiplicative mulE rho)
  (cases multiplicative-exp mulE
    (mul-node (fact tail)
      (let ([acc (eval-factor fact rho)])
        (eval-mul-tail acc tail rho)))))

;; mul-tail: left-assoc fold for */ 
(define (eval-mul-tail acc tail rho)
  (cases mul-tail tail
    (mul-done () acc)
    (mul-more (f rest)
      (eval-mul-tail
       (arith* acc (eval-factor f rho))
       rest rho))
    (div-more (f rest)
      (eval-mul-tail
       (arith/ acc (eval-factor f rho))
       rest rho))))

;; -----------------------------------------------------------------------------
;; Factors
;; -----------------------------------------------------------------------------

(define (eval-factor f rho)
  (cases factor f
    (const-exp (n)      (V-num n))                 ; integer literal
    (var-exp   (id)     (env-lookup rho id))       ; variable
    (paren-exp (e)      (eval-expr e rho))         ; (expr)
    (neg-exp   (f1)     (let ([v (eval-factor f1 rho)])
                          (V-num (- (expect-number 'neg v)))))
    (pos-exp   (f1)     (eval-factor f1 rho))))

;; -----------------------------------------------------------------------------
;; Numeric ops on ajsvals
;; -----------------------------------------------------------------------------

(define (arith+ a b)
  (V-num (+ (expect-number '+ a) (expect-number '+ b))))
(define (arith- a b)
  (V-num (- (expect-number '- a) (expect-number '- b))))
(define (arith* a b)
  (V-num (* (expect-number '* a) (expect-number '* b))))
(define (arith/ a b)
  (V-num (/ (expect-number '/ a) (expect-number '/ b))))


