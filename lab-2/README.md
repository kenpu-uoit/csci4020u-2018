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
