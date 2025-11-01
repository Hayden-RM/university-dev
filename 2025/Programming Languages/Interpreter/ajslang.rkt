#lang eopl
(require eopl)
(provide (all-defined-out))

;; ========== LEXICAL SPECIFICATION ==========

;; Define the lexical tokens for the AJS language
(define ajs-lexical-spec
  '((whitespace (whitespace) skip)                    ; Skip whitespace
    (comment ("//" (arbno (not #\newline))) skip)     ; Skip single-line comments

    ;; Identifiers: start with letter/underscore/dollar, then letters/digits/underscore/dollar
    (identifier
     ((or letter #\_ #\$)
      (arbno (or letter digit #\_ #\$)))
     symbol)

    ;; Number formats:
    (number (digit (arbno digit)) number)                    ; Integer: 123
    (number (digit (arbno digit) #\. digit (arbno digit)) number) ; Decimal: 123.45
    (number (#\. digit (arbno digit)) number)                ; Decimal: .45
    (number (digit (arbno digit) #\.) number)                ; Decimal: 123.
    
    ;; String literals: enclosed in double quotes
    (string
     (#\" (arbno (not #\")) #\")
     string)))

;; ========== GRAMMAR SPECIFICATION (LL(1)) ==========

;; Define the abstract syntax grammar for the AJS language
(define ajs-grammar
  '(
    ;; Program: sequence of statements
    (program ((arbno statement)) a-program)

    ;; Statement types
    (statement ("const" identifier "=" expr ";") const-stmt)          ; Constant declaration
    (statement ("function" identifier "(" (separated-list identifier ",") ")" block) fun-stmt) ; Function declaration
    (statement ("return" expr ";") return-stmt)                       ; Return statement
    (statement (expr ";") expr-stmt)                                  ; Expression statement

    ;; Block: group of statements in braces
    (block ("{" (arbno statement) "}") a-block)

    ;; Expression hierarchy (ordered by precedence, lowest to highest)

    ;; Top-level expression wrapper
    (expr (logical-or) expr-wrap)

    ;; Logical OR with short-circuiting and ternary operator
    (logical-or (logical-and lor-tail) lor-node)
    (lor-tail ("||" logical-and lor-tail) lor-more)                   ; OR operator
    (lor-tail ("?" expr ":" logical-or) cond-exp)                     ; Ternary conditional operator
    (lor-tail () lor-done)                                            ; End of OR chain

    ;; Logical AND with short-circuiting
    (logical-and (equality land-tail) land-node)
    (land-tail ("&&" equality land-tail) land-more)                   ; AND operator
    (land-tail () land-done)                                          ; End of AND chain

    ;; Equality operators (strict equality/inequality)
    (equality (relational eq-tail) eq-node)
    (eq-tail ("===" relational eq-tail) eqe-more)                     ; Strict equality ===
    (eq-tail ("!==" relational eq-tail) eqn-more)                     ; Strict inequality !==
    (eq-tail () eq-done)                                              ; End of equality chain

    ;; Relational comparison operators
    (relational (additive-exp rel-tail) rel-node)
    (rel-tail (">"  additive-exp rel-tail) gt-more)                   ; Greater than
    (rel-tail ("<"  additive-exp rel-tail) lt-more)                   ; Less than
    (rel-tail (">=" additive-exp rel-tail) ge-more)                   ; Greater than or equal
    (rel-tail ("<=" additive-exp rel-tail) le-more)                   ; Less than or equal
    (rel-tail () rel-done)                                            ; End of relational chain

    ;; Additive operators (addition/subtraction)
    (additive-exp (multiplicative-exp add-op-tail) add-node)
    (add-op-tail ("+" multiplicative-exp add-op-tail) add-more)       ; Addition
    (add-op-tail ("-" multiplicative-exp add-op-tail) sub-more)       ; Subtraction
    (add-op-tail () add-done)                                         ; End of additive chain

    ;; Multiplicative operators (multiplication/division)
    (multiplicative-exp (unary-exp mul-op-tail) mul-node)
    (mul-op-tail ("*" unary-exp mul-op-tail) mul-more)                ; Multiplication
    (mul-op-tail ("/" unary-exp mul-op-tail) div-more)                ; Division
    (mul-op-tail () mul-done)                                         ; End of multiplicative chain

    ;; Unary operators
    (unary-exp (primary-exp) simple-unary)                            ; No unary operator
    (unary-exp ("-" unary-exp) neg-exp)                               ; Unary minus (negation)
    (unary-exp ("+" unary-exp) pos-exp)                               ; Unary plus (no-op)

    ;; Primary expressions with function call support
    (primary-exp (atom-exp call-tail) call-exp)                       ; Expression that can be called

    ;; Function call chain - handles f(x)(y) style calls
    (call-tail () no-call)                                            ; No function call
    (call-tail ("(" (separated-list expr ",") ")" call-tail) more-call) ; Function application

    ;; Atomic expressions (lowest level, cannot be followed by calls)
    (atom-exp (number) const-atom)                                    ; Numeric literal
    (atom-exp (identifier) var-atom)                                  ; Variable reference
    (atom-exp ("(" expr ")") paren-atom)                              ; Parenthesized expression
  ))

;; ========== PARSER GENERATION ==========

;; Generate datatypes from the lexical and grammar specifications
(sllgen:make-define-datatypes ajs-lexical-spec ajs-grammar)

;; Create scanner for tokenizing input strings
(define scan  (sllgen:make-string-scanner ajs-lexical-spec ajs-grammar))

;; Create parser for converting tokens to abstract syntax trees
(define parse (sllgen:make-string-parser  ajs-lexical-spec ajs-grammar))

;; ========== INITIALIZATION MESSAGE ==========
(display "parser built successfully")
