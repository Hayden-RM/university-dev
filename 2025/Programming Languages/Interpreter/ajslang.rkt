#lang eopl
(require eopl)
(provide (all-defined-out))

;; ========= 1) LEXICAL SPEC =========
(define ajs-lexical-spec
  '((whitespace (whitespace) skip)
    (comment ("//" (arbno (not #\newline))) skip)

    ;; identifiers -> symbol
    (identifier
     ((or letter #\_ #\$)
      (arbno (or letter digit #\_ #\$)))
     symbol)

    ;; numbers (integer/ decimals)
    (number (digit (arbno digit)) number)
    (number (digit (arbno digit) #\. digit (arbno digit)) number)
    (number (#\. digit (arbno digit)) number)
    (number (digit (arbno digit) #\.) number)
    ;; simple strings (kept for later use)
    (string
     (#\" (arbno (not #\")) #\")
     string)))

;; ========= 2) GRAMMAR (LL(1)) =========
(define ajs-grammar
  '(
    (program ((arbno statement)) a-program)

    (statement ("const" identifier "=" expr ";") const-stmt)
    (statement ("function" identifier "(" (separated-list identifier ",") ")" block) fun-stmt)
    (statement ("return" expr ";") return-stmt)
    (statement (expr ";") expr-stmt)

    (block ("{" (arbno statement) "}") a-block)

    (expr (logical-or) expr-wrap)

    ;; Conditional expression - handled in logical-or
    (logical-or (logical-and lor-tail) lor-node)
    (lor-tail ("||" logical-and lor-tail) lor-more)
    (lor-tail ("?" expr ":" logical-or) cond-exp)  ; Conditional as part of logical-or
    (lor-tail () lor-done)

    ;; Logical AND
    (logical-and (equality land-tail) land-node)
    (land-tail ("&&" equality land-tail) land-more)
    (land-tail () land-done)

    ;; Equality
    (equality (relational eq-tail) eq-node)
    (eq-tail ("===" relational eq-tail) eqe-more)
    (eq-tail ("!==" relational eq-tail) eqn-more)
    (eq-tail () eq-done)

    ;; Relational
    (relational (additive-exp rel-tail) rel-node)
    (rel-tail (">"  additive-exp rel-tail) gt-more)
    (rel-tail ("<"  additive-exp rel-tail) lt-more)
    (rel-tail (">=" additive-exp rel-tail) ge-more)
    (rel-tail ("<=" additive-exp rel-tail) le-more)
    (rel-tail () rel-done)

    ;; Additive
    (additive-exp (multiplicative-exp add-op-tail) add-node)
    (add-op-tail ("+" multiplicative-exp add-op-tail) add-more)
    (add-op-tail ("-" multiplicative-exp add-op-tail) sub-more)
    (add-op-tail () add-done)

    ;; Multiplicative
    (multiplicative-exp (unary-exp mul-op-tail) mul-node)
    (mul-op-tail ("*" unary-exp mul-op-tail) mul-more)
    (mul-op-tail ("/" unary-exp mul-op-tail) div-more)
    (mul-op-tail () mul-done)

    ;; Unary expressions (+, -)
    (unary-exp (primary-exp) simple-unary)
    (unary-exp ("-" unary-exp) neg-exp)
    (unary-exp ("+" unary-exp) pos-exp)

    ;; Primary expressions - the key is to handle calls at this level
    (primary-exp (atom-exp call-tail) call-exp)

    ;; Call tail for function application
    (call-tail () no-call)
    (call-tail ("(" (separated-list expr ",") ")" call-tail) more-call)

    ;; Atom expressions (cannot be followed by function calls)
    (atom-exp (number) const-atom)
    (atom-exp (identifier) var-atom)
    (atom-exp ("(" expr ")") paren-atom)
  ))

(sllgen:make-define-datatypes ajs-lexical-spec ajs-grammar)

(define scan  (sllgen:make-string-scanner ajs-lexical-spec ajs-grammar))
(define parse (sllgen:make-string-parser  ajs-lexical-spec ajs-grammar))

(display "parser built successfully")
