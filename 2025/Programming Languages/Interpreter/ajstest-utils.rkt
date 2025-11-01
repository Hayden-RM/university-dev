#lang eopl
(require eopl
         "ajslang.rkt"
         "ajsdata-structures.rkt"
         "ajsinterp.rkt"
         (only-in racket/base void))
(provide (all-defined-out))

(define (andmap f xs) (if (null? xs) #t (and (f (car xs)) (andmap f (cdr xs)))))

;; Value extraction
(define (expect-number proc-name val)
  (cases ajsval val
    (num-val (n) n)
    (else (eopl:error proc-name "Expected number, got ~s" val))))

(define (expect-boolean proc-name val)
  (cases ajsval val
    (bool-val (b) b)
    (else (eopl:error proc-name "Expected boolean, got ~s" val))))

;; Core runners
(define (parse-ajs s) (parse s))

(define (eval-ajs s)
  (eval-program (parse-ajs s)))

(define (run s)
  (let ([outs (eval-ajs s)])
    (for-each ajs-print outs)
    (void)))

;; Assertion helpers
(define (only-num-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-num-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-num-out "expected one output, got ~a" (length outs))]
      [else (expect-number 'only-num-out (car outs))])))

(define (only-bool-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-bool-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-bool-out "expected one output, got ~a" (length outs))]
      [else (expect-boolean 'only-bool-out (car outs))])))

(define (say-ok program-str expected)
  (display "CORRECT  : ")
  (display program-str)
  (display " ==> ")
  (display expected))

(define (check-num program-str expected)
  (let ([got (only-num-out program-str)])
    (if (equal? got expected)
        (begin (say-ok program-str expected) (newline))
        (eopl:error 'check-num "INCORRECT ~a ==> got ~a, expected ~a" program-str got expected))))

(define (check-bool program-str expected-bool)
  (let ([got (only-bool-out program-str)])
    (if (eq? got expected-bool)
        (begin (display "CORRECT  : ") (display program-str)
               (display " ==> ") (display (if got "true" "false")))
        (eopl:error 'check-bool "INCORRECT ~a ==> got ~a, expected ~a" program-str got expected-bool))))

(define (check-many cases)
  (for-each (lambda (pr)
              (check-num (car pr) (cadr pr)))
            cases))

;; Pretty-printer - COMPLETE with rec-closure-val
(define (ajs-print v)
  (cases ajsval v
    (num-val (n)   (display n)   (newline))
    (bool-val (b)  (display (if b "true" "false")) (newline))
    (str-val (s)   (display "\"") (display s) (display "\"") (newline))
    (null-val ()   (display "null") (newline))
    (closure-val (params body env) (display "<function>") (newline))
    (rec-closure-val (name params body env) (display "<recursive-function>") (newline))))

(newline)
(display "test-utils built successfully")
