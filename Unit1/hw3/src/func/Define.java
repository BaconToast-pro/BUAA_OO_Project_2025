package func;

import java.util.HashMap;

public class Define {
    private final int argNum;
    private final HashMap<Integer, String> vari;
    private String expr;

    public Define(String vari1, String expr) {
        this.argNum = 1;
        this.vari = new HashMap<>();
        vari.put(1, vari1);
        this.expr = expr;
    }

    public Define(String vari1, String vari2, String expr) {
        this.argNum = 2;
        this.vari = new HashMap<>();
        vari.put(1, vari1);
        vari.put(2, vari2);
        this.expr = expr;
    }

    public HashMap<Integer, String> getVari() {
        return this.vari;
    }

    public String getExpr() {
        return this.expr;
    }
}
