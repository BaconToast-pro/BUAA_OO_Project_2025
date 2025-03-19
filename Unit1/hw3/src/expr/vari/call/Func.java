package expr.vari.call;

import expr.Factor;
import expr.vari.Vari;
import poly.Mono;
import poly.Poly;

import java.math.BigInteger;

public class Func extends Vari {
    private final int argNum;
    private final Factor factor1;
    private final Factor factor2;

    public Func(Factor factor) {
        this.argNum = 1;
        this.factor1 = factor;
        this.factor2 = null;
    }

    public Func(Factor factor1, Factor factor2) {
        this.argNum = 2;
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    protected int getArgNum() {
        return this.argNum;
    }

    protected Factor getFactor1() {
        return this.factor1;
    }

    protected Factor getFactor2() {
        return this.factor2;
    }

    public Func clone() {
        return new Func(this.factor1, this.factor2);
    }

    public Poly toPoly() {
        Poly poly = super.toPoly();
        Mono mono = new Mono(BigInteger.ONE, BigInteger.ZERO);
        mono.addFunc(this);
        poly.addMono(mono);
        return poly;
    }

    public String toString() {
        return null;
    }
}
