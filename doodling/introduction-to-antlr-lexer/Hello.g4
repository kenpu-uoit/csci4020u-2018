lexer grammar Hello;

New : '***';

Whitespace : [ \t\r\n]+;

Keyword : 'let'
        | 'add'
        | 'delete'
        ;

String : '"'
         ( ~'"' )*
         '"';

Number : [0-9]+
       ;

ID : [a-zA-Z]+
   ;

OP : '=' | '+' | '-'
   ;
