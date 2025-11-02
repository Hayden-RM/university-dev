#lang eopl
(require eopl
         "ajstest-utils.rkt")
(provide (all-defined-out))

;;<==============================>
;;    ***** AJS TESTING ******
;;<==============================>

(newline)
(newline)
(display "======== TESTING BEGIN ===========")
(newline)
(newline)
(display "=== AJS Tests: arithmetic, const, comparisons, logic, ternary ===")
(newline)

;; ================= Arithmetic Tests =================
(check-num "345;" 345)
(check-num "123 + 45;" 168)
(check-num "5 * 35;" 175)
(check-num "1 - 5 / 2 * 4 + 3;" -6)
(check-num "3 * 2 * (5 + 1 - 2);" 24)

;; ================= Constant Tests =================
(check-num "const size = 5; size;" 5)
(check-num "const size = 5; 5 * size;" 25)
(check-num "-7;" -7)
(check-num "+(1+2) * -3;" -9)

;; ================= Ternary Operator Tests =================
(check-num "(3 > 2) ? 111 : 222;" 111)
(check-num "(3 > 5) ? 111 : 222;" 222)
(check-num "3 > 2 ? 3 : -3;" 3)
(check-num "3 < 2 ? 3 : -3;" -3)

;; ================= Boolean Logic Tests =================
(check-bool "(3 > 2) && (5 < 9);" #t)
(check-bool "(3 > 2) && (5 > 9);" #f)
(check-bool "(3 > 2) || (5 > 9);" #t)
(check-bool "(3 < 2) || (5 < 9);" #t)

;; ================= Function Tests =================
(newline)
(display "=== Function Tests ===")
(newline)

;; Basic function declaration and calls
(check-num "function square(x) { return x * x; } square(21);" 441)
(check-num "function square(x) { return x * x; } square(4 + 2);" 36)
(check-num "function square(x) { return x * x; } square(3) + square(4);" 25)

;; Multiple parameters
(check-num "function square(x) { return x * x; } function sum_of_squares(x, y) { return square(x) + square(y); } sum_of_squares(3, 4);" 25)

;; Function using conditionals
(check-num "function abs(x) { return x >= 0 ? x : -x; } abs(6);" 6)
(check-num "function abs(x) { return x >= 0 ? x : -x; } abs(-5);" 5)
(check-num "function abs(x) { return x >= 0 ? x : -x; } abs(0);" 0)

;; Nested function calls
(check-num "function square(x) { return x * x; } square(square(3));" 81)

;; Function composition
(check-num "function square(x) { return x * x; } function sum_of_squares(x, y) { return square(x) + square(y); } function f(a) { return sum_of_squares(a + 1, a * 2); } f(5);" 136)

;; Multiple functions
(check-num "function max(a, b) { return a > b ? a : b; } max(10, 20);" 20)
(check-num "function max(a, b) { return a > b ? a : b; } max(-5, -10);" -5)

;; Function with multiple statements
(check-num "function double(x) { const result = x * 2; return result; } double(8);" 16)

;; ================= Recursion Tests =================
(newline)
(display "=== Recursion Tests ===")
(newline)

;; Test 1: Simple counting recursion
(check-num "function count(n) { return n === 0 ? 0 : 1 + count(n - 1); } count(3);" 3)

;; Test 2: Factorial (mathematical recursion)
(check-num "function factorial(n) { return n === 1 ? 1 : n * factorial(n - 1); } factorial(5);" 120)

;; Test 3: Fibonacci (multiple recursive calls)
(check-num "function fib(n) { return n === 0 ? 0 : n === 1 ? 1 : fib(n - 1) + fib(n - 2); } fib(6);" 8)

;; Test 4: Sum of numbers
(check-num "function sum(n) { return n === 0 ? 0 : n + sum(n - 1); } sum(5);" 15)

;; Test 5: Power function
(check-num "function power(base, exp) { return exp === 0 ? 1 : base * power(base, exp - 1); } power(2, 4);" 16)

;; Test 6: GCD using subtraction (no modulo operator)
(check-num "function gcd(a, b) { return a === b ? a : a > b ? gcd(a - b, b) : gcd(a, b - a); } gcd(48, 18);" 6)

;; Test 7: String length (simulated)
(check-num "function str_length(n) { return n === 0 ? 0 : 1 + str_length(n - 1); } str_length(5);" 5)

;; Test 8: Multiple base cases (numeric result)
(check-num "function multi_base(n) { return n === 0 ? 0 : n === 1 ? 1 : n === 2 ? 2 : 99; } multi_base(1);" 1)

;; Test 9: Tree recursion (simplified ackermann)
(check-num "function ackermann(m, n) { return m === 0 ? n + 1 : n === 0 ? ackermann(m - 1, 1) : ackermann(m - 1, ackermann(m, n - 1)); } ackermann(2, 2);" 7)

;; Test 10: Recursion with multiple parameters
(check-num "function multiply(a, b) { return b === 0 ? 0 : a + multiply(a, b - 1); } multiply(4, 3);" 12)

;; Test 11: Tail recursion
(check-num "function tail_factorial(n, acc) { return n === 0 ? acc : tail_factorial(n - 1, n * acc); } function factorial(n) { return tail_factorial(n, 1); } factorial(4);" 24)

;; Test 12: Nested recursive calls
(check-num "function nested(n) { return n === 0 ? 0 : 1 + nested(n - 1); } nested(3);" 3)

;; Test 13: Recursive function as parameter
(check-num "function apply_twice(f, x) { return f(f(x)); } function increment(x) { return x + 1; } apply_twice(increment, 5);" 7)

;; Test 14: Deep recursion test
(check-num "function deep(n) { return n === 0 ? 0 : 1 + deep(n - 1); } deep(10);" 10)

;; Test 15: Complex factorial with different base case
(check-num "function fact2(n) { return n <= 1 ? 1 : n * fact2(n - 1); } fact2(6);" 720)

;; Test 16: Exponential growth
(check-num "function exp_growth(n) { return n === 0 ? 1 : 2 * exp_growth(n - 1); } exp_growth(5);" 32)

;; Test 17: Binary recursion
(check-num "function bin_recur(n) { return n === 0 ? 0 : n === 1 ? 1 : bin_recur(n - 1) + bin_recur(n - 2); } bin_recur(5);" 5)

(display "========== if / else tests =======")
(newline)
;; ========== if / else tests =======
(newline)
(display "=== if / else tests ===")
(newline)

;; These should all produce output
(check-num "if (true) { 42; }" 42)
(check-num "if (true) { 42; } else { 24; }" 42)
(check-num "if (false) { 42; } else { 24; }" 24)
(check-num "if (true) { if (false) { 1; } else { 2; } } else { 3; }" 2)
(check-num "if (3 > 2) { 42; } else { 24; }" 42)
(check-num "if (3 < 2) { 42; } else { 24; }" 24)
(check-num "const x = 5; if (x > 3) { x * 2; } else { x; }" 10)
(check-num "const x = 2; if (x > 3) { x * 2; } else { x; }" 2)
(check-num "const x = 5; const y = 10; if (x > 0) { if (y > 5) { x + y; } else { x; } } else { 0; }" 15)

;; Test for no output case separately
(display "Testing if (false) { 42; } - should produce no output: ")
(let ([outs (eval-ajs "if (false) { 42; }")])
  (if (null? outs)
      (display "CORRECT") 
      (display "INCORRECT"))
  (newline))

;; ================= Operator Precedence Tests =================
(newline)
(display "=== Operator Precedence Tests ===")
(newline)

(check-num "1 + 2 * 3;" 7)        ; Multiplication before addition
(check-num "(1 + 2) * 3;" 9)      ; Parentheses override
(check-num "5 - 3 - 1;" 1)        ; Left associativity of subtraction
(check-num "12 / 3 / 2;" 2)       ; Left associativity of division
(check-num "2 * 3 + 4 * 5;" 26)   ; Mixed precedence

(newline)
(display "=== ALL TESTS COMPLETE ===")
(newline)
