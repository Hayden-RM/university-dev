#lang racket

#|

NAME: Hayden Richard-Marsters
COMP712 > Functional Programming Assignment - Question 3: Higher-Order Functions
STUDENT ID: 21152003
CONTACT: qjn4504@autuni.ac.nz

|#

;; QUESTION 3
;; ===========


;; Question 3a:  sum-prod-pair
;; returns a dotted pai (sum . product) of the list's elements using tail recursive looping.

(define (sum-prod-pair xs)

  (let loop ([xs xs] ; remaining loop to process
             [s 0] ; running sum accumulator
             [p 1]) ; running product accumulator
    
    (if (null? xs)
        (cons s p) ; when no elements are left, return (sum . product)
        (loop (rest xs) 
              (+ s (first xs)) ; add current element to running sum
              (* p (first xs)))))) ; multiply into running product

;; Question 3b: sum-prod-fold
;; Same result, however implemented using foldr as required. 

;; For reference:
;; foldr (fold right) and foldl (fold left) applies a given function to an accumulated value and each element of the list to produce a single value
;; Foldr applies the given function from the end to the beginning of the list.

(define (sum-prod-fold xs)
  (foldr (lambda (x acc)

           ; acc is a pair (sum . product); extract with car/cdr
           (cons (+ x (car acc)) ; new sum = x + previous sum
                 (* x (cdr acc)))) ; new product = x * previous product
         (cons 0 1) ; Base case: (sum . product) = (0 . 1) 
         xs))



;; TESTS
;; =====

;; sum-prod-pair
;; =============

(displayln (sum-prod-pair '(2 4))) ; -> '(6 . 8)
(displayln (sum-prod-pair '(-2 4))) ; -> '(2 . -8)
(displayln (sum-prod-pair '(7))) ; -> '(7 . 7)
(displayln (sum-prod-pair '(1 2 3 4 5))) ; -> '(15 . 120)
(displayln (sum-prod-pair '(-1 -2 -3 -4 -5))) ; -> ;(-15 . -120)


;; sum-prod-fold 
;; =============

(displayln (sum-prod-fold '(2 4))) ; -> '(6 . 8)
(displayln (sum-prod-fold '(-2 4))) ; -> '(2 . -8)
(displayln (sum-prod-fold '(7))) ; -> '(7 . 7)
(displayln (sum-prod-fold '(1 2 3 4 5))) ; -> '(15 . 120)
(displayln (sum-prod-fold '())) ; -> '(0 . 1)





