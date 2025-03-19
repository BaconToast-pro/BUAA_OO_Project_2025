package expr;

import poly.Mono;
import poly.Poly;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class Term {
    private final ArrayList<Factor> factors;
    private String sign;

    public Term() {
        this.factors = new ArrayList<>();
        this.sign = "+";
    }

    public void addFactors(Factor factor) {
        this.factors.add(factor);
    }

    public void setSign(String sign) {
        if (this.sign.equals(sign)) {
            this.sign = "+";
        } else {
            this.sign = "-";
        }
    }

    public Poly toPoly() {
        Poly poly = new Poly();
        Mono mono = new Mono(BigInteger.ONE, BigInteger.ZERO);
        poly.addMono(mono);
        for (Factor factor : this.factors) {
            poly = poly.mul(factor.toPoly());
        }
        if (this.sign.equals("-")) {
            poly.negate();
        }
        return poly;
    }

    public String toString() {
        Iterator<Factor> iterFactor = factors.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(sign);
        sb.append(iterFactor.next().toString());
        if (iterFactor.hasNext()) {
            do {
                sb.append("*");
                sb.append(iterFactor.next().toString());
            } while (iterFactor.hasNext());
        }
        return sb.toString();
    }
}
