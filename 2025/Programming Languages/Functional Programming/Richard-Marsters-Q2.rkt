#lang racket

#|

NAME: Hayden Richard-Marsters
COMP712 > Functional Programming Assignment - Question 2: Sublist
STUDENT ID: 21152003
CONTACT: qjn4504@autuni.ac.nz

|#

;; QUESTION 2
;; ===========

;; Strategy:
;; ----------
;; - Define a helper prefix (prefix?) that checks whether the sub-list pattern (pat) is a prefix of the second list (xs)
;; - Slide pat along list: if pat isn't a prefix at the current position, recurse on (rest-list) and try again, returns true (#t) if match found, and false (#f) if not found
;; 
;; Edge cases:
;; -----------
;; - The empty list is a contiguous sub-list of any list (including empty)
;; - If pat is non-empty and list is empty, a matching cannot be found - returning false (#f)
;; - capitalised are not equal to lowercase. i.e. '(A B C) does not match '(a b c) 



;; Purpose: function returns true if the pattern is a prefix to the list, false otherwise. 
(define (prefix? pat xs)
  (cond

    ;; Base case  1: If 'pat' is empty, return true - knowing that an empty 'pat' is a contiguous sublist if any list (including empty)
    [(null? pat) #t]

    ;; Base case 2: If 'pat' has elements but 'xs' has run out, then return false as 'pat' cannot be a prefix here
    [(null? xs) #f]

    ;; Recursive case: if the heads match, keep checking the tails
    ;; Otherwise, this position fails (handled by else) 
    [(equal? (first pat) (first xs))
     (prefix? (rest pat) (rest xs))]

    ;; Heads don't match -> not a prefix at this position
    [else #f]))


;; Purpose: function returns true if the pattern appears as a contiguous sublist somewhere within the list
(define (sublist pat xs)
  (cond

    ;; Base case 1: an empty pattern is contained in any list
    [(null? pat) #t]

    ;; Base case 2: returns false if list is empty
    [(null? xs) #f]

    ;; if 'pat' is a prefix starting at this position, return true as match found
    [(prefix? pat xs) #t]

    ;; Otherwise, recursively step through list and repeat
    [else (sublist pat (rest xs))]))



;; TESTS
;; ======

(displayln (sublist '(a b c) '(a b c d))) ; -> #t
(displayln (sublist '(a b z) '(a b c d))) ; -> #f
(displayln (sublist '() '(a b c))) ; -> #t
(displayln (sublist '(e f) '(a b c d e f))) ; -> #t
(displayln (sublist '(a b c d e f) '(a b c))) ; -> #f
(displayln (sublist '(A B C) '(a b c))) ; -> #f 
(displayln (sublist '(1 2 3 4) '(1 2 3 4  5 6))) ; -> #t 
(displayln (sublist '(1 2 4 3) '(1 2 3 4 5 6))) ; -> #f
(displayln (sublist '(a a a) '(a))) ; -> #f
(displayln (sublist '(b a) '(a b a a b a))) ; -> #t
(displayln (sublist '(a b c) '(a b c))) ; -> #t





  

