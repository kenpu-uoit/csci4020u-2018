grammar PL;

program returns [List<Code> cs]
    : block EOF { $cs = $block.c.children; }
    ;

block returns [Code c]
    @init { $c = Code.block(); }
    : ( stmt { $c.children.add($stmt.c);} )+
    ;

expr returns [Code c]
    : x=expr OP y=expr { $c = Code.binOp($OP.text, $x.c, $y.c); }
    | '(' expr ')'     { $c = $expr.c; }
    | ifElse           { $c = $ifElse.c; }
    | NUM              { $c = Code.data($NUM.text); }
    | ID               { $c = Code.variable($ID.text); }
    ;

ifElse returns [Code c]
    : 'if' cond '{' x=block '}' { $c = Code.ifElse($cond.c, $x.c); }
      ('else' '{' y=block '}' { $c.children.add($y.c); }
      )?
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

cond returns [Code c]
    : a=expr CMP b=expr     { $c = Code.compare($CMP.text, $a.c, $b.c); }
    | 'not' cond            { $c = Code.not($cond.c); }
    | x=cond 'and' y=cond   { $c = Code.and($x.c, $y.c); }
    | x=cond 'or' y=cond    { $c = Code.or($x.c, $y.c); }
    | '(' cond ')'          { $c = $cond.c; }
    ;

ID  : [a-z]+;
NUM : [0-9]+ ('.' [0-9]+)? ;
OP  : '+' | '-' | '*' | '/' ;
CMP : '<' | '>' | '==' ;
WS  : [ \t\r\n] -> skip;

