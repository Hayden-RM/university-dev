#lang eopl
(require eopl) ; <-- add format here
(provide (all-defined-out))

;; ============================================================
;; AJS Data Structures
;;  - Environments (lexical scope)
;;  - Runtime Values (nums, bools, strings, null, closures)
;;  - Return-as-exception mechanism
;;  - Small helper utilities
;; ============================================================
;; EoPL lacks andmap, so define it manually
(define (andmap f lst)
  (cond
    [(null? lst) #t]
    [else (and (f (car lst)) (andmap f (cdr lst)))]))



;; ---------- small predicates ----------
(define (symbol-list? xs) (and (list? xs) (andmap symbol? xs)))

;; ============================================================
;; Environments
;;   Chain of frames; each frame binds a parallel list of names/values.
;;   const-only: no mutation, no rebind
;; ============================================================
(define-datatype env env?
  (empty-env)
  (extend-env
    (names symbol-list?)
    (vals  (lambda (v) (and (list? v) (andmap ajsval? v))))
    (parent env?)))

;; Safe lookup (error on unbound)
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

;; Extend by one binding
(define (env-extend1 rho x v)
  (extend-env (list x) (list v) rho))

;; Extend by multiple (names, values) — lengths must match
(define (env-extend* rho names vals)
  (when (not (= (length names) (length vals)))
    (eopl:error 'env-extend* "arity mismatch: ~a names vs ~a values"
                (length names) (length vals)))
  (extend-env names vals rho))

;; ============================================================
;; Values (runtime)
;; ============================================================

;; Forward declare ajsval? so env above can refer to it in its predicate
;; (define-datatype below will provide ajsval and ajsval?)
;; NOTE: env definition already references ajsval? via a lambda, which is
;;       bound after this definition. In Racket that’s fine (late bound).

(define-datatype ajsval ajsval?
  (num-val   (n number?))
  (bool-val  (b boolean?))
  (str-val   (s string?))
  (null-val)
  ;; closure: params, body-AST, defining-environment
  (closure-val
    (params symbol-list?)
    (body   (lambda (_) #t))      ; accept any AST node; evaluator knows shape
    (env0   env?)))

;; Tag testers (since EoPL define-datatype doesn’t make them automatically)
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



;; Extractors / expectations
(define (expect-number who v)
  (cases ajsval v
    (num-val (n) n)
    (else (eopl:error who "expected number, got ~s" v))))

(define (expect-boolean who v)
  (cases ajsval v
    (bool-val (b) b)
    (else (eopl:error who "expected boolean, got ~s" v))))

;; In ajsdata-structures.rkt, replace expect-closure:
(define (expect-closure who v)
  (cases ajsval v
    (closure-val (ps body rho) (list ps body rho))
    (else (eopl:error who "expected function/closure, got ~s" v))))

;; JS-like truthiness (minimal subset we need)
;; falsey: false, null, 0, "" ; everything else truthy (incl. functions)
(define (truthy? v)
  (cases ajsval v
    (bool-val  (b) b)
    (null-val  () #f)
    (num-val   (n) (not (equal? n 0)))
    (str-val   (s) (not (string=? s "")))
    (closure-val (p b e) #t)))

;; Strict value equality helper (for ===)
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
     [else #f])))

(define (ajs!== a b)
  (cases ajsval (ajs=== a b)
    (bool-val (x) (bool-val (not x)))
    (else (bool-val #t)))) ; unreachable, but keeps total

;; Constructors for convenience (used by interpreter)
(define (V-num n)   (num-val n))
(define (V-bool b)  (bool-val b))
(define (V-str s)   (str-val s))
(define (V-null)    (null-val))
(define (V-clos ps body env0) (closure-val ps body env0))

;; ============================================================
;; Return-as-exception (to implement `return` control flow)
;; ============================================================
;; replace the struct definition + helpers:
(define (raise v)
  (eopl:error 'raise "raised value: ~s" v))

;; ===== Return-as-exception (EoPL-friendly, no structs) =====
(define (make-return-exn v) (list 'return-exn v))
(define (return-exn? x) (and (pair? x) (eq? (car x) 'return-exn)))
(define (return-exn-value x) (cadr x))

(define (raise-return v)
  (raise (make-return-exn v)))

(newline)
(display "data-structures built successfully")

