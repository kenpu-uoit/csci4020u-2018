# Lab 5

In this lab, you will be experimenting with embedded actions.

## A parser

Consider the following grammar:

```
expr 
    : expr '+' expr
    | expr '-' expr
    | expr '*' expr
    | expr '/' expr
    | '(' expr ')'
    | Number
    ;

Number : [0-9]+;
WS : [ \t\r\n]+ -> skip;
``` 

Write a parsing program of arithmetic expressions.

## A calculator

Modify the grammar `expr` has an attribute.

```
expr returns [int x]
    : expr '+' expr { ... }
    | expr '-' expr { ... }
    | expr '*' expr { ... }
    | expr '/' expr { ... }
    | '(' expr ')'  { ... }
    | Number        { ... }
    ;

Number : [0-9]+;
WS : [ \t\r\n]+ -> skip;
```

- Complete the embedded actions
- Modify the parsing program so that it prints the final value.

