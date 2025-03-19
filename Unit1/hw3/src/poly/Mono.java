package poly;

import expr.vari.call.Func;
import expr.vari.Trigo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// Monomial
public class Mono {
    private BigInteger coe;
    private final BigInteger exp;
    private final ArrayList<Trigo> trigo;
    private final ArrayList<Func> func;
    private String toString;

    public Mono(BigInteger coe, BigInteger exp) {
        this.coe = coe;
        this.exp = exp;
        this.trigo = new ArrayList<>();
        this.func = new ArrayList<>();
        this.toString = null;
    }

    public Poly toPoly() {
        Poly poly = new Poly();
        poly.addMono(this);
        return poly;
    }

    public BigInteger getCoe() {
        return this.coe;
    }

    public BigInteger getExp() {
        return this.exp;
    }

    public ArrayList<Trigo> getTrigo() {
        return this.trigo;
    }

    public ArrayList<Func> getFunc() {
        return this.func;
    }

    public void addTrigo(Trigo trigo) {
        this.trigo.add(new Trigo(trigo.getType(), trigo.getFactor(), trigo.getExp()));
    }

    public void addFunc(Func func) {
        this.func.add(func.clone());
    }

    public Mono add(Mono mono) {
        if (!this.exp.equals(mono.getExp())) {
            System.out.println("Error: Mono.add returned null");
            return null;
        } else {
            Mono ans = new Mono(this.coe.add(mono.getCoe()), this.exp);
            for (Trigo trigo : this.trigo) {
                ans.addTrigo(trigo);
            }
            return ans;
        }
    }

    public Mono mul(Mono mono) {
        Mono ans = new Mono(this.coe.multiply(mono.getCoe()), this.exp.add(mono.getExp()));
        for (Trigo trigo1 : this.trigo) {
            ans.addTrigo(trigo1);
        }
        for (Trigo trigo2 : mono.getTrigo()) {
            boolean sign = false;
            for (Trigo trigo1 : ans.getTrigo()) {
                if (trigo1.baseEquals(trigo2)) {
                    Trigo trigoAns = new Trigo(trigo1.getType(), trigo1.getFactor(),
                        trigo1.getExp().add(trigo2.getExp()));
                    ans.getTrigo().remove(trigo1);
                    ans.addTrigo(trigoAns);
                    sign = true;
                    break;
                } else if (trigo1.getType().equals(trigo2.getType())) {
                    Poly poly1 = trigo1.getFactor().toPoly();
                    Poly poly2 = trigo2.getFactor().toPoly();
                    poly1.negate();
                    if (poly1.toString().equals(poly2.toString())) {
                        if (trigo1.getType().equals("sin")) {
                            Trigo trigoAns = new Trigo(trigo1.getType(), trigo1.getFactor(),
                                trigo1.getExp().add(trigo2.getExp()));
                            ans.getTrigo().remove(trigo1);
                            ans.addTrigo(trigoAns);
                            ans.negate();
                            sign = true;
                            break;
                        } else {
                            Trigo trigoAns = new Trigo(trigo1.getType(), trigo1.getFactor(),
                                trigo1.getExp().add(trigo2.getExp()));
                            ans.getTrigo().remove(trigo1);
                            ans.addTrigo(trigoAns);
                            sign = true;
                            break;
                        }
                    }
                }
            }
            if (!sign) {
                ans.addTrigo(trigo2);
            }
        }
        for (Func func1 : this.func) {
            ans.addFunc(func1.clone());
        }
        for (Func func2 : mono.func) {
            ans.addFunc(func2.clone());
        }
        return ans;
    }

    public Mono pow(Mono mono) {
        if (!mono.getTrigo().isEmpty() || !mono.getExp().equals(BigInteger.ZERO)) {
            System.out.println("Error: Mono.pow returned null");
            return null;
        } else {
            int exp = mono.getExp().intValue();
            Mono ans = new Mono(this.coe.pow(exp), this.exp.multiply(mono.getExp()));
            for (Trigo trigo : this.getTrigo()) {
                Trigo trigoAns = new Trigo(trigo.getType(), trigo.getFactor(),
                    trigo.getExp().multiply(mono.getExp()));
                ans.addTrigo(trigoAns);
            }
            return ans;
        }
    }

    public void negate() {
        this.coe = this.coe.negate();
        this.toString = null;
    }

