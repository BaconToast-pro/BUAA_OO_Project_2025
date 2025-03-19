import poly.Monomial;
import poly.Polynomial;

import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;

public class Computer {
    private final String input;
    private int pos = 0;
    private final Deque<Polynomial> stack;

    public Computer(String input) {
        this.input = input;
        this.stack = new LinkedList<>();
        this.next();
    }

    private String getNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        pos--;
        return sb.toString();
    }

    public void next() {
        if (pos == input.length()) {
            return;
        }

        char c = input.charAt(pos);
        if (Character.isDigit(c)) {
            Monomial tmp = new Monomial(new BigInteger(this.getNumber()), BigInteger.valueOf(0));
            Polynomial ansPoly = new Polynomial(BigInteger.valueOf(0), tmp);
            this.stack.push(ansPoly);
        } else if (c == 'x') {
            Monomial tmp = new Monomial(BigInteger.valueOf(1), BigInteger.valueOf(1));
            Polynomial ansPoly = new Polynomial(BigInteger.valueOf(1), tmp);
            this.stack.push(ansPoly);
        } else if (c == '+') {
            Polynomial polyB = this.stack.pop();
            Polynomial polyA = this.stack.pop();
            polyA.operation(polyB, '+');
            this.stack.push(polyA);
        } else if (c == '-') {
            Polynomial polyB = this.stack.pop();
            Polynomial polyA = this.stack.pop();
            polyA.operation(polyB, '-');
            this.stack.push(polyA);
        } else if (c == '*') {
            Polynomial polyB = this.stack.pop();
            Polynomial polyA = this.stack.pop();
            polyA.operation(polyB, '*');
            this.stack.push(polyA);
        } else if (c == '^') {
            Polynomial polyB = this.stack.pop();
            Polynomial polyA = this.stack.pop();
            polyA.operation(polyB, '^');
            this.stack.push(polyA);
        }

        //System.out.println(this.stack);
        pos++;
        this.next();
    }

    public String toString() {
        if (this.stack.isEmpty()) {
            return "Stack is empty";
        }
        return this.stack.peek().toString();
    }
}
