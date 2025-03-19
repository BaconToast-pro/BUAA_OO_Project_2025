import expr.Expr;
import expr.Term;
import expr.Power;
import expr.Factor;
import expr.Variate;
import expr.Number;

import java.math.BigInteger;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Expr parseExpr() {
        Expr expr = new Expr();
        expr.addTerm(parseTerm());

        while (lexer.peek().equals("+") || lexer.peek().equals("-")) {
            expr.addOperator(lexer.peek());
            lexer.next();
            expr.addTerm(parseTerm());
        }
        return expr;
    }

    public Term parseTerm() {
        Term term = new Term();
        term.addPower(parsePower());

        while (lexer.peek().equals("*")) {
            term.addOperator(lexer.peek());
            lexer.next();
            term.addPower(parsePower());
        }
        return term;
    }

    public Power parsePower() {
        Power power = new Power();
        power.addFactor(parseFactor());

        while (lexer.peek().equals("^")) {
            lexer.next();
            power.addFactor(parseFactor());
        }
        return power;
    }

    public Factor parseFactor() {
        if (lexer.peek().equals("(")) {
            lexer.next();
            Factor expr = parseExpr();
            lexer.next();
            return expr;
        } else if (lexer.peek().charAt(0) == 'x') {
            int exp = (lexer.peek().length() == 1) ? 1 : (lexer.peek().charAt(2) - '0');
            lexer.next();
            return new Variate(exp);
        } else {
            BigInteger num = new BigInteger(lexer.peek());
            lexer.next();
            return new Number(num);
        }
    }
}
