#lang eopl
(require eopl
         "ajslang.rkt"
         "ajsdata-structures.rkt"
         "ajsinterp.rkt"
         (only-in racket/base void))
(provide (all-defined-out))

;; Helper function similar to Racket's andmap
(define (andmap f xs) (if (null? xs) #t (and (f (car xs)) (andmap f (cdr xs)))))

;; ========== VALUE EXTRACTION FUNCTIONS ==========

;; Extract number value from ajsval, throw error if not a number
(define (expect-number proc-name val)
  (cases ajsval val
    (num-val (n) n)
    (else (eopl:error proc-name "Expected number, got ~s" val))))

;; Extract boolean value from ajsval, throw error if not a boolean
(define (expect-boolean proc-name val)
  (cases ajsval val
    (bool-val (b) b)
    (else (eopl:error proc-name "Expected boolean, got ~s" val))))

;; ========== CORE EVALUATION FUNCTIONS ==========

;; Parse AJS program string to abstract syntax
(define (parse-ajs s) (parse s))

;; Evaluate parsed AJS program
(define (eval-ajs s)
  (eval-program (parse-ajs s)))

;; Run program and print all outputs
(define (run s)
  (let ([outs (eval-ajs s)])
    (for-each ajs-print outs)  ; Print each output value
    (void)))                   ; Return void to avoid printing return value

;; ========== ASSERTION AND TESTING HELPERS ==========

;; Expect exactly one numeric output from program
(define (only-num-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-num-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-num-out "expected one output, got ~a" (length outs))]
      [else (expect-number 'only-num-out (car outs))])))

;; Expect exactly one boolean output from program
(define (only-bool-out s)
  (let ([outs (eval-ajs s)])
    (cond
      [(null? outs) (eopl:error 'only-bool-out "no output produced by program")]
      [(pair? (cdr outs)) (eopl:error 'only-bool-out "expected one output, got ~a" (length outs))]
      [else (expect-boolean 'only-bool-out (car outs))])))

;; Display success message for test case
(define (say-ok program-str expected)
  (display "CORRECT  : ")
  (display program-str)
  (display " Expected ==> ")
  (display expected))

;; Check if program produces expected numeric result
(define (check-num program-str expected)
  (let ([got (only-num-out program-str)])
    (if (equal? got expected)
        (begin (say-ok program-str expected) (newline))
        (eopl:error 'check-num "INCORRECT ~a ==> got ~a, expected ~a" program-str got expected))))

;; Check if program produces expected boolean result
(define (check-bool program-str expected-bool)
  (let ([got (only-bool-out program-str)])
    (if (eq? got expected-bool)
        (begin (display "CORRECT  : ") (display program-str)
               (display " ==> ") (display (if got "true" "false")))
        (eopl:error 'check-bool "INCORRECT ~a ==> got ~a, expected ~a" program-str got expected-bool)))(newline))

;; Run multiple numeric test cases
(define (check-many cases)
  (for-each (lambda (pr)
              (check-num (car pr) (cadr pr)))
            cases))

;; ========== PRINTING ==========

;; Print AJS values with appropriate formatting
(define (ajs-print v)
  (cases ajsval v
    (num-val (n)   (display n)   (newline))                    ; Print numbers directly
    (bool-val (b)  (display (if b "true" "false")) (newline)) ; Print as "true"/"false"
    (str-val (s)   (display "\"") (display s) (display "\"") (newline)) ; Quote strings
    (null-val ()   (display "null") (newline))                ; Print null literal
    (closure-val (params body env) (display "<function>") (newline)) ; Function representation
    (rec-closure-val (name params body env) (display "<recursive-function>") (newline)))) ; Recursive function

;; ========== INITIALIZATION ==========
(newline)
(display "test-utils built successfully")
