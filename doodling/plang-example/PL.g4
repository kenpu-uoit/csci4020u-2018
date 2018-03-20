grammar PL;

program returns [List<Code> cs]
    : ( expr {$cs = Code.append($cs, $expr.c);} )+ EOF
    ;

expr returns [Code c]
    : x=expr OP y=expr { $c = Code.binOp($OP.text, $x.c, $y.c); }
    | '(' expr ')'     { $c = $expr.c; }
    | NUM              { $c = Code.data($NUM.text); }
    ;

stmt returns [Code c]
    : assignStmt
    | printStmt
    ;

assignStmt returns [Code c]
    : ID '=' expr
    ;

printStmt returns [Code c]
    : '->' expr
    ;

ID  : ([a-zA-Z])+;
NUM : [0-9]+ ('.' [0-9]+)? ;
OP  : '+' | '-' | '*' | '/' ;
WS  : [ \t\r\n] -> skip;

