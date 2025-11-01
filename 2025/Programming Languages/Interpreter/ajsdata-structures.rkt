#lang eopl
(require eopl)
(provide (all-defined-out))

;; ========== HELPER FUNCTIONS ==========

;; Custom andmap implementation for lists
(define (andmap f lst)
  (cond
    [(null? lst) #t]
    [else (and (f (car lst)) (andmap f (cdr lst)))]))

;; Check if list contains only symbols
(define (symbol-list? xs) (and (list? xs) (andmap symbol? xs)))

;; ========== ENVIRONMENT DATA STRUCTURE ==========

;; Environment datatype for variable bindings
(define-datatype env env?
  (empty-env)                              ; Base empty environment
  (extend-env                             ; Extended environment
    (names symbol-list?)                  ; List of variable names
    (vals  (lambda (v) (and (list? v) (andmap ajsval? v)))) ; List of corresponding values
    (parent env?)))                       ; Parent environment for scoping

;; Look up a variable in the environment
(define (env-lookup rho x)
  (cases env rho
    (empty-env ()
      (eopl:error 'env-lookup "unbound identifier ~a" x))
    (extend-env (ns vs parent)
      (let loop ((ns ns) (vs vs))
        (cond
          [(null? ns) (env-lookup parent x)] ; Not found in current scope, check parent
          [(eq? (car ns) x) (car vs)]        ; Found variable, return its value
          [else (loop (cdr ns) (cdr vs))]))))) ; Continue searching current scope

;; Extend environment with a single binding
(define (env-extend1 rho x v)
  (extend-env (list x) (list v) rho))

;; Extend environment with multiple bindings
(define (env-extend* rho names vals)
  (when (not (= (length names) (length vals)))
    (eopl:error 'env-extend* "arity mismatch: ~a names vs ~a values"
                (length names) (length vals)))
  (extend-env names vals rho))

;; ========== AJS VALUE TYPES ==========

;; Runtime value representations for AJS language
(define-datatype ajsval ajsval?
  (num-val   (n number?))                 ; Numeric values
  (bool-val  (b boolean?))                ; Boolean values
  (str-val   (s string?))                 ; String values
  (null-val)                              ; Null value
  (closure-val                            ; Function closures
    (params symbol-list?)                ; Parameter names
    (body   (lambda (_) #t))             ; Function body (any value accepted)
    (env0   env?))                       ; Captured environment
  (rec-closure-val                       ; Recursive function closures
    (func-name symbol?)                  ; Function name for self-reference
    (params symbol-list?)               ; Parameter names
    (body   (lambda (_) #t))            ; Function body
    (env0   env?)))                     ; Captured environment

;; ========== VALUE TYPE CHECKERS ==========

;; Check if value is a number
(define (num-val? v)
  (cases ajsval v
    (num-val (_) #t)
    (else #f)))

;; Check if value is a boolean
(define (bool-val? v)
  (cases ajsval v
    (bool-val (_) #t)
    (else #f)))

;; Check if value is a string
(define (str-val? v)
  (cases ajsval v
    (str-val (_) #t)
    (else #f)))

;; Check if value is null
(define (null-val? v)
  (cases ajsval v
    (null-val () #t)
    (else #f)))

;; Check if value is a closure (non-recursive function)
(define (closure-val? v)
  (cases ajsval v
    (closure-val (p b e) #t)
    (else #f)))

;; Check if value is a recursive closure
(define (rec-closure-val? v)
  (cases ajsval v
    (rec-closure-val (n p b e) #t)
    (else #f)))

;; ========== VALUE EXTRACTORS ==========

;; Extract number from ajsval, error if not a number
(define (expect-number who v)
  (cases ajsval v
    (num-val (n) n)
    (else (eopl:error who "expected number, got ~s" v))))

;; Extract boolean from ajsval, error if not a boolean
(define (expect-boolean who v)
  (cases ajsval v
    (bool-val (b) b)
    (else (eopl:error who "expected boolean, got ~s" v))))

;; Extract closure components from ajsval, error if not a function
(define (expect-closure who v)
  (cases ajsval v
    (closure-val (ps body rho) (list ps body rho))
    (rec-closure-val (name ps body rho) (list ps body rho))
    (else (eopl:error who "expected function/closure, got ~s" v))))

;; ========== TRUTHINESS AND EQUALITY ==========

;; Determine JavaScript-style truthiness of a value
(define (truthy? v)
  (cases ajsval v
    (bool-val  (b) b)                    ; Booleans: use their value
    (null-val  () #f)                    ; Null: false
    (num-val   (n) (not (equal? n 0)))   ; Numbers: false only for 0
    (str-val   (s) (not (string=? s ""))) ; Strings: false only for empty string
    (closure-val (p b e) #t)             ; Functions: always true
    (rec-closure-val (n p b e) #t)))     ; Recursive functions: always true

;; Strict equality comparison (=== operator)
(define (ajs=== a b)
  (bool-val
   (cond
     [(and (num-val? a) (num-val? b))    ; Number comparison
      (= (expect-number '=== a) (expect-number '=== b))]
     [(and (bool-val? a) (bool-val? b))  ; Boolean comparison
      (eq? (expect-boolean '=== a) (expect-boolean '=== b))]
     [(and (str-val? a) (str-val? b))    ; String comparison
      (string=? (cases ajsval a (str-val (s) s) (else ""))
                (cases ajsval b (str-val (s) s) (else "")))]
     [(and (null-val? a) (null-val? b)) #t] ; Null comparison: both null
     [(and (closure-val? a) (closure-val? b)) (eq? a b)] ; Function reference equality
     [(and (rec-closure-val? a) (rec-closure-val? b)) (eq? a b)] ; Recursive function reference equality
     [else #f]))) ; Different types: not equal

;; Strict inequality comparison (!== operator)
(define (ajs!== a b)
  (cases ajsval (ajs=== a b)
    (bool-val (x) (bool-val (not x))) ; Negate the equality result
    (else (bool-val #t)))) ; Should not happen, but default to true

;; ========== VALUE CONSTRUCTORS ==========

;; Convenience constructors for creating ajsval instances
(define (V-num n)   (num-val n))
(define (V-bool b)  (bool-val b))
(define (V-str s)   (str-val s))
(define (V-null)    (null-val))
(define (V-clos ps body env0) (closure-val ps body env0))
(define (V-rec-clos name ps body env0) (rec-closure-val name ps body env0))

;; ========== RETURN EXCEPTION HANDLING ==========

;; Datatype for representing return statements as exceptions
(define-datatype return-exn return-exn?
  (a-return-exn (value ajsval?))) ; Wraps the return value

;; Create a return exception
(define (make-return-exn v)
  (a-return-exn v))

;; Extract value from return exception
(define (return-exn-value x)
  (cases return-exn x
    (a-return-exn (v) v)))

;; ========== INITIALIZATION MESSAGE ==========
(newline)
(display "data-structures built successfully")
