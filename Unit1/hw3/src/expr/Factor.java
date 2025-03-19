package expr;

import poly.Poly;

public interface Factor {
    Factor derive();

    Poly toPoly();

    boolean equals(Factor factor);
}
