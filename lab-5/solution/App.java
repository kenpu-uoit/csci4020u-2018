import org.antlr.v4.runtime.*;
import static java.lang.System.out;

class App {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName(args[0]);
        L5Lexer lex = new L5Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        L5Parser parser = new L5Parser(tokens);

        parser.start();
    }
}
