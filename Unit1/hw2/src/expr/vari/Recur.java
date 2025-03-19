package expr.vari;

import expr.Factor;
import poly.Mono;
import poly.Poly;

import java.math.BigInteger;

public class Recur extends Vari {
    private final int serNum;
    private final int argNum;
    private final Factor factor1;
    private final Factor factor2;

    public Recur(int serNum, Factor factor) {
        this.serNum = serNum;
        this.argNum = 1;
        this.factor1 = factor;
        this.factor2 = null;
    }

    public Recur(int serNum, Factor factor1, Factor factor2) {
        this.serNum = serNum;
        this.argNum = 2;
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    public Recur clone() {
        if (this.argNum == 1) {
            return new Recur(this.serNum, this.factor1);
        } else {
            return new Recur(this.serNum, this.factor1, this.factor2);
        }
    }

    public Poly toPoly() {
        Poly poly = super.toPoly();
        Mono mono = new Mono(BigInteger.ONE, BigInteger.ZERO);
        mono.addRecur(this);
        poly.addMono(mono);
        return poly;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("f{");
        sb.append(serNum);
        sb.append("}(");
        sb.append(factor1);
        if (argNum == 1) {
            sb.append(")");
        } else {
            sb.append(",");
            sb.append(factor2);
            sb.append(")");
        }
        return sb.toString();
    }
}
