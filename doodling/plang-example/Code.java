import java.util.*;

class Code {
    public enum Type {
        BIN_OP,
        DATA,
        VARIABLE,
        ASSIGN,
        PRINT,
        COND,
        IF,
        BLOCK,
        DECL,
        FUNCALL
    }
    static void p(String s) {
        System.out.print(s);
    }

    public void prettyPrint(String indent) {
        p(indent);
        if(this.t == Type.DATA) {
            p(this.number + "\n");
            return;
        }
        p("[" + this.t + "] \n");
        p(indent);
        p(" name=" + this.name);
        p(" op=" + this.op);
        p(" number=" + this.number + "\n");
        if( this.paramNames != null ) {
            p("  paramNames:");
            for(String s: this.paramNames)
                p(s + ",");
            p("\n");
        }
        for(int i=0; i < children.size(); i++) {
            Code c = children.get(i);
            c.prettyPrint(indent + i + " ");
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

    // DECL
    List<String> paramNames;


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

    public static Code binary(boolean cond) {
        if(cond)
            return data(1.0);
        else
            return data(-1.0);
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

    public static Code compare(String op, Code x, Code y) {
        Code c = new Code(Type.COND);
        c.op = op;
        c.children.add(x);
        c.children.add(y);
        return c;
    }

    public static Code and(Code x, Code y) {
        return compare("and", x, y);
    }

    public static Code or(Code x, Code y) {
        return compare("or", x, y);
    }

    public static Code not(Code x) {
        return compare("not", x, null);
    }

    public static Code ifElse(Code cond, Code x) {
        Code c = new Code(Type.IF);
        c.children.add(cond);
        c.children.add(x);
        return c;
    }

    public static Code block() {
        Code c = new Code(Type.BLOCK);
        return c;
    }

    public static Code decl(String fname, List<String> paramNames, Code body) {
        Code c = new Code(Type.DECL);
        c.name = fname;
        c.paramNames = paramNames;
        c.children = body.children;
        return c;
    }

    public static Code funCall(String name, List<Code> args) {
        Code c = new Code(Type.FUNCALL);
        c.name = name;
        c.children = args;
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
