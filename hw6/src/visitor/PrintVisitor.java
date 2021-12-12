package visitor;

import token.Brace;
import token.Number;
import token.Operation;
import token.Token;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PrintVisitor implements TokenVisitor {
    private void write(Token token) {
        System.out.print(token.toString() + " ");
    }

    @Override
    public void visit(Number token) {
        write(token);
    }

    @Override
    public void visit(Brace token) {
        write(token);
    }

    @Override
    public void visit(Operation token) {
        write(token);
    }
}
