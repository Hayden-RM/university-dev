#lang eopl
(require eopl
         "ajstest-utils.rkt")
(provide (all-defined-out))

(newline)
(display "=== AJS Tests: arithmetic, const, comparisons, logic, ternary ===")
(newline)

;; --- Visible runs (should match comments) ---
(run "345;")                       ; 345
(run "123 + 45;")                  ; 168
(run "5 * 35;")                    ; 175
(run "1 - 5 / 2 * 4 + 3;")         ; -6
(run "3 * 2 * (5 + 1 - 2);")       ; 24
(run "const size = 5; size;")      ; 5
(run "const size = 5; 5 * size;")  ; 25
(run "-7;")                        ; -7
(run "+(1+2) * -3;")               ; -9

;; Ternary via lor-tail
(run "(3 > 2) ? 111 : 222;")       ; 111
(run "(3 > 5) ? 111 : 222;")       ; 222
(run "3 > 2 ? 3 : -3;")            ; 3
(run "3 < 2 ? 3 : -3;")            ; -3

;; Short-circuit && and ||
(run "(3 > 2) && (5 < 9);")        ; true
(run "(3 > 2) && (5 > 9);")        ; false
(run "(3 > 2) || (5 > 9);")        ; true
(run "(3 < 2) || (5 < 9);")        ; true

;; --- Function tests ---
(newline)
(display "--- Function Tests ---")
(newline)

;; Basic function declaration and calls - ALL IN ONE RUN STATEMENT
(run "function square(x) { return x * x; } square(21);")                ; 441
(run "function square(x) { return x * x; } square(4 + 2);")             ; 36
(run "function square(x) { return x * x; } square(3) + square(4);")     ; 25

;; Multiple parameters
(run "function square(x) { return x * x; } function sum_of_squares(x, y) { return square(x) + square(y); } sum_of_squares(3, 4);")      ; 25

;; Function using conditionals
(run "function abs(x) { return x >= 0 ? x : -x; } abs(6);")                    ; 6
(run "function abs(x) { return x >= 0 ? x : -x; } abs(-5);")                   ; 5
(run "function abs(x) { return x >= 0 ? x : -x; } abs(0);")                    ; 0

;; Nested function calls
(run "function square(x) { return x * x; } square(square(3));")         ; 81

;; Function composition
(run "function square(x) { return x * x; } function sum_of_squares(x, y) { return square(x) + square(y); } function f(a) { return sum_of_squares(a + 1, a * 2); } f(5);")                      ; 136

;; Multiple functions
(run "function max(a, b) { return a > b ? a : b; } max(10, 20);")               ; 20
(run "function max(a, b) { return a > b ? a : b; } max(-5, -10);")              ; -5

;; Function with multiple statements
(run "function double(x) { const result = x * 2; return result; } double(8);")                 ; 16

(run "function factorial(n) {

    return n === 1

	       ? 1

		   : n * factorial(n - 1);

} factorial(8);
function double(x) { const result = x * 2; return result; } double(8);")

(newline)
(display "--- Assertions ---")
(newline)
;; numeric checks
(check-num "345;" 345)
(check-num "123 + 45;" 168)
(check-num "5 * 35;" 175)
(check-num "1 - 5 / 2 * 4 + 3;" -6)
(check-num "3 * 2 * (5 + 1 - 2);" 24)
(check-num "const size = 5; size;" 5)
(check-num "const size = 5; 5 * size;" 25)
(check-num "-7;" -7)
(check-num "+(1+2) * -3;" -9)
(check-num "(3 > 2) ? 111 : 222;" 111)
(check-num "(3 > 5) ? 111 : 222;" 222)
(check-num "3 > 2 ? 3 : -3;" 3)
(check-num "3 < 2 ? 3 : -3;" -3)

;; boolean checks
(check-bool "(3 > 2) && (5 < 9);" #t)
(check-bool "(3 > 2) && (5 > 9);" #f)
(check-bool "(3 > 2) || (5 > 9);" #t)
(check-bool "(3 < 2) || (5 < 9);" #t)

;; function checks - EACH TEST MUST BE SELF-CONTAINED
(check-num "function square(x) { return x * x; } square(5);" 25)
(check-num "function square(x) { return x * x; } square(3);" 9)
(check-num "function square(x) { return x * x; } square(4 + 2);" 36)
(check-num "function square(x) { return x * x; } square(3) + square(4);" 25)
(check-num "function abs(x) { return x >= 0 ? x : -x; } abs(6);" 6)
(check-num "function abs(x) { return x >= 0 ? x : -x; } abs(-5);" 5)
(check-num "function abs(x) { return x >= 0 ? x : -x; } abs(0);" 0)
(check-num "function square(x) { return x * x; } square(square(3));" 81)
(check-num "function max(a, b) { return a > b ? a : b; } max(10, 20);" 20)
(check-num "function double(x) { const result = x * 2; return result; } double(8);" 16)

;; Add this section after the existing function tests
;; --- Simple Recursion Test ---
(newline)
(display "--- Simple Recursion Test ---")
(newline)
(run "function simple_rec(n) { return n === 0 ? 0 : simple_rec(n - 1) + 1; } simple_rec(3);")


(newline)
(display "=== End of tests ===")
