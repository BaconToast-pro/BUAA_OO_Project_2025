package expr;

import poly.Mono;
import poly.Poly;

import java.math.BigInteger;

public class SubExpr extends Expr implements Factor {
    private BigInteger exp;

    public SubExpr() {
        super();
        this.exp = BigInteger.ONE;
    }

    public void setExp(BigInteger exp) {
        this.exp = exp;
    }

    public Poly toPoly() {
        Poly poly = new Poly();
        Mono mono = new Mono(BigInteger.ONE, BigInteger.ZERO);
        poly.addMono(mono);
        Poly mulPoly = super.toPoly();
        int exp = this.exp.intValue();
        for (int i = 0; i < exp; i++) {
            poly = poly.mul(mulPoly);
        }
        return poly;
    }

    public boolean equals(SubExpr subExpr) {
        if (this.toString().equals(subExpr.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String expr = this.toPoly().toString();
        if (!expr.contains("+") && !expr.contains("-") && !expr.contains("*")) {
            sb.append(this.toPoly().toString());
        } else if (this.toPoly().getMono().size() == 1 &&
            this.toPoly().getMono().get(0).getCoe().equals(BigInteger.ZERO) &&
            this.toPoly().getMono().get(0).getFunc().isEmpty()) {
            sb.append(this.toPoly().toString());
        } else {
            sb.append("(");
            sb.append(this.toPoly().toString());
            sb.append(")");
        }
        return sb.toString();
    }
}
