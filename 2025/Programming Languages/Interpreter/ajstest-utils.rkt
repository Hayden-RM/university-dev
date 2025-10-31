#lang eopl
(require eopl
         "ajslang.rkt"              ; provides scan/parse and AST types
         "ajsdata-structures.rkt"   ; values, env, printers
         "ajsinterp.rkt"
         (only-in racket/base void))           ; eval-program, etc.
(provide (all-defined-out))

;; EoPL doesn't always export andmap; define it if missing.
(define (andmap f xs) (if (null? xs) #t (and (f (car xs)) (andmap f (cdr xs)))))

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

;; --- Tiny assertion helpers (phase 1: numeric only) --------------------------

;; Extract number from a single-output run
(define (only-num-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-num-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-num-out "expected one output, got ~a" (length outs))]
      [else (expect-number 'only-num-out (car outs))])))

;; helper: print OK line without string-append on numbers
(define (say-ok program-str expected)
  (display "OK  : ")
  (display program-str)
  (display " ==> ")
  (display expected))

(define (check-num program-str expected)
  (let ([got (only-num-out program-str)])
    (if (equal? got expected)
        (say-ok program-str expected)
        (eopl:error 'check-num "FAIL: ~a ==> got ~a, expected ~a" program-str got expected))))


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
    (closure-val (p b e) (display "<function>") (newline))))
