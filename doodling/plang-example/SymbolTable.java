import java.util.*;

class SymbolTable extends HashMap<String, Code> {
    private SymbolTable parent = null;

    public SymbolTable() {
        this(null);
    }
    public SymbolTable(SymbolTable parent) {
        super();
        this.parent = parent;
    }

    public Code resolve(String name) throws Exception {
        Code c = this.get(name);

        if(c == null) {
            if(parent == null) {
                throw new Exception("Undefined: " + name);
            } else {
                return parent.resolve(name);
            }
        } else {
            return c;
        }
    }

    public void prettyPrint() {
        System.out.println("+-------------------------");
        for(String k : this.keySet()) {
            System.out.printf("| %s: ...\n", k);
        }
        System.out.println("+-------------------------");
    }
}
