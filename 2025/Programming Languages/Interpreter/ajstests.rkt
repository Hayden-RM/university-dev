#lang eopl
(require eopl
         "ajstest-utils.rkt")     ; brings in run, check-num, etc.
(provide (all-defined-out))

(display "=== AJS Phase-1 Tests: Integers, const, arithmetic, unary ± ===")
(newline)

;; ------------------------------
;; Simple run tests (visible output)
;; ------------------------------
(display "--- Direct run outputs: simple arithmetic tests ---" )
(newline)

(run "345;")                      ; expect 345
(run "123 + 45;")                 ; expect 168
(run "5 * 35;")                   ; expect 175
(run "1 - 5 / 2 * 4 + 3;")        ; expect -6
(run "3 * 2 * (5 + 1 - 2);")      ; expect 24
(run "const size = 5; size;")     ; expect 5
(run "const size = 5; 5 * size;") ; expect 25
(run "-7;")                       ; expect -7
(run "+(1+2) * -3;")              ; expect -9

(newline)
(display "=== End of Phase-1 Tests ===")
