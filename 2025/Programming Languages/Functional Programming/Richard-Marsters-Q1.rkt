#lang racket

#|

NAME: Hayden Richard-Marsters
COMP712 > Functional Programming Assignment - Question 1: Recursion
STUDENT ID: 21152003
CONTACT: qjn4504@autuni.ac.nz

|#

; Function returns the maximum value within a list.
; If the list is empty, it returns negative infinity
(define (max-list ls)

  ; Base case: if the list is empty, return -inf.0.
  (if (null? ls)
      -inf.0

      ; Recursive case:
      ; Compute the maximum of the tail once and bind it to rest-max,
      ; so we don't recompute the same subproblem
      (let ([rest-max (max-list (rest ls))])

        ; Compare the first element with the maximum of the rest.
        ; Since we're in the non-empty branch, (first ls) and (rest ls) are safe to call.
        (if (> (first ls) rest-max)
            (first ls) ; if the first element is larger, it's the max, thus returning first ls as the maximum value within the list 
            rest-max)))) ; otherwise, the max of the rest is larger, thus returning rest-max as the maximum value within the list

#|

Question 1:

Firstly, Ls (capital L) is not the same as ls (lowercase L) originally in line 4.
Racket is case-sensitive, so Ls an undefined identifier.
Therefore, we fix the issue by renaming Ls -> ls in line 4.

a) Explain why the original Racket function is NOT efficient

The originial Racket function is not efficient because it calls (max-list (rest ls)) twice
in the false branch, causing unnecessary recomputation. Since, Racket eagerly evaluates, that
second call re-traverses the entire tail.

b) Rewrite the function using let to make it more efficient

By using let, the function now only executes (max-list (rest ls)) once, and
stores it in rest-max, which is reused, making it more efficient as the function no longer
re-traverses the tail. 

|#

;; TESTS
;; ======

(displayln (max-list '(4 2 8 4 3 3 7 12 53))) ; -> 53
(displayln (max-list '(5 1 1 1 6 1 1))) ; -> 6
(displayln (max-list '(5 1 1 1 2))) ; -> 5
(displayln (max-list '())) ; -> -inf.0
(displayln (max-list '(1))) ; -> 1
(displayln (max-list '(-1 1))) ; -> 1            
(displayln (max-list '(-4 -2 -10 -1))) ; -> -1
(displayln (max-list '(2 2 2 2 2))) ; -> 2



