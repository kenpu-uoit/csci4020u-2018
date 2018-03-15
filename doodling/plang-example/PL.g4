grammar PL;

program 
    : expr+ EOF
    ;

expr
    : expr OP expr
    | '(' expr ')'
    | NUM
    ;

NUM : [0-9]+ ('.' [0-9]+)? ;
OP  : '+' | '-' | '*' | '/' ;
WS  : [ \t\r\n] -> skip;

