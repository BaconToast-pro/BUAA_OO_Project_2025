package poly;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Polynomial {
    private HashMap<BigInteger, Monomial> monomials;

    public Polynomial(BigInteger exp, Monomial mono) {
        this.monomials = new HashMap<>();
        monomials.put(exp, mono);
    }

    public void operation(Polynomial poly, Character op) {
        Iterator<Map.Entry<BigInteger, Monomial>> iter = poly.peek().entrySet().iterator();
        switch (op) {
            case '+':
                while (iter.hasNext()) {
                    Map.Entry<BigInteger, Monomial> entry = iter.next();
                    if (monomials.containsKey(entry.getKey())) {
                        this.monomials.get(entry.getKey()).add(entry.getValue());
                    } else {
                        this.monomials.put(entry.getKey(), entry.getValue());
                    }
                }
                break;
            case '-':
                while (iter.hasNext()) {
                    Map.Entry<BigInteger, Monomial> entry = iter.next();
                    if (monomials.containsKey(entry.getKey())) {
                        this.monomials.get(entry.getKey()).sub(entry.getValue());
                    } else {
                        Monomial empty = new Monomial(BigInteger.valueOf(0), entry.getKey());
                        empty.sub(entry.getValue());
                        this.monomials.put(entry.getKey(), empty);
                    }
                }
                break;
            case '*':
                Monomial repMono = new Monomial(BigInteger.ZERO, BigInteger.ZERO);
                Polynomial replace = new Polynomial(BigInteger.ZERO, repMono);
                while (iter.hasNext()) {
                    Map.Entry<BigInteger, Monomial> entry = iter.next();
                    Iterator<Map.Entry<BigInteger, Monomial>> subIter =
                        monomials.entrySet().iterator();
                    while (subIter.hasNext()) {
                        Map.Entry<BigInteger, Monomial> subEntry = subIter.next();
                        Monomial monoA = new Monomial(entry.getValue().getCoefficient(),
                            entry.getKey());
                        BigInteger monoBCoe = subEntry.getValue().getCoefficient();
                        Monomial monoAns = monoA.mul(new Monomial(monoBCoe, subEntry.getKey()));
                        Polynomial tmp = new Polynomial(monoAns.getExponent(), monoAns);
                        replace.operation(tmp, '+');
                    }
                }
                this.monomials = replace.peek();
                break;
            case '^':
                int exp = poly.peek().get(BigInteger.ZERO).getCoefficient().intValue();
                Monomial tmp = new Monomial(BigInteger.ONE, BigInteger.ZERO);
                Polynomial answer = new Polynomial(BigInteger.ZERO, tmp);
                while (exp > 0) {
                    answer.operation(this, '*');
                    exp--;
                }
                this.monomials = answer.peek();
                break;
            default:
        }
    }

    public HashMap<BigInteger, Monomial> peek() {
        return this.monomials;
    }

    public String toString() {
        Iterator<Map.Entry<BigInteger, Monomial>> iter = this.monomials.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry<BigInteger, Monomial> entry = iter.next();
            sb.append(entry.getValue().toString());
        }
        return sb.toString();
    }
}
