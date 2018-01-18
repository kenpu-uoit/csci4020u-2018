import org.antlr.v4.runtime.*;

class Main {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName(args[0]);

        CommonTokenStream tokenStream = 
            new CommonTokenStream(new Hello(input));

        tokenStream.fill();

        for(Token t : tokenStream.getTokens()) {
            print(t);
        }
    }
    static void print(Token t) {
        int type = t.getType();
        if(type > 0 && type != Hello.Whitespace) {
            String ruleName = Hello.ruleNames[t.getType()-1];
            System.out.printf("%s: \"%s\"\n", ruleName, t.getText());
        }
    }
}
