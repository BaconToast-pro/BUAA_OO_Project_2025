public class Lexer {
    private final String input;
    private int pos = 0;
    private String curToken;

    public Lexer(String input) {
        this.input = input;
        this.next();
    }

    /**
     * Read next Number.
     * @return String with [0-9]
     */
    private String getNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }

    private String getVariate() {
        pos++;
        return "x";
    }

    /**
     * Read next token.
     */
    public void next() {
        if (pos == input.length()) {
            return;
        }

        char c = input.charAt(pos);
        if (Character.isDigit(c)) {
            curToken = getNumber();
        } else if (c == 'x') {
            curToken = getVariate();
        } else {
            pos++;
            curToken = String.valueOf(c);
        }
    }

    /**
     * Get token being read now.
     */
    public String peek() {
        return this.curToken;
    }
}
