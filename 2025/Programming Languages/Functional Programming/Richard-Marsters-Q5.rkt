#lang racket

#|

NAME: Hayden Richard-Marsters
COMP712 > Functional Programming Assignment - Question 5: Streams in Racket
STUDENT ID: 21152003
CONTACT: qjn4504@autuni.ac.nz

|#

;; QUESTION 5
;; ==========


;; Q5a: sequence
;; =============
;; sequence : number number number -> (listof nmumber)
;; Produce list of numbers from low to high (inclusive) separated by stride and in sorted order.
;; If low > high, return the empty list
(define (sequence low high stride)
  (cond
    [(<= stride 0)
     (error "sequence: stride must be positive")]
    [(> low high) '()]
    [else (cons low (sequence (+ low stride) high stride))]))

;; Usage examples
(displayln (sequence 3 11 2)) ; -> '(3 5 7 9 11)
(displayln (sequence 3 8 3)) ; -> '(3 6)
(displayln (sequence 3 2 1)) ; -> '()



;; Q5b: string-append-map
;; ======================
;; string-append-map : (listof string) string -> (listof string)
;; Append suffix to each string in xs
(define (string-append-map xs suffix)
  (map (lambda (s) (string-append s suffix)) xs))

;; Usage examples
(displayln (string-append-map '("x" "y" "z") "-a")) ; -> '(x-a y-a z-a)
(displayln (string-append-map '("s" "b" "c") "at")) ; -> '(sat bat cat)



;; Q5c: list-nth-mod
;; =================
;; list-nth-mod : (listof any) integer -> any
;; Returns the element at index (remainder n (length xs)), counting from 0.
(define (list-nth-mod xs n)
  (cond
    [(< n 0) (error "list-nth-mod: negative number")]
    [(null? xs) (error "list-nth-mod: empty list")]
    {else
     (let* ([len (length xs)]
            [i (remainder n len)])
       (car (list-tail xs i)))}))

;; Usage examples
(displayln (list-nth-mod '(a b c) 0)) ; -> a
(displayln (list-nth-mod '(x y z) 5)) ; -> z  ; 5 mod 3 = 2
;; (displayln (list-nth-mod '() 3)) ; -> error termination: empty list
;; (displayln (list-nth-mod '(1 2 3) -1)) ; -> error termination: negative number



;; Q5d: stream-for-n-steps
;; =======================
;; stream-for-n-steps : stream nonneg-int -> (listof any)
;; return a list with the first n values produced by s in order
(define (stream-for-n-steps s n)
  (cond
    [(< n 0) (error "stream-for-n-steps: negative n")]
    [(zero? n) '()]
    [else
     (let ([pr (s)])
       (cons (car pr)
             (stream-for-n-steps (cdr pr) (sub1 n))))]))



;; Q5e: funny-number-stream
;; ========================
;; funny-number-stream : stream
;; Return a stream of natural numbers starting at 1, but numbers divisible by 5 are negated (i.e. 1, 2, 3, 4, -5, 6...)
(define (funny-number-stream)
  ;; local helper building the stream starting from n
  (define (from n)
    (lambda ()
      (define val (if (zero? (remainder n 5)) (- n) n))
      (cons val (from (add1 n)))))
  (from 1))

;; Usage examples for (d) and (e)
(displayln (stream-for-n-steps (funny-number-stream) 15)) ; -> (1 2 3 4 -5 6 7 8 9 -10 11 12 13 14 -15)
(displayln (stream-for-n-steps (funny-number-stream) 10)) ; -> (1 2 3 4 -5 6 7 8 9 -10)
; (displayln (stream-for-n-steps (funny-number-stream) -10)) -> error termination: negative n






  







  