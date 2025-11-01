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

(newline)
(display "--- Assertions ---")

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

(newline)
(display "=== End of tests ===")
