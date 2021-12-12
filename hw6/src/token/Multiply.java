package token;

public class Multiply implements Operation {
    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int apply(int a, int b) {
        return a * b;
    }

    @Override
    public String toString() {
        return "*";
    }
}
