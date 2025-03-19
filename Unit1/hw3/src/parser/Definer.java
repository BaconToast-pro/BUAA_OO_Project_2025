package parser;

import expr.Expr;
import func.Define;
import func.OrdDefine;
import func.RecDefine;

import java.util.HashMap;

public class Definer {
    private Define initDefine0;
    private Define initDefine1;
    private HashMap<Integer, Define> recHashFunc;
    private Define recDefine;
    private Define ordDefineG;
    private Define ordDefineH;

    public Definer() {
        recHashFunc = new HashMap<>();
    }

    public void readRec(String input) {
        int equal = input.indexOf('=');
        String vari = input.substring(5, equal - 1);
        String inputExpr = input.substring(equal + 1);
        String expr = this.replaceOrd(inputExpr);
        if (Character.isDigit(input.charAt(2))) {
            if (vari.length() == 1) {
                if (vari.equals("y")) {
                    vari = "x";
                    expr = expr.replace("y", "x");
                }
                Lexer lexer = new Lexer(expr);
                Parser parser = new Parser(lexer);
                expr = parser.parseExpr().toString();
                if (input.charAt(2) == '0') {
                    initDefine0 = new RecDefine('0', vari, expr);
                } else {
                    initDefine1 = new RecDefine('1', vari, expr);
                }
            } else {
                if (input.charAt(2) == '0') {
                    initDefine0 = new RecDefine('0', vari.substring(0, 1),
                        vari.substring(2, 3), expr);
                } else {
                    initDefine1 = new RecDefine('1', vari.substring(0, 1),
                        vari.substring(2, 3), expr);
                }
            }
        } else {
            if (vari.length() == 1) {
                recDefine = new RecDefine('n', vari, expr);
            } else {
                recDefine = new RecDefine('n', vari.substring(0, 1),
                    vari.substring(2, 3), expr);
            }
        }
        if (initDefine0 != null && initDefine1 != null && recDefine != null) {
            this.calHash();
        }
    }

    private void calHash() {
        for (int i = 2; i <= 3; i++) {
            if (!this.initDefine0.getVari().containsKey(2)) {
                String vari = this.initDefine0.getVari().get(1);
                String func = "f{" + i + "}(" + vari + ")";
                String ans = this.replaceRec(func);
                Define define = new Define(vari, ans);
                this.recHashFunc.put(i, define);
            }
        }
    }

    public void readOrd(String input) {
        int equal = input.indexOf('=');
        String vari = input.substring(2, equal - 1);
        String expr = input.substring(equal + 1);
        if (expr.contains("g") || expr.contains("h")) {
            expr = this.replaceOrd(expr);
        }
        if (vari.length() == 1) {
            if (vari.equals("y")) {
                vari = "x";
                expr = expr.replace("y", "x");
            }
            Lexer lexer = new Lexer(expr);
            Parser parser = new Parser(lexer);
            expr = parser.parseExpr().toString();
            if (input.charAt(0) == 'g') {
                ordDefineG = new OrdDefine("g", vari, expr);
            } else {
                ordDefineH = new OrdDefine("h", vari, expr);
            }
        } else {
            if (input.charAt(0) == 'g') {
                ordDefineG = new OrdDefine("g", vari.substring(0, 1),
                        vari.substring(2, 3), expr);
            } else {
                ordDefineH = new OrdDefine("h", vari.substring(0, 1),
                        vari.substring(2, 3), expr);
            }
        }
    }

