import java.util.*;

class Code {
    public enum Type {
        NUMBER,
        CODE
    }

    Type t;
    double number;
    String op;
    List<Code> children;

    public Code() {
        this.children = new ArrayList<Code>();
    }

    @Override
    public String toString() {
        String s = "";
        s += String.format("t:%s\n", t);
        s += String.format("number:%s\n", number);
        s += String.format("op:%s\n", op);
        s += "children:\n";
        for(Code c: children) {
            s += c.toString();
            s += "----------\n";
        }
        return s;
    }

    public static Code binOp(String op, Code x, Code y) {
        Code c = new Code();
        c.t = Type.CODE;
        c.op = op;
        c.children = new ArrayList<Code>();
        c.children.add(x);
        c.children.add(y);
        return c;
    }

    public static Code data(String text) {
        return data(Double.parseDouble(text));
    }

    public static Code data(double num) {
        Code c = new Code();
        c.t = Type.NUMBER;
        c.number = num;
        return c;
    }

    public static List<Code> append(List<Code> l, Code c) {
        if(l == null) {
            l = new ArrayList<Code>();
        }
        l.add(c);
        return l;
    }

    public static Code binOpEval(char op, Code x, Code y) throws Exception {
        double a = eval(x).number;
        double b = eval(y).number;
        double c = 0.0;
        switch(op) {
            case '+':
                c = a + b;
                break;
            case '-':
                c = a - b;
                break;
            case '*':
                c = a * b;
                break;
            case '/':
                if(b == 0)
                    throw new Exception("Division by zero");
                else
                    c = a / b;
            default:
                throw new Exception("unknown operator");
        }
        return data(c);
    }

    public static Code eval(Code c) throws Exception {
        switch(c.t) {
            case NUMBER:
                return c;
            case CODE:
                if(c.op.equals("*") 
                        || c.op.equals("+")
                        || c.op.equals("-")
                        || c.op.equals("/"))
                    return binOpEval(c.op.charAt(0), c.children.get(0), c.children.get(1));
            default:
                throw new Exception("Unknown type");
        }
    }
}
