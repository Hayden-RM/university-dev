#lang racket

#|

NAME: Hayden Richard-Marsters
COMP712 > Functional Programming Assignment - Question 4: Remember the Past
STUDENT ID: 21152003
CONTACT: qjn4504@autuni.ac.nz

|#

;; QUESTION 4
;; ===========

;; Function 'fib' computes the fibonacci sequence as follows
(define (fib n)
  (if (< n 2)
      n
      (+ (fib (- n 1)) (fib (- n 2)))))

;; Q4a: Monitoring

;; Wrap a 1-arg function 'f' with a call counter
;; Symbols:
;; 'how-many-calls?' -> return count
;; 'reset-count' -> reset to 0 and return 0
(define (fmonitor f)
  (let ([count 0])
    (lambda (x)
      (cond
        [(eq? x 'how-many-calls?) count]
        [(eq? x 'reset-count) (set! count 0) 0]
        [else (set! count (+ count 1))
              (f x)]))))

;; Q4b: Memoize

;; Helper Functions

;; table-find : table any -> (list arg result) or #f
;; Return the first (arg result) pair whose arg equals key, else #f
(define (table-find tbl key)
  (cond
    [(null? tbl) #f]
    [(equal? (caar tbl) key) (car tbl)]
    [else (table-find (cdr tbl) key)]))

;; table-ref : table any any -> any
;; Return the stored result forkey if present; otherwise return 'default'
(define (table-ref tbl key default)
  (let ([pair (table-find tbl key)])
    (if pair (cadr pair) default)))

;; table-set : table any any -> table
;; Return a new table with (key value) added at the front. 
;; (simple add; if key already exists, we allow duplicates-first hit wins.)
(define (table-set tbl key value)
  (cons (list key value) tbl))

;; memoize : cache results as (arg result) pairs; skip caching for symbols
(define (memoize f)
  (let ([table '()]) ; list of (arg result)
    (lambda (x)
      (if (symbol? x)
          (f x)
          (let ([hit (table-find table x)])
            (if hit
                (cadr hit)
                (let ([v (f x)])
                  (set! table (table-set table x v))
                  v)))))))


(set! fib (fmonitor fib))
(set! fib (memoize fib))


;; TESTS
;; ======
;; (displayln (fib 35))
;; (displayln (fib 35))              ; cache hit; count shouldn't increase now
;; (displayln (fib 'how-many-calls?))
;; (displayln (fib 'reset-count))
(displayln (fib 8))
(displayln (fib 'how-many-calls?))
(displayln (fib 10))
(displayln (fib 'how-many-calls?))
(displayln (fib 'reset-count))
(displayln (fib 15))
(displayln (fib 'how-many-calls?))



