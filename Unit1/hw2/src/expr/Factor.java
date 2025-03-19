package expr;

import poly.Poly;

public interface Factor {
    Poly toPoly();

    boolean equals(Factor factor);
}
