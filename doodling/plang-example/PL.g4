grammar PL;

program returns [List<Code> cs]
    : ( stmt {$cs = Code.append($cs, $stmt.c);} )+ EOF
    ;

expr returns [Code c]
    : x=expr OP y=expr { $c = Code.binOp($OP.text, $x.c, $y.c); }
    | '(' expr ')'     { $c = $expr.c; }
    | NUM              { $c = Code.data($NUM.text); }
    | ID               { $c = Code.variable($ID.text); }
    ;

stmt returns [Code c]
    : assignStmt    { $c = $assignStmt.c; }
    | printStmt     { $c = $printStmt.c; }
    | expr          { $c = $expr.c; }
    ;

assignStmt returns [Code c]
    : ID '=' expr   { $c = Code.assign($ID.text, $expr.c); }
    ;

printStmt returns [Code c]
    : '->' expr { $c = Code.print($expr.c); }
    ;

ID  : [a-z]+;
NUM : [0-9]+ ('.' [0-9]+)? ;
OP  : '+' | '-' | '*' | '/' ;
WS  : [ \t\r\n] -> skip;

