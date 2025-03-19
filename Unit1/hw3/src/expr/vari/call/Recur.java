package expr.vari.call;

import expr.Factor;

public class Recur extends Func {
    private final int serNum;

    public Recur(int serNum, Factor factor) {
        super(factor);
        this.serNum = serNum;
    }

    public Recur(int serNum, Factor factor1, Factor factor2) {
        super(factor1, factor2);
        this.serNum = serNum;
    }

    public Recur clone() {
        if (super.getArgNum() == 1) {
            return new Recur(this.serNum, super.getFactor1());
        } else {
            return new Recur(this.serNum, super.getFactor1(), super.getFactor2());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("f{");
        sb.append(serNum);
        sb.append("}(");
        sb.append(super.getFactor1());
        if (super.getArgNum() == 1) {
            sb.append(")");
        } else {
            sb.append(",");
            sb.append(super.getFactor2());
            sb.append(")");
        }
        return sb.toString();
    }
}
