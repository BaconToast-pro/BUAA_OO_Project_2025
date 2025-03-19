package expr;

import java.util.ArrayList;
import java.util.Iterator;

public class Power implements Factor {
    private final ArrayList<Factor> factors;

    public Power() {
        this.factors = new ArrayList<>();
    }

    public void addFactor(Factor factor) {
        this.factors.add(factor);
    }

    public String toString() {
        Iterator<Factor> iterFactor = factors.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(iterFactor.next().toString());
        if (iterFactor.hasNext()) {
            do {
                sb.append(" ");
                sb.append(iterFactor.next().toString());
                sb.append(" ");
                sb.append("^");
            } while (iterFactor.hasNext());
        }
        return sb.toString();
    }
}
