class Eval {
    public static Code Do(Code c, SymbolTable ctx) throws Exception {
        String varname;
        Code expr;
        if(c == null) {
            c.prettyPrint("**");
            throw new Exception("Empty code fragment");
        }
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
                ctx.put(c.name, Do(c.children.get(0), ctx));
                return null;
            case PRINT:
                expr = Do(c.children.get(0), ctx);
                expr.prettyPrint("-> ");
                return null;
            case COND:
                return Do_COND(c, ctx);
            case IF:
                return Do_IF(c, ctx);
            case BLOCK:
                return Do_BLOCK(c, ctx);
            case DECL:
                ctx.put(c.name, c);
                return null;
            case FUNCALL:
                return Do_FUNCALL(c, ctx);
            default:
                throw new Exception("Unknown code type:" + c.t);
        }
    }

    static Code Do_BLOCK(Code c, SymbolTable ctx) throws Exception {
        for(int i=0; i < c.children.size(); i++) {
            c.children.set(i, Do(c.children.get(i), ctx));
        }
        return c;
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
                return Code.data(x.number / y.number);
        return null;
    }

    static Code Do_COND(Code c, SymbolTable ctx) throws Exception {
        if(c.op.equals("==")) {

            Code x = Do(c.children.get(0), ctx);
            Code y = Do(c.children.get(1), ctx);
            return Code.binary(x.number == y.number);

        } else if(c.op.equals("<")) {

            Code x = Do(c.children.get(0), ctx);
            Code y = Do(c.children.get(1), ctx);
            return Code.binary(x.number < y.number);

        } else if(c.op.equals(">")) {

            Code x = Do(c.children.get(0), ctx);
            Code y = Do(c.children.get(1), ctx);
            return Code.binary(x.number > y.number);

        } else if(c.op.equals("and")) {

            Code x = Do(c.children.get(0), ctx);
            Code y = Do(c.children.get(1), ctx);
            return Code.binary(x.number > 0 &&  y.number > 0);

        } else if(c.op.equals("or")) {

            Code x = Do(c.children.get(0), ctx);
            Code y = Do(c.children.get(1), ctx);
            return Code.binary(x.number > 0 ||  y.number > 0);

        } else if(c.op.equals("not")) {

            Code x = Do(c.children.get(0), ctx);
            return Code.binary(!(x.number > 0));

        } else {
            throw new Exception("Unknown condition operator");
        }
    }

    static Code Do_IF(Code c, SymbolTable ctx) throws Exception {
        Code cond = Do(c.children.get(0), ctx);
        Code block = null;

        if(cond.number > 0) {
            block = Do(c.children.get(1), ctx);
        } else {
            if(c.children.size() == 3) {
                block = Do(c.children.get(2), ctx);
            }
        }

        if(block != null) {
            int n = block.children.size();
            return block.children.get(n-1);
        }

        return null;
    }

    static Code Do_FUNCALL(Code c, SymbolTable ctx) throws Exception {
        SymbolTable myCtx = new SymbolTable(ctx);

        String funName = c.name;
        Code fun = ctx.resolve(funName);

        // Make sure that the arity agrees
        if(fun.paramNames.size() != c.children.size()) {
            throw new Exception(funName + " arity error");
        }

        // Bind the parameters to arguments in the new ctx
        for(int i=0; i < fun.paramNames.size(); i++) {
            String parameter = fun.paramNames.get(i);
            Code argument = Do(c.children.get(i), ctx);
            myCtx.put(parameter, argument);
        }

        Code result = null;
        for(Code body : fun.children) {
            result = Do(body, myCtx);
        }

        return result;
    }
}
