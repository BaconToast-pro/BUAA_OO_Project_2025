package expr;
// change
import java.util.ArrayList;
import java.util.Iterator;

public class Expr implements Factor {
    private final ArrayList<Term> terms;
    private final ArrayList<String> operators;

    public Expr() {
        this.terms = new ArrayList<>();
        this.operators = new ArrayList<>();
    }

    public void addTerm(Term term) {
        this.terms.add(term);
    }

    public void addOperator(String operator) {
        this.operators.add(operator);
    }

    public String toString() {
        Iterator<Term> iterTerm = terms.iterator();
        Iterator<String> iterOperator = operators.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(iterTerm.next().toString());
        if (iterTerm.hasNext()) {
            do {
                sb.append(" ");
                sb.append(iterTerm.next());
                sb.append(" ");
                sb.append(iterOperator.next());
            } while (iterTerm.hasNext());
        }
        return sb.toString();
    }
}
