#lang eopl
(require eopl
         "ajslang.rkt"
         "ajsdata-structures.rkt"
         "ajsinterp.rkt"
         (only-in racket/base void))
(provide (all-defined-out))

;; EoPL doesn't always export andmap; define it if missing.
(define (andmap f xs) (if (null? xs) #t (and (f (car xs)) (andmap f (cdr xs)))))

;; --- Value extraction helpers ---

;; Extract number from ajsval
(define (expect-number proc-name val)
  (cases ajsval val
    (num-val (n) n)
    (else (eopl:error proc-name "Expected number, got ~s" val))))

;; Extract boolean from ajsval
(define (expect-boolean proc-name val)
  (cases ajsval val
    (bool-val (b) b)
    (else (eopl:error proc-name "Expected boolean, got ~s" val))))

;; --- Core runners ------------------------------------------------------------

;; parse-ajs : string -> Program AST (errors if parse fails)
(define (parse-ajs s) (parse s))

;; eval-ajs : string -> (listof ajsval)
;; Parses the program and evaluates it, returning the list of outputs
;; produced by expression statements.
(define (eval-ajs s)
  (eval-program (parse-ajs s)))

;; run : string -> void
;; Parses, evaluates, and prints outputs (using ajs-print).
(define (run s)
  (let ([outs (eval-ajs s)])
    (for-each ajs-print outs)
    (void)))

;; --- Tiny assertion helpers --------------------------

;; Extract number from a single-output run
(define (only-num-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-num-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-num-out "expected one output, got ~a" (length outs))]
      [else (expect-number 'only-num-out (car outs))])))

;; Extract boolean from a single-output run
(define (only-bool-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-bool-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-bool-out "expected one output, got ~a" (length outs))]
      [else (expect-boolean 'only-bool-out (car outs))])))

;; helper: print OK line without string-append on numbers
(define (say-ok program-str expected)
  (display "OK  : ")
  (display program-str)
  (display " ==> ")
  (display expected))

;; Numeric assertion
(define (check-num program-str expected)
  (let ([got (only-num-out program-str)])
    (if (equal? got expected)
        (begin (say-ok program-str expected) (newline))
        (eopl:error 'check-num "FAIL: ~a ==> got ~a, expected ~a" program-str got expected))))

;; Boolean assertion
(define (check-bool program-str expected-bool)
  (let ([got (only-bool-out program-str)])
    (if (eq? got expected-bool)
        (begin (display "OK  : ") (display program-str)
               (display " ==> ") (display (if got "true" "false")))
        (eopl:error 'check-bool "FAIL: ~a ==> got ~a, expected ~a" program-str got expected-bool))))

;; check-many : (listof (list program-str expected-number)) -> void
(define (check-many cases)
  (for-each (lambda (pr)
              (check-num (car pr) (cadr pr)))
            cases))

;; ============================================================
;; Pretty-printer for runtime values (used by run / tests)
;; ============================================================

;; Pretty-printer with explicit newlines (EoPL-safe)
(define (ajs-print v)
  (cases ajsval v
    (num-val (n)   (display n)   (newline))
    (bool-val (b)  (display (if b "true" "false")) (newline))
    (str-val (s)   (display "\"") (display s) (display "\"") (newline))
    (null-val ()   (display "null") (newline))
    (closure-val (params body env) (display "<function>") (newline))))

(newline)
(display "test-utils built successfully")
