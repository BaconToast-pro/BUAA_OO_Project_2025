package expr.vari;

import expr.Factor;
import poly.Mono;
import poly.Poly;

import java.math.BigInteger;

public class Trigo extends Vari {
    private final String type;
    private final Factor factor;
    private final BigInteger exp;

    public Trigo(String type, Factor factor, BigInteger exp) {
        this.type = type;
        this.factor = factor;
        this.exp = exp;
    }

    public String getType() {
        return this.type;
    }

    public Factor getFactor() {
        return this.factor;
    }

    public BigInteger getExp() {
        return this.exp;
    }

    public boolean baseEquals(Trigo trigo) {
        return this.type.equals(trigo.getType()) &&
            this.getFactor().toString().equals(trigo.getFactor().toString());
    }

    public boolean equals(Trigo trigo) {
        return this.baseEquals(trigo) && this.exp.equals(trigo.getExp());
    }

    @Override
    public Poly toPoly() {
        Poly poly = super.toPoly();
        Mono mono = new Mono(BigInteger.ONE, BigInteger.ZERO);
        mono.addTrigo(this);
        poly.addMono(mono);
        return poly;
    }

    public Trigo clone() {
        Trigo ans = new Trigo(this.type, this.factor, this.exp);
        return ans;
    }

    public String toString() {
        if (exp.equals(BigInteger.ZERO)) {
            return "1";
        }
        if (factor.toString().equals("0")) {
            return type.equals("sin") ? "0" : "1";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append("(");
        sb.append(factor);
        sb.append(")");
        if (!exp.equals(BigInteger.ONE)) {
            sb.append("^");
            sb.append(exp);
        }
        return sb.toString();
    }
}
