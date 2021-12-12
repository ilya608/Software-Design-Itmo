package visitor;

import token.*;
import token.Number;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private final List<Token> parsed;
    private final Stack<Token> operations;

    public ParserVisitor() {
        parsed = new ArrayList<>();
        operations = new Stack<>();
    }

    @Override
    public void visit(Number token) {
        parsed.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token instanceof OpenBrace) {
            operations.push(token);
        } else {
            while (!operations.isEmpty() && !(operations.peek() instanceof OpenBrace)) {
                Token top = operations.pop();
                parsed.add(top);
            }
            if (operations.isEmpty()) {
                throw new RuntimeException("Parse error: incorrect brace sequence");
            }
            operations.pop();
        }
    }

    @Override
    public void visit(Operation token) {
        while (!operations.isEmpty() && operations.peek() instanceof Operation
                && ((Operation) operations.peek()).priority() <= token.priority()) {
            parsed.add(operations.pop());
        }
        operations.push(token);
    }

    public List<Token> getParsed() {
        while (!operations.isEmpty()) {
            if (operations.peek() instanceof OpenBrace) {
                throw new RuntimeException("Parse error: unexpected open brace");
            }
            parsed.add(operations.pop());
        }
        return parsed;
    }
}
