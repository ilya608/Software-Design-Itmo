import token.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Token> tokens = readTokens();

            List<Token> polishTokens = convertTokensToPolishNotation(tokens);

            printTokens(polishTokens);

            int result = calculateResult(polishTokens);

            System.out.println("\nРезультат: " + result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int calculateResult(List<Token> polishTokens) {
        CalcVisitor calcVisitor = new CalcVisitor();
        polishTokens.forEach(t -> t.accept(calcVisitor));
        return calcVisitor.getResult();
    }

    private static void printTokens(List<Token> tokens) {
        PrintVisitor printVisitor = new PrintVisitor();
        System.out.print("Польская нотация: ");
        tokens.forEach(t -> t.accept(printVisitor));
    }

    private static List<Token> convertTokensToPolishNotation(List<Token> tokens) {
        ParserVisitor parserVisitor = new ParserVisitor();
        tokens.forEach(t -> t.accept(parserVisitor));
        List<Token> parsed = parserVisitor.getParsed();
        return parsed;
    }

    private static List<Token> readTokens() {
        Tokenizer tokenizer = new Tokenizer();
        return tokenizer.tokenize();
    }
}
