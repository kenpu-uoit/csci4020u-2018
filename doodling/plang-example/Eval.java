class Eval {
    public static Code Do(Code c, SymbolTable ctx) throws Exception {
        String varname;
        Code expr;
        switch(c.t) {
            case BIN_OP:
                return Do_BIN_OP(
                        c.op, 
                        Do(c.children.get(0), ctx), 
                        Do(c.children.get(1), ctx), 
                        ctx);
            case DATA:
                return c;
            case VARIABLE:
                varname = c.name;
                return ctx.resolve(varname);
            case ASSIGN:
                varname = c.name;
                expr = Do(c.children.get(0), ctx);
                ctx.put(varname, expr);
                return null;
            case PRINT:
                expr = Do(c.children.get(0), ctx);
                expr.prettyPrint("-> ");
                return null;
            case COND:
                throw new Exception("Don't know how to eval cond yet");
            case IF:
                throw new Exception("Just wait until Tuesday");
            default:
                throw new Exception("Unknown code type");
        }
    }

    static Code Do_BIN_OP(String op, Code x, Code y, SymbolTable ctx) throws Exception {
        if(op.equals("+"))
            return Code.data(x.number + y.number);
        if(op.equals("*"))
            return Code.data(x.number * y.number);
        if(op.equals("-"))
            return Code.data(x.number - y.number);
        if(op.equals("/"))
            if(y.number == 0)
                throw new Exception("Oops, you did something BAD...");
            else
                return Code.data(x.number - y.number);
        return null;
    }
}
