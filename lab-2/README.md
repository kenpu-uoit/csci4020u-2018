# Lab 2: Lexical Analysis with Antlr

In this lab, we will construct an ANTLR lexical analyzer for a snippet of the C programming language.

Consider the C program:

```c
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
  printf("Program name = %s\n", argv[0]);
  printf("Message = %s\n", argv[1]);
}
```
## Types of tokens.

We will consider the following types of tokens:

- Directives such as `#include`.  See https://en.wikipedia.org/wiki/Directive_(programming)
- Library inclusions such as `<stdio.h>`
- Types: `int`, `float`, `char`, `char *`, `(char **)`
- Identifiers: `main`, `argc`, `printf` are all identifiers.
- Numeric literals: `0`, `1`
- String literals

For each type of tokens, specify the regular expression.

## Lexical analysis with ANTLR

Write an ANTLR lexer grammar, and a main Java class to perform lexical analysis on the given C program.

Feel free to add additional token types so that the entire program can be mapped to a stream of tokens.

