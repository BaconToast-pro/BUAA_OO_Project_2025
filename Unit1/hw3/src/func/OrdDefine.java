package func;

public class OrdDefine extends Define {
    private final String name;

    public OrdDefine(String name, String vari1, String expr) {
        super(vari1, expr);
        this.name = name;
    }

    public OrdDefine(String name, String vari1, String vari2, String expr) {
        super(vari1, vari2, expr);
        this.name = name;
    }
}
