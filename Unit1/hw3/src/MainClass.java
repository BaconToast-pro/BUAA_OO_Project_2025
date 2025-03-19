import expr.Expr;
import parser.Definer;
import parser.Lexer;
import parser.Parser;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Definer definer = new Definer();
        for (int i = 0; i < n; i++) {
            // Read in the ordinary function definition.
            String ordinary = scanner.nextLine();
            definer.readOrd(ordinary.replaceAll("[ \t]", ""));
        }
        int m = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < 3 * m; i++) {
            // Read in the recursive function recursive.
            String recursive = scanner.nextLine();
            definer.readRec(recursive.replaceAll("[ \t]", ""));
        }
        String input = scanner.nextLine();

        String input0 = definer.replaceOrd(input.replaceAll("[ \t]", ""));
        String inputExpr = definer.replaceRec(input0);

        // System.out.println(inputExpr);

        Lexer lexer = new Lexer(inputExpr);
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr();

        // System.out.println(expr.toString());

        System.out.println(expr.toString());

        // System.out.println("Runtime: " + (endTime - startTime) + "ms");
    }
}
