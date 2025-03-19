package expr.vari;

import poly.Mono;
import poly.Poly;

import java.math.BigInteger;

public class Power extends Vari {
    private final BigInteger exp;
    private final String type;

    public Power(BigInteger exp) {
        this.exp = exp;
        this.type = "x";
    }

    public Power(BigInteger exp, String type) {
        this.exp = exp;
        this.type = type;
    }

    @Override
    public Poly toPoly() {
        Poly poly = super.toPoly();
        BigInteger coe = BigInteger.ONE;
        BigInteger exp = this.exp;
        poly.addMono(new Mono(coe, exp));
        return poly;
    }

    public boolean equals(Power power) {
        if (this.toString().equals(power.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (exp.equals(BigInteger.ZERO)) {
            sb.append("1");
        } else {
            sb.append("x");
            if (!exp.equals(BigInteger.ONE)) {
                sb.append("^");
                sb.append(exp);
            }
        }
        return sb.toString();
    }
}