    public boolean equals(Mono mono) {
        if (this.exp.equals(mono.getExp()) && this.func.isEmpty()) {
            if (this.trigo.size() == mono.getTrigo().size()) {
                if (this.trigo.equals(mono.getTrigo())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Mono clone() {
        Mono ans = new Mono(this.coe, this.exp);
        for (Trigo trigo : this.trigo) {
            ans.addTrigo(trigo.clone());
        }
        for (Func func : this.func) {
            ans.addFunc(func.clone());
        }
        return ans;
    }

    public String trigoRecurToString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> stringTrigo = new ArrayList<>();
        for (Trigo trigo : this.trigo) {
            stringTrigo.add(trigo.toString());
        }
        Collections.sort(stringTrigo);
        Iterator iter = stringTrigo.iterator();
        if (iter.hasNext()) {
            sb.append(iter.next());
            while (iter.hasNext()) {
                sb.append("*");
                sb.append(iter.next());
            }
        }
        if (!this.func.isEmpty()) {
            if (this.trigo.isEmpty()) {
                Iterator iterRecur = this.func.iterator();
                if (iterRecur.hasNext()) {
                    sb.append(iterRecur.next());
                    while (iterRecur.hasNext()) {
                        sb.append("*");
                        sb.append(iterRecur.next());
                    }
                }
            } else {
                for (Func func : this.func) {
                    sb.append("*");
                    sb.append(func.toString());
                }
            }
        }
        return sb.toString();
    }

    public String powerToString() {
        StringBuilder sb = new StringBuilder();
        if (coe.equals(BigInteger.ZERO)) {
            sb.append("");
        } else if (coe.equals(BigInteger.ONE)) {
            if (exp.equals(BigInteger.ZERO)) {
                sb.append("+1");
            } else if (exp.equals(BigInteger.ONE)) {
                sb.append("+x");
            } else {
                sb.append("+x^");
                sb.append(exp);
            }
        } else if (coe.compareTo(BigInteger.ZERO) > 0) {
            sb.append("+");
            sb.append(coe);
            if (exp.equals(BigInteger.ZERO)) {
                sb.append("");
            } else if (exp.equals(BigInteger.ONE)) {
                sb.append("*x");
            } else {
                sb.append("*x^");
                sb.append(exp);
            }
        } else if (coe.equals(BigInteger.ONE.negate())) {
            if (exp.equals(BigInteger.ZERO)) {
                sb.append("-1");
            } else if (exp.equals(BigInteger.ONE)) {
                sb.append("-x");
            } else {
                sb.append("-x^");
                sb.append(exp);
            }
        } else { // coe < -1
            sb.append(coe);
            if (exp.equals(BigInteger.ZERO)) {
                sb.append("");
            } else if (exp.equals(BigInteger.ONE)) {
                sb.append("*x");
            } else {
                sb.append("*x^");
                sb.append(exp);
            }
        }
        return sb.toString();
    }

    private void washTrigo() {
        Iterator iterator = this.trigo.iterator();
        while (iterator.hasNext()) {
            Trigo trigo1 = (Trigo) iterator.next();
            if (trigo1.getExp().equals(BigInteger.ZERO)) {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        if (this.toString == null) {
            StringBuilder sb = new StringBuilder();
            washTrigo();
            if (trigo.isEmpty() && func.isEmpty()) {
                sb.append(this.powerToString());
            } else {
                if (coe.equals(BigInteger.ZERO)) {
                    sb.append("");
                } else if (coe.equals(BigInteger.ONE)) {
                    sb.append("+");
                    if (exp.equals(BigInteger.ZERO)) {
                        sb.append(this.trigoRecurToString());
                    } else if (exp.equals(BigInteger.ONE)) {
                        sb.append("x*");
                        sb.append(this.trigoRecurToString());
                    } else {
                        sb.append("x^");
                        sb.append(exp);
                        sb.append("*");
                        sb.append(this.trigoRecurToString());
                    }
                } else if (coe.equals(BigInteger.ONE.negate())) {
                    sb.append("-");
                    if (exp.equals(BigInteger.ZERO)) {
                        sb.append(this.trigoRecurToString());
                    } else if (exp.equals(BigInteger.ONE)) {
                        sb.append("x*");
                        sb.append(this.trigoRecurToString());
                    } else {
                        sb.append("x^");
                        sb.append(exp);
                        sb.append("*");
                        sb.append(this.trigoRecurToString());
                    }
                } else {
                    if (coe.compareTo(BigInteger.ZERO) > 0) {
                        sb.append("+");
                    }
                    sb.append(coe);
                    sb.append("*");
                    if (exp.equals(BigInteger.ZERO)) {
                        sb.append(this.trigoRecurToString());
                    } else if (exp.equals(BigInteger.ONE)) {
                        sb.append("x*");
                        sb.append(this.trigoRecurToString());
                    } else {
                        sb.append("x^");
                        sb.append(exp);
                        sb.append("*");
                        sb.append(this.trigoRecurToString());
                    }
                }
            }
            this.toString = sb.toString();
            return this.toString;
        } else {
            return this.toString;
        }
    }
}
