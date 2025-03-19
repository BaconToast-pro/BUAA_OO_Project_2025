package expr;

import poly.Mono;
import poly.Poly;

import java.math.BigInteger;

public class Const implements Factor {
    private final BigInteger number;
    private final String sign;

    public Const(String sign, String number) {
        this.sign = new String(sign);
        this.number = new BigInteger(number);
    }

    public Poly toPoly() {
        Poly poly = new Poly();
        BigInteger coe = (sign.equals("+") ? number : number.negate());
        BigInteger exp = BigInteger.ZERO;
        poly.addMono(new Mono(coe, exp));
        return poly;
    }

    public boolean equals(Factor factor) {
        if (this.toString().equals(factor.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (sign.equals("-")) {
            sb.append(sign);
        }
        sb.append(number);
        return sb.toString();
    }
}
