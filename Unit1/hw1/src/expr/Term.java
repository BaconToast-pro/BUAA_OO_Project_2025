package expr;

import java.util.ArrayList;
import java.util.Iterator;

public class Term implements Factor {
    private final ArrayList<Power> powers;
    private final ArrayList<String> operators;

    public Term() {
        this.powers = new ArrayList<>();
        this.operators = new ArrayList<>();
    }

    public void addPower(Power power) {
        this.powers.add(power);
    }

    public void addOperator(String operator) {
        this.operators.add(operator);
    }

    public String toString() {
        Iterator<Power> iterPower = powers.iterator();
        Iterator<String> iterOperator = operators.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(iterPower.next().toString());
        if (iterPower.hasNext()) {
            do {
                sb.append(" ");
                sb.append(iterPower.next().toString());
                sb.append(" ");
                sb.append(iterOperator.next());
            } while (iterPower.hasNext());
        }
        return sb.toString();
    }
}
