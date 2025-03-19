package parser;

import expr.Const;
import expr.Expr;
import expr.Factor;
import expr.SubExpr;
import expr.Term;
import expr.vari.Power;
import expr.vari.Recur;
import expr.vari.Trigo;
import expr.vari.Vari;

import java.math.BigInteger;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public Expr parseExpr() {
        Expr expr = new Expr();
        if (!lexer.peek().equals("+") && !lexer.peek().equals("-")) {
            Term term = parseTerm(); // "+" or endOfExpr
            term.setSign("+");
            expr.addTerm(term);
        }
        while (lexer.peek().equals("+") || lexer.peek().equals("-")) {
            String sign = lexer.peek();
            lexer.next(); // Term
            Term term = parseTerm(); // "+" or endOfExpr
            term.setSign(sign);
            expr.addTerm(term);
        }
        return expr;
    }

    public Term parseTerm() {
        Term term = new Term();
        if (lexer.peek().equals("+") || lexer.peek().equals("-")) {
            term.setSign(lexer.peek());
            lexer.next(); // Factor
        }
        do {
            if (lexer.peek().equals("*")) {
                lexer.next(); // Factor
            }
            Factor factor = parseFactor(); // "*" or endOfTerm
            term.addFactors(factor);
        } while (lexer.peek().equals("*"));
        return term;
    }

    public Factor parseFactor() {
        if (lexer.peek().equals("x") || lexer.peek().equals("y") ||
            lexer.peek().equals("sin") || lexer.peek().equals("cos") ||
            lexer.peek().equals("f")) { // Vari
            return parseVari(); // endOfVari
        } else if (lexer.peek().equals("+") || lexer.peek().equals("-") ||
            Character.isDigit(lexer.peek().charAt(0))) { // Const
            if (lexer.peek().equals("+") || lexer.peek().equals("-")) {
                String sign = lexer.peek();
                lexer.next(); // Number
                String number = lexer.peek();
                lexer.next(); // endOfConst
                return new Const(sign, number);
            }
            String number = lexer.peek();
            lexer.next(); // endOfConst
            return new Const("+", number);
        } else if (lexer.peek().equals("(")) { // SubExpr
            return parseSubExpr();
        } else {
            System.out.println("Error: parser.Parser.parseFactor returned null");
            return null;
        }
    }

    public Vari parseVari() {
        if (lexer.peek().equals("x") || lexer.peek().equals("y")) { // Power
            lexer.next(); // "^" or endOfPower
            BigInteger exp = parseExp(); // endOfPower
            return new Power(exp);
        } else if (lexer.peek().equals("sin") || lexer.peek().equals("cos")) { // Trigo
            final String type = lexer.peek(); // "sin" or "cos"
            lexer.next(); // "("
            lexer.next(); // Factor
            Factor factor = parseFactor(); // ")"
            lexer.next(); // "^" or endOfTrigo
            BigInteger exp = parseExp(); // endOfTrigo
            return new Trigo(type, factor, exp);
        } else if (lexer.peek().equals("f")) { // Recur
            lexer.next(); // "{"
            lexer.next(); // serNum
            final int serNum = Integer.parseInt(lexer.peek());
            lexer.next(); // "}"
            lexer.next(); // "("
            lexer.next(); // Factor
            Factor factor1 = parseFactor(); // "," or ")"
            if (lexer.peek().equals(",")) {
                lexer.next(); // Factor
                Factor factor2 = parseFactor(); // ")"
                lexer.next(); // endOfRecur
                return new Recur(serNum, factor1, factor2);
            }
            lexer.next(); // endOfRecur
            return new Recur(serNum, factor1);
        } else {
            System.out.println("Error: parser.Parser.parseVari returned null");
            return null;
        }
    }

    public SubExpr parseSubExpr() {
        SubExpr subExpr = new SubExpr();
        lexer.next(); // "+" or Term
        if (!lexer.peek().equals("+") && !lexer.peek().equals("-")) {
            Term term = parseTerm(); // "+" or ")"
            term.setSign("+");
            subExpr.addTerm(term);
        }
        while (lexer.peek().equals("+") || lexer.peek().equals("-")) {
            String sign = lexer.peek();
            lexer.next(); // Term
            Term term = parseTerm(); // "+" or ")"
            term.setSign(sign);
            subExpr.addTerm(term);
        }
        lexer.next(); // "^" or endOfSubExpr
        if (lexer.peek().equals("^")) {
            BigInteger exp = parseExp(); // endOfSubExpr
            subExpr.setExp(exp);
        } else {
            subExpr.setExp(BigInteger.ONE);
        }
        return subExpr;
    }

    public BigInteger parseExp() {
        if (lexer.peek().equals("^")) {
            lexer.next(); // "+" or Number
            if (lexer.peek().equals("+")) {
                lexer.next(); // Number
            }
            BigInteger exp = new BigInteger(lexer.peek());
            lexer.next(); // endOfPower
            return exp;
        } else { // endOfPower
            BigInteger exp = new BigInteger("1");
            return exp;
        }
    }
}
