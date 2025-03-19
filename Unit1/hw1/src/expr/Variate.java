package expr;

public class Variate implements Factor {
    private final int exp;

    public Variate(int exp) {
        this.exp = exp;
    }

    public String toString() {
        if (this.exp > 1) {
            return "x^" + this.exp;
        } else if (this.exp == 1) {
            return "x";
        } else {
            return "0";
        }
    }
}
