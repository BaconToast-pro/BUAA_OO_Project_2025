package expr;

import poly.Mono;
import poly.Poly;

import java.math.BigInteger;
import java.util.ArrayList;

public class Expr implements Factor {
    private final ArrayList<Term> terms;

    public Expr() {
        this.terms = new ArrayList<>();
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }

    public void negate() {
        for (Term term : this.terms) {
            term.setSign("-");
        }
    }

    public SubExpr derive() {
        SubExpr expr = new SubExpr();
        for (Term term : this.terms) {
            Term term1 = new Term();
            term1.addFactors(term.derive());
            expr.addTerm(term1);
        }
        return expr;
    }

    public Poly toPoly() {
        Poly poly = new Poly();
        Mono mono = new Mono(BigInteger.ZERO, BigInteger.ZERO);
        poly.addMono(mono);
        for (Term term : this.terms) {
            poly = poly.add(term.toPoly());
        }
        for (Mono mono0 : poly.getMono()) {
            if (mono0.getCoe().equals(BigInteger.ZERO)) {
                poly.getMono().remove(mono0);
                break;
            }
        }
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
        return this.toPoly().toString();
    }
}
