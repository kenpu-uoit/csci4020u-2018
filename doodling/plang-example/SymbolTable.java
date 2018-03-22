import java.util.*;

class SymbolTable extends HashMap<String, Code> {
    public Code resolve(String name) {
        return this.get(name);
    }
}
