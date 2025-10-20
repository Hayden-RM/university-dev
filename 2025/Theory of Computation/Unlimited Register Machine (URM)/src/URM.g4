grammar URM;

// Parser Rules
program
    :
    config? ins+ EOF
    ;

// Accept config with any number of ints (i.e. config[9, 7,...n]
config
    : CONFIG INT (',' INT)* ']'
    ;

// Instructions
ins
    : jump
    | succ
    | zero
    | transfer
    ;

jump
    : JUMP INT ',' INT ',' INT ')'
    ;

succ
    : SUCC INT ')'
    ;

zero
    : ZERO INT ')'
    ;

transfer
    : TRANSFER INT ',' INT ')'
    ;

// Lexer Rules (case-insensitive keywords that include '(' / '['
CONFIG          : [Cc][Oo][Nn][Ff][Ii][Gg]'[' ;
JUMP            : [Jj][Uu][Mm][Pp]'(' ;
SUCC            : [Ss][Uu][Cc][Cc]'(' ;
ZERO            : [Zz][Ee][Rr][Oo]'(' ;
TRANSFER        : [Tt][Rr][Aa][Nn][Ss][Ff][Ee][Rr]'(' ;

// Integers
INT             : [0-9]+ ;

// Comments & Whitespace
LINE_COMMENT    : ('//' | '#') ~[\r\n]* -> skip ;
WS              : [ \t\r\n]+ -> skip ;

