package expr;

import parser.Lexer;
import parser.Parser;

import java.util.HashMap;

public class RecurDefine {
    private final char serNum;
    private final int argNum;
    private final HashMap<Integer, String> vari;
    private String expr;

    public RecurDefine(char serNum, String vari1, String expr) {
        String vari0 = vari1;
        String expr0 = expr;
        if (vari0.equals("y")) {
            vari0 = "x";
            expr0 = expr0.replace("y", "x");
        }
        this.serNum = serNum;
        this.argNum = 1;
        this.vari = new HashMap<>();
        vari.put(1, vari0);
        if (Character.isDigit(serNum)) {
            Lexer lexer = new Lexer(expr0);
            Parser parser = new Parser(lexer);
            this.expr = parser.parseExpr().toString();
        } else {
            this.expr = expr0;
        }
    }

    public RecurDefine(char serNum, String vari1, String vari2, String expr) {
        this.serNum = serNum;
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
