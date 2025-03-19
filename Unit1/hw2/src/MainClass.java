import expr.Expr;
import parser.Definer;
import parser.Lexer;
import parser.Parser;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        // long startTime = System.currentTimeMillis();

        Definer definer = new Definer();
        for (int i = 0; i < 3 * n; i++) {
            // Read in the recursive function definition.
            String definition = scanner.nextLine();
            definer.read(definition.replaceAll("[ \t]", ""));
        }
        String input = scanner.nextLine();

        String inputExpr = definer.replace(input.replaceAll("[ \t]", ""));

        // System.out.println(inputExpr);

        Lexer lexer = new Lexer(inputExpr);
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr();

        // System.out.println(expr.toString());

        System.out.println(expr.toString());

        // long endTime = System.currentTimeMillis();
        // System.out.println("Runtime: " + (endTime - startTime) + "ms");
    }
}
