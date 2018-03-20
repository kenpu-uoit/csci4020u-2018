import org.antlr.v4.runtime.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
      if(args.length == 0) {
        System.out.println("Usage: <input file>");
        System.exit(0);
      }
      CharStream input = CharStreams.fromFileName(args[0]);
      PLLexer lexer = new PLLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      PLParser parser = new PLParser(tokens);
      List<Code> cs = parser.program().cs;
      System.out.println("Code count:" + cs.size());
      /*
      for(Code c: cs) {
          Code result = Code.eval(c);
          System.out.println("=>" + result.number);
      }
      */
    }
}

