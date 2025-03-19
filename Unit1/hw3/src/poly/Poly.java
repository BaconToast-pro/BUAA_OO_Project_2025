package poly;

import java.util.ArrayList;
import java.util.Iterator;

// Polynomial
public class Poly {
    private final ArrayList<Mono> mono;
    private String toString;

    public Poly() {
        this.mono = new ArrayList<>();
        this.toString = null;
    }

    public void addMono(Mono mono) {
        this.mono.add(mono.clone());
    }

    public ArrayList<Mono> getMono() {
        return this.mono;
    }

    public Poly add(Poly poly) {
        Poly ans = new Poly();
        for (Mono mono : this.getMono()) {
            ans.addMono(mono);
        }
        for (Mono mono : poly.getMono()) {
            boolean sign = false;
            for (Mono monoAns : ans.getMono()) {
                if (monoAns.getExp().equals(mono.getExp())) {
                    if (monoAns.getFunc().isEmpty() && mono.getFunc().isEmpty()) {
                        if (monoAns.getTrigo().size() == 1 && mono.getTrigo().size() == 1) {
                            if (monoAns.getTrigo().get(0).getType().equals("sin") &&
                                mono.getTrigo().get(0).getType().equals("sin")) {
                                Poly poly1 = monoAns.getTrigo().get(0).getFactor().toPoly();
                                Poly poly2 = mono.getTrigo().get(0).getFactor().toPoly();
                                poly1.negate();
                                if (poly1.toString().equals(poly2.toString())) {
                                    ans.getMono().remove(monoAns);
                                    mono.negate();
                                    ans.addMono(monoAns.add(mono));
                                    sign = true;
                                    break;
                                }
                            } else if (monoAns.getTrigo().get(0).getType().equals("cos") &&
                                mono.getTrigo().get(0).getType().equals("cos")) {
                                Poly poly1 = monoAns.getTrigo().get(0).getFactor().toPoly();
                                Poly poly2 = mono.getTrigo().get(0).getFactor().toPoly();
                                poly1.negate();
                                if (poly1.toString().equals(poly2.toString())) {
                                    ans.getMono().remove(monoAns);
                                    ans.addMono(monoAns.add(mono));
                                    sign = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (monoAns.trigoRecurToString().isEmpty() &&
                        mono.trigoRecurToString().isEmpty()) {
                        ans.getMono().remove(monoAns);
                        ans.addMono(monoAns.add(mono));
                        sign = true;
                        break;
                    }
                }
            }
            if (!sign) {
                ans.addMono(mono);
            }
        }
        return ans;
    }

    public Poly mul(Poly poly) {
        Poly ans = new Poly();
        for (Mono mono : this.mono) {
            for (Mono monoAns : poly.getMono()) {
                ans = ans.add(mono.mul(monoAns).toPoly());
            }
        }
        return ans;
    }

    public void negate() {
        for (Mono mono : this.mono) {
            mono.negate();
        }
    }

    @Override
    public String toString() {
        if (this.toString == null) {
            StringBuilder sb = new StringBuilder();
            Iterator<Mono> iter = mono.iterator();
            if (iter.hasNext()) {
                do {
                    sb.append(iter.next().toString());
                } while (iter.hasNext());
            }
            if (sb.toString().isEmpty()) {
                this.toString = "0";
            } else if (sb.toString().charAt(0) == '+') {
                this.toString = sb.toString().substring(1);
            } else {
                this.toString =  sb.toString();
            }
            return this.toString;
        } else {
            return this.toString;
        }
    }
}
