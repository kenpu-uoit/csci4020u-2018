grammar Calc;

@header {
import org.antlr.v4.runtime.*;
}

@members {
public static void main(String[] args) throws Exception {
  if(args.length == 0) {
    System.out.println("Usage: <input file>");
    System.exit(0);
  }
  CharStream input = CharStreams.fromFileName(args[0]);
  CalcLexer lexer = new CalcLexer(input);
  CommonTokenStream tokens = new CommonTokenStream(lexer);
  CalcParser parser = new CalcParser(tokens);
  parser.program();
}
}

program 
    : expr+ EOF
    ;

expr
    : expr OP expr
    | '(' expr ')'
    | NUM
    ;

NUM : [0-9]+ ('.' [0-9]+)? ;
OP  : '+' | '-' | '*' | '/' ;
WS  : [ \t\r\n] -> skip;

