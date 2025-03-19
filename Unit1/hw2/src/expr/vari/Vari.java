package expr.vari;

import expr.Factor;
import poly.Poly;

public class Vari implements Factor {
    public Poly toPoly() {
        return new Poly();
    }

    @Override
    public boolean equals(Factor factor) {
        return false;
    }
}
