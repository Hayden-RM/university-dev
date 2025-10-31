#lang eopl
(require eopl)
(provide (all-defined-out))

;; ========= 1) LEXICAL SPEC (integers only) =========
(define ajs-lexical-spec
  '((whitespace (whitespace) skip)
    (comment ("//" (arbno (not #\newline))) skip)

    ;; identifiers -> symbol
    (identifier
     ((or letter #\_ #\$)
      (arbno (or letter digit #\_ #\$)))
     symbol)

    ;; numbers: UNSIGNED WHOLE NUMBERS ONLY
    ;; one-or-more digits = digit (arbno digit)
    (number
     (digit (arbno digit))
     number)

    ;; simple strings (kept for later use)
    (string
     (#\" (arbno (not #\")) #\")
     string)))

;; ========= 2) GRAMMAR (LL(1), unary ± here) =========
(define ajs-grammar
  '(
    (program ((arbno statement)) a-program)

    (statement ("const" identifier "=" expr ";") const-stmt)
    (statement (expr ";")                        expr-stmt)

    (expr (additive-exp) expr-wrap)

    ;; additive-exp -> multiplicative-exp add-tail
    (additive-exp (multiplicative-exp add-tail) add-node)
    (add-tail ("+" multiplicative-exp add-tail) add-more)
    (add-tail ("-" multiplicative-exp add-tail) sub-more) ; <-- fixed here
    (add-tail ()                                 add-done)

    ;; multiplicative-exp -> factor mul-tail
    (multiplicative-exp (factor mul-tail) mul-node)
    (mul-tail ("*" factor mul-tail) mul-more)
    (mul-tail ("/" factor mul-tail) div-more)
    (mul-tail ()                      mul-done)

    ;; factor (with unary +/-)
    (factor (number)            const-exp)
    (factor (identifier)        var-exp)
    (factor ("(" expr ")")      paren-exp)
    (factor ("-" factor)        neg-exp)
    (factor ("+" factor)        pos-exp)
  ))

(sllgen:make-define-datatypes ajs-lexical-spec ajs-grammar)

(define scan  (sllgen:make-string-scanner ajs-lexical-spec ajs-grammar))
(define parse (sllgen:make-string-parser  ajs-lexical-spec ajs-grammar))

(display "Parser Built Successfully")