    public String replaceRec(String input) {
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
            if (!ans.contains("dx")) {
                Lexer lexer = new Lexer(ans);
                Parser parser = new Parser(lexer);
                Expr expr = parser.parseExpr();
                ans = expr.toString();
            }
            // System.out.println(ans);
        }
        return ans;
    }

    public String replaceOrd(String input) {
        String ans = input;
        while (ans.contains("g") || ans.contains("h")) {
            int start;
            if (ans.contains("g")) {
                start = ans.indexOf('g');
            } else {
                start = ans.indexOf('h');
            }
            int end;
            int stack = 1;
            for (end = start + 2; stack != 0; end++) {
                if (ans.charAt(end) == '(') {
                    stack++;
                } else if (ans.charAt(end) == ')') {
                    stack--;
                }
            }
            String func = ans.substring(start, end);
            String replace = this.call(func);
            if (func.contains("y") && !func.contains("x") && replace.contains("x")) {
                replace = replace.replace("x", "y");
            } else if (func.contains("x") && !func.contains("y") && replace.contains("y")) {
                replace = replace.replace("y", "x");
            }
            ans = ans.replace(func, replace);
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
            ans = this.recDefine.getExpr().replace("n-1", n1);
            ans = ans.replace("n-2", n2);
            String vari1 = this.recDefine.getVari().get(1).equals("x") ? "u" : "v";
            String vari2 = this.recDefine.getVari().get(2).equals("x") ? "u" : "v";
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
                if (recHashFunc.containsKey(serNum)) {
                    String vari = this.recHashFunc.get(serNum).getVari().get(1);
                    ans = this.recHashFunc.get(serNum).getExpr().replace(vari, factor);
                    return "(" + ans + ")";
                } else {
                    String n1 = String.valueOf(serNum - 1);
                    String n2 = String.valueOf(serNum - 2);
                    String vari = this.recDefine.getVari().get(1);
                    ans = this.recDefine.getExpr().replace("n-1", n1);
                    ans = ans.replace("n-2", n2).replace(vari, factor);
                }
            }
        }
        ans = ans.replace("++", "+").replace("--", "+");
        ans = ans.replace("+-", "-").replace("-+", "-");
        if (!ans.contains("dx")) {
            Lexer lexer = new Lexer(ans);
            Parser parser = new Parser(lexer);
            Expr expr = parser.parseExpr();
            ans = expr.toString();
        }
        return '(' + ans + ')';
    }

    public String call2args(String func) {
        final char name = func.charAt(0);
        int pos = 2;
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
        String factor1 =  '(' + func.substring(2, pos) + ')';
        String factor2 = '(' + func.substring(pos + 1, func.length() - 1) + ')';
        if (!factor1.contains("y")) {
            factor1 = '(' + new Parser(new Lexer(factor1)).parseExpr().toString() + ')';
        }
        if (!factor2.contains("y")) {
            factor2 = '(' + new Parser(new Lexer(factor2)).parseExpr().toString() + ')';
        }
        if (name == 'g') {
            String vari1 = this.ordDefineG.getVari().get(1).equals("x") ? "u" : "v";
            String vari2 = this.ordDefineG.getVari().get(2).equals("x") ? "u" : "v";
            ans = this.ordDefineG.getExpr().replace("x", "u").replace("y", "v");
            ans = ans.replace(vari1, factor1);
            ans = ans.replace(vari2, factor2);
        } else {
            String vari1 = this.ordDefineH.getVari().get(1).equals("x") ? "u" : "v";
            String vari2 = this.ordDefineH.getVari().get(2).equals("x") ? "u" : "v";
            ans = this.ordDefineH.getExpr().replace("x", "u").replace("y", "v");
            ans = ans.replace(vari1, factor1);
            ans = ans.replace(vari2, factor2);
        }
        return ans;
    }

    public String call(String func) {
        char name = func.charAt(0);
        String ans;
        if (func.contains(",")) {
            ans = call2args(func);
        } else {
            String factor = '(' + func.substring(2, func.length() - 1) + ')';
            if (name == 'g') {
                String vari = this.ordDefineG.getVari().get(1);
                ans = this.ordDefineG.getExpr().replace(vari, factor);
            } else {
                String vari = this.ordDefineH.getVari().get(1);
                ans = this.ordDefineH.getExpr().replace(vari, factor);
            }
        }
        ans = ans.replace("++", "+").replace("--", "+");
        ans = ans.replace("+-", "-").replace("-+", "-");
        if (!ans.contains("y")) {
            Lexer lexer = new Lexer(ans);
            Parser parser = new Parser(lexer);
            Expr expr = parser.parseExpr();
            ans = expr.toString();
        }
        return '(' + ans + ')';
    }
}
