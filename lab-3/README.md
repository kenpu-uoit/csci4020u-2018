# ANTLR Grammar

In this lab, we are to experiment with the ANTLR library to author context free grammars.


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

## Design the grammar

