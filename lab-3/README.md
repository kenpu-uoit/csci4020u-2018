# ANTLR Grammar

In this lab, we are to experiment with the ANTLR library to author context free grammars.  This lab is based
on the reference book: _The Definitive ANTLR 4 Reference_, section 4.1.


## The language to parse

We will be working with a language of a simple integer based calculator.

Here are some examples of the strings in the language.

1. `10 + 20`
2. `3 * (10 + 20)`
3. `(4 * 4) + ((3 + 4) * (4 * 5))`

## Design the lexical analyzer

We will have the following token types:

- `Number`
- `Whitespace`

Technically, `'+'`, `'*'`, `'('`, and `')'` as tokens.  But since they
are just verbatim strings, ANTLR does not require them to have any
specific token types.

Specify an action with the lexical rule of `Whitespace` so that they 
are tossed out by the lexical analyzer.  (See Section 4.1 of reference).

## Design complete grammar

Create a grammar file `Calc.g4` with the start symbol: `expr`.
The grammar should have productions of `expr`.  The bodies
will consist of `expr`, `'+'`, `'*'`, `Number`.

## Generate Lexer and Parser Java classes

Use ANTLR to convert `Calc.g4` to `CalcLexer.java` and `CalcParser.java` using

```
java -jar antlr-4.7-complete.jar -no-visitor -no-listener Calc.g4
```

## Write a parsing program

Write an executable Java program that performs the following:

```java
import org.antlr.v4.runtime.*;

class Program {
  public static void main(String[] args) {
    String inputFilename = args[0];
    
    // 1. Convert inputFilename to CharStream
    // 2. Construct CalcLexer from CharStream
    // 3. Construct CommonTokenStream from CalcLexer
    // 4. Construct CalcParser from CommonTokenStream
    // 5. Perform parsing
  }
}
```

For step 5., make sure you include the parsing call in a `try`-`catch` block so
your program can react to input files that contain invalid source code.

## Compile your code

Make sure you can generate the executable Java class:

```
javac -cp antlr-4.7-complete.jar:$PWD Program.java
```

## Experiment

You can run your program with

```
java -cp antlr-4.7-complete.jar:$PWD Program <input file>
```

- Try out input files with valid inputs.
- Try out input files with invalid inputs.

_You need to gain working knowledge to test if a file part of the language defined by the grammar_.
