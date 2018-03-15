grammar PL;

program returns [List<Code> cs]
    : ( expr {$cs = Code.append($cs, $expr.c);} )+ EOF
    ;

expr returns [Code c]
    : x=expr OP y=expr { $c = Code.binOp($OP.text, $x.c, $y.c); }
    | '(' expr ')'     { $c = $expr.c; }
    | NUM              { $c = Code.data($NUM.text); }
    ;

NUM : [0-9]+ ('.' [0-9]+)? ;
OP  : '+' | '-' | '*' | '/' ;
WS  : [ \t\r\n] -> skip;

