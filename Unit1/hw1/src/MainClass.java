import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        // get inputExpr then preprocess it to formal form
        Scanner scanner = new Scanner(System.in);
        String inputExpr = scanner.nextLine();
        Optimizer preProcessor = new Optimizer(inputExpr);
        String infixExpr = preProcessor.preProcess(inputExpr);

        // System.out.println(infixExpr);

        // convert infix expr to suffix expr (remove parenthesis)
        Lexer lexer = new Lexer(infixExpr);
        Parser parser = new Parser(lexer);
        String suffixExpr = parser.parseExpr().toString();


        // System.out.println(suffixExpr);

        // compute polynomial
        Computer computer = new Computer(suffixExpr);

        // System.out.println(computer);
        // optimize answer
        Optimizer optimizer = new Optimizer(computer.toString());
        System.out.println(optimizer.optimize(computer.toString()));
    }
}