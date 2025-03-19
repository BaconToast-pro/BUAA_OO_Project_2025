package func;

public class RecDefine extends Define {
    private final char serNum;

    public RecDefine(char serNum, String vari1, String expr) {
        super(vari1, expr);
        this.serNum = serNum;
    }

    public RecDefine(char serNum, String vari1, String vari2, String expr) {
        super(vari1, vari2, expr);
        this.serNum = serNum;
    }
}
