package parser;

import expr.Expr;
import expr.RecurDefine;

public class Definer {
    private RecurDefine initDefine0;
    private RecurDefine initDefine1;
    private RecurDefine recurDefine;

    public Definer() {
    }

    public void read(String input) {
        int equal = input.indexOf('=');
        String vari = input.substring(5, equal - 1);
        String expr = input.substring(equal + 1);
        if (Character.isDigit(input.charAt(2))) {
            if (vari.length() == 1) {
                if (input.charAt(2) == '0') {
                    initDefine0 = new RecurDefine('0', vari, expr);
                } else {
                    initDefine1 = new RecurDefine('1', vari, expr);
                }
            } else {
                if (input.charAt(2) == '0') {
                    initDefine0 = new RecurDefine('0', vari.substring(0, 1),
                        vari.substring(2, 3), expr);
                } else {
                    initDefine1 = new RecurDefine('1', vari.substring(0, 1),
                        vari.substring(2, 3), expr);
                }
            }
        } else {
            if (vari.length() == 1) {
                recurDefine = new RecurDefine('n', vari, expr);
            } else {
                recurDefine = new RecurDefine('n', vari.substring(0, 1),
                    vari.substring(2, 3), expr);
            }
        }
    }

    public String replace(String input) {
        String ans = input;
        while (ans.contains("f")) {
            int start = ans.indexOf('f');
            int end;
            int stack = 1;
            for (end = start + 5; stack != 0; end++) {
                if (ans.charAt(end) == '(') {
                    stack++;
                } else if (ans.charAt(end) == ')') {
                    stack--;
                }
            }
            String func = ans.substring(start, end);
            ans = ans.replace(func, this.recur(func));
        }
        return ans;
    }

    public String recur2args(String func) {
        final int serNum = func.charAt(2) - '0';
        int pos = 5;
        int stack = 0;
        for (; ; pos++) {
            if (func.charAt(pos) == '(') {
                stack++;
            } else if (func.charAt(pos) == ')') {
                stack--;
            } else if (func.charAt(pos) == ',') {
                if (stack == 0) {
                    break;
                }
            }
        }
        String ans;
        String factor1 =  '(' + func.substring(5, pos) + ')';
        String factor2 = '(' + func.substring(pos + 1, func.length() - 1) + ')';
        if (!factor1.contains("y")) {
            factor1 = '(' + new Parser(new Lexer(factor1)).parseExpr().toString() + ')';
        }
        if (!factor2.contains("y")) {
            factor2 = '(' + new Parser(new Lexer(factor2)).parseExpr().toString() + ')';
        }
        if (serNum == 0) {
            String vari1 = this.initDefine0.getVari().get(1).equals("x") ? "u" : "v";
            String vari2 = this.initDefine0.getVari().get(2).equals("x") ? "u" : "v";
            ans = this.initDefine0.getExpr().replace("x", "u").replace("y", "v");
            ans = ans.replace(vari1, factor1);
            ans = ans.replace(vari2, factor2);
        } else if (serNum == 1) {
            String vari1 = this.initDefine1.getVari().get(1).equals("x") ? "u" : "v";
            String vari2 = this.initDefine1.getVari().get(2).equals("x") ? "u" : "v";
            ans = this.initDefine1.getExpr().replace("x", "u").replace("y", "v");
            ans = ans.replace(vari1, factor1);
            ans = ans.replace(vari2, factor2);
        } else {
            String n1 = String.valueOf(serNum - 1);
            String n2 = String.valueOf(serNum - 2);
            ans = this.recurDefine.getExpr().replace("n-1", n1);
            ans = ans.replace("n-2", n2);
            String vari1 = this.recurDefine.getVari().get(1).equals("x") ? "u" : "v";
            String vari2 = this.recurDefine.getVari().get(2).equals("x") ? "u" : "v";
            ans = ans.replace("x", "u").replace("y", "v");
            ans = ans.replace(vari1, factor1);
            ans = ans.replace(vari2, factor2);
        }
        return ans;
    }

    public String recur(String func) {
        int serNum = func.charAt(2) - '0';
        String ans;
        if (func.contains(",")) {
            ans = recur2args(func);
        } else {
            String factor = '(' + func.substring(5, func.length() - 1) + ')';
            if (serNum == 0) {
                String vari = this.initDefine0.getVari().get(1);
                ans = this.initDefine0.getExpr().replace(vari, factor);
            } else if (serNum == 1) {
                String vari = this.initDefine1.getVari().get(1);
                ans = this.initDefine1.getExpr().replace(vari, factor);
            } else {
                String n1 = String.valueOf(serNum - 1);
                String n2 = String.valueOf(serNum - 2);
                String vari = this.recurDefine.getVari().get(1);
                ans = this.recurDefine.getExpr().replace("n-1", n1);
                ans = ans.replace("n-2", n2).replace(vari, factor);
            }
        }
        ans = ans.replace("++", "+").replace("--", "+");
        ans = ans.replace("+-", "-").replace("-+", "-");
        Lexer lexer = new Lexer(ans);
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr();

        return '(' + expr.toString() + ')';
    }
}
