package parser;

public class Lexer {
    private final String input;
    private int pos = 0;
    private String token;

    public Lexer(String input) {
        this.input = input;
        this.next();
    }

    private String getNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }

    // Trigonometric Function
    private String getTrigo() {
        StringBuilder sb = new StringBuilder();
        while (Character.isAlphabetic(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }

    // Recurrence Function
    private String getRecur() {
        StringBuilder sb = new StringBuilder();
        while (input.charAt(pos) != '(') {
            sb.append(input.charAt(pos));
            pos++;
        }
        int stack = 1;
        while (stack != 0) {
            if (input.charAt(pos) == '(') {
                stack++;
            } else if (input.charAt(pos) == ')') {
                stack--;
            }
            sb.append(input.charAt(pos));
            pos++;
        }
        pos++;
        return sb.toString();
    }

    public void next() {
        if (pos == input.length()) {
            return;
        }

        char c = input.charAt(pos);
        if (Character.isDigit(c)) {
            token = getNumber();
        } else if (c == 'x') {
            pos++;
            token = "x";
        } else if (c == 'y') {
            pos++;
            token = "y";
        } else if (c == 's' || c == 'c') {
            token = getTrigo();
        } else if (c == 'f') {
            pos++;
            token = "f";
        } else if (c == 'g' || c == 'h') {
            pos++;
            token = String.valueOf(c);
        } else if (c == 'd') {
            pos += 2;
            token = "dx";
        } else {
            pos++;
            token = String.valueOf(c);
        }
    }

    public String peek() {
        return this.token;
    }
}
