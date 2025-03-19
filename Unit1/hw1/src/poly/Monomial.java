package poly;

import java.math.BigInteger;

public class Monomial {
    private BigInteger coefficient;
    private BigInteger exponent;

    public Monomial(BigInteger coefficient, BigInteger exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public void add(Monomial monomial) {
        this.coefficient = this.coefficient.add(monomial.getCoefficient());
    }

    public void sub(Monomial monomial) {
        this.coefficient = this.coefficient.subtract(monomial.getCoefficient());
    }

    public Monomial mul(Monomial monomial) {
        BigInteger mulCoe = this.coefficient.multiply(monomial.getCoefficient());
        return new Monomial(mulCoe, this.exponent.add(monomial.getExponent()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coefficient.compareTo(BigInteger.ZERO) > 0) {
            sb.append('+');
        }
        if (coefficient.equals(BigInteger.ZERO)) {
            return "";
        } else if (coefficient.equals(BigInteger.ONE)) {
            if (exponent.equals(BigInteger.ZERO)) {
                sb.append("1");
            } else if (exponent.equals(BigInteger.ONE)) {
                sb.append("x");
            } else {
                sb.append("x^");
                sb.append(exponent);
            }
        } else {
            sb.append(coefficient);
            if (exponent.equals(BigInteger.ZERO)) {
                sb.append("");
            } else if (exponent.equals(BigInteger.ONE)) {
                sb.append("*x");
            } else {
                sb.append("*x^");
                sb.append(exponent);
            }
        }
        return sb.toString();
    }

    public BigInteger getCoefficient() {
        return this.coefficient;
    }

    public BigInteger getExponent() {
        return this.exponent;
    }
}
