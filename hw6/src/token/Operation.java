package token;

import visitor.TokenVisitor;

public interface Operation extends Token {
    @Override
    default void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    int priority();

    int apply(int a, int b);
}
