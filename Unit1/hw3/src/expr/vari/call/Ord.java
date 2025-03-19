package expr.vari.call;

import expr.Factor;

public class Ord extends Func {
    private final String name;

    public Ord(String name, Factor factor) {
        super(factor);
        this.name = name;
    }

    public Ord(String name, Factor factor1, Factor factor2) {
        super(factor1, factor2);
        this.name = name;
    }

    public Ord clone() {
        if (super.getArgNum() == 1) {
            return new Ord(this.name, super.getFactor1());
        } else {
            return new Ord(this.name, super.getFactor1(), super.getFactor2());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("(");
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
