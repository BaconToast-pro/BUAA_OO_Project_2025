package expr.vari;

import expr.Factor;
import poly.Poly;

public class Vari implements Factor {
    public Factor derive() {
        return null;
    }

    public Poly toPoly() {
        return new Poly();
    }

    public boolean equals(Factor factor) {
        return false;
    }
}
