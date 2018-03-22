import java.util.*;

class Code {
    public enum Type {
        BIN_OP,
        DATA,
        VARIABLE,
        ASSIGN,
        PRINT
    }
    static void p(String s) {
        System.out.print(s);
    }

    public void prettyPrint(String indent) {
        p(indent);
        p("[" + this.t + "] ");
        switch(this.t) {
            case BIN_OP:
                p(this.op + "\n");
                this.children.get(0).prettyPrint(indent + "  ");
                this.children.get(1).prettyPrint(indent + "  ");
                break;
            case DATA:
                p("" + this.number + "\n");
                break;
            case VARIABLE:
                p(this.name + "\n");
                break;
            case ASSIGN:
                p(this.name + " = \n");
                this.children.get(0).prettyPrint(indent + "  ");
                break;
            case PRINT:
                p("PRINT\n");
                this.children.get(0).prettyPrint(indent + "  ");
        }
    }

    Type t;

    // For DATA
    double number;

    // For BIN_OP
    String op;
    List<Code> children;

    // For VARIABLE
    String name;

    // For ASSIGN
    // reuse name and children

    // PRINT
    // reuse children

    public Code(Type t) {
        this.t = t;
        this.children = new ArrayList<Code>();
    }

    public static Code binOp(String op, Code x, Code y) {
        Code c = new Code(Type.BIN_OP);
        c.op = op;
        c.children.add(x);
        c.children.add(y);
        return c;
    }

    public static Code data(String text) {
        return data(Double.parseDouble(text));
    }

    public static Code data(double num) {
        Code c = new Code(Type.DATA);
        c.number = num;
        return c;
    }

    public static Code variable(String name) {
        Code c = new Code(Type.VARIABLE);
        c.name = name;
        return c;
    }

    public static Code assign(String name, Code expr) {
        Code c = new Code(Type.ASSIGN);
        c.name = name;
        c.children.add(expr);
        return c;
    }

    public static Code print(Code expr) {
        Code c = new Code(Type.PRINT);
        c.children.add(expr);
        return c;
    }

    public static List<Code> append(List<Code> l, Code c) {
        if(l == null) {
            l = new ArrayList<Code>();
        }
        l.add(c);
        return l;
    }

}
