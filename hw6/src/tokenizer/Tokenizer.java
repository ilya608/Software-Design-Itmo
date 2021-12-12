package tokenizer;

import token.*;
import token.Number;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

public class Tokenizer {
    private final InputStream inputStream;
    private char current;
    private boolean needPrev;

    public Tokenizer() {
        this.inputStream = System.in;
        needPrev = false;
    }

    public List<Token> tokenize() {
        List<Token> res = new ArrayList<>();

        Token token = nextToken();
        while (token != null) {
            res.add(token);
            token = nextToken();
        }

        return res;
    }

    private Token nextToken() {
        char symbol = getNext();
        switch (symbol) {
            case '(':
                return new OpenBrace();
            case ')':
                return new CloseBrace();
            case '+':
                return new Add();
            case '-':
                return new Subtract();
            case '*':
                return new Multiply();
            case '/':
                return new Divide();
            default:
                if (symbol == (char) -1) {
                    return null;
                }
                if (Character.isWhitespace(symbol)) {
                    return nextToken();
                }
                if (Character.isDigit(symbol)) {
                    return getNumberToken();
                }
                throw new RuntimeException("Parse error: unknown symbol");
        }
    }

    private Number getNumberToken() {
        int number = getInt();
        prev();
        return new Number(number);
    }

    private int getInt() {
        int result = 0;
        while (Character.isDigit(current)) {
            result = result * 10 + current - '0';
            getNext();
        }
        return result;
    }

    private void prev() {
        if (needPrev) {
            throw new RuntimeException("Can't do 2 steps back");
        }
        needPrev = true;
    }

    private char getNext() {
        if (needPrev) {
            needPrev = false;
            return current;
        }
        try {
            current = (char) inputStream.read();
            return current;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
