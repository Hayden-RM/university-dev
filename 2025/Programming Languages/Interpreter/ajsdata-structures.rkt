#lang eopl
(require eopl)
(provide (all-defined-out))

;; ============================================================
;; AJS Data Structures
;; ============================================================
(define (andmap f lst)
  (cond
    [(null? lst) #t]
    [else (and (f (car lst)) (andmap f (cdr lst)))]))

(define (symbol-list? xs) (and (list? xs) (andmap symbol? xs)))

;; ============================================================
;; Environments
;; ============================================================
(define-datatype env env?
  (empty-env)
  (extend-env
    (names symbol-list?)
    (vals  (lambda (v) (and (list? v) (andmap ajsval? v))))
    (parent env?)))

(define (env-lookup rho x)
  (cases env rho
    (empty-env ()
      (eopl:error 'env-lookup "unbound identifier ~a" x))
    (extend-env (ns vs parent)
      (let loop ((ns ns) (vs vs))
        (cond
          [(null? ns) (env-lookup parent x)]
          [(eq? (car ns) x) (car vs)]
          [else (loop (cdr ns) (cdr vs))])))))

(define (env-extend1 rho x v)
  (extend-env (list x) (list v) rho))

(define (env-extend* rho names vals)
  (when (not (= (length names) (length vals)))
    (eopl:error 'env-extend* "arity mismatch: ~a names vs ~a values"
                (length names) (length vals)))
  (extend-env names vals rho))

;; ============================================================
;; Values (runtime)
;; ============================================================
(define-datatype ajsval ajsval?
  (num-val   (n number?))
  (bool-val  (b boolean?))
  (str-val   (s string?))
  (null-val)
  (closure-val
    (params symbol-list?)
    (body   (lambda (_) #t))
    (env0   env?))
  (rec-closure-val
    (func-name symbol?)
    (params symbol-list?)
    (body   (lambda (_) #t))
    (env0   env?)))

;; Tag testers
(define (num-val? v)
  (cases ajsval v
    (num-val (_) #t)
    (else #f)))

(define (bool-val? v)
  (cases ajsval v
    (bool-val (_) #t)
    (else #f)))

(define (str-val? v)
  (cases ajsval v
    (str-val (_) #t)
    (else #f)))

(define (null-val? v)
  (cases ajsval v
    (null-val () #t)
    (else #f)))

(define (closure-val? v)
  (cases ajsval v
    (closure-val (p b e) #t)
    (else #f)))

(define (rec-closure-val? v)
  (cases ajsval v
    (rec-closure-val (n p b e) #t)
    (else #f)))

;; Extractors
(define (expect-number who v)
  (cases ajsval v
    (num-val (n) n)
    (else (eopl:error who "expected number, got ~s" v))))

(define (expect-boolean who v)
  (cases ajsval v
    (bool-val (b) b)
    (else (eopl:error who "expected boolean, got ~s" v))))

(define (expect-closure who v)
  (cases ajsval v
    (closure-val (ps body rho) (list ps body rho))
    (rec-closure-val (name ps body rho) (list ps body rho))
    (else (eopl:error who "expected function/closure, got ~s" v))))

;; Truthiness
(define (truthy? v)
  (cases ajsval v
    (bool-val  (b) b)
    (null-val  () #f)
    (num-val   (n) (not (equal? n 0)))
    (str-val   (s) (not (string=? s "")))
    (closure-val (p b e) #t)
    (rec-closure-val (n p b e) #t)))

;; Equality
(define (ajs=== a b)
  (bool-val
   (cond
     [(and (num-val? a) (num-val? b))
      (= (expect-number '=== a) (expect-number '=== b))]
     [(and (bool-val? a) (bool-val? b))
      (eq? (expect-boolean '=== a) (expect-boolean '=== b))]
     [(and (str-val? a) (str-val? b))
      (string=? (cases ajsval a (str-val (s) s) (else ""))
                (cases ajsval b (str-val (s) s) (else "")))]
     [(and (null-val? a) (null-val? b)) #t]
     [(and (closure-val? a) (closure-val? b)) (eq? a b)]
     [(and (rec-closure-val? a) (rec-closure-val? b)) (eq? a b)]
     [else #f])))

(define (ajs!== a b)
  (cases ajsval (ajs=== a b)
    (bool-val (x) (bool-val (not x)))
    (else (bool-val #t))))

;; Constructors
(define (V-num n)   (num-val n))
(define (V-bool b)  (bool-val b))
(define (V-str s)   (str-val s))
(define (V-null)    (null-val))
(define (V-clos ps body env0) (closure-val ps body env0))
(define (V-rec-clos name ps body env0) (rec-closure-val name ps body env0))

;; ============================================================
;; Return-as-exception
;; ============================================================
(define-datatype return-exn return-exn?
  (a-return-exn (value ajsval?)))

(define (make-return-exn v)
  (a-return-exn v))

(define (return-exn-value x)
  (cases return-exn x
    (a-return-exn (v) v)))

(newline)
(display "data-structures built successfully")
