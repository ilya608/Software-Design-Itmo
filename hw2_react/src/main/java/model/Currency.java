package model;

public enum Currency {
    USD,
    EUR,
    RUB;

    private double getMultiplier() {
        switch (this) {
            case EUR:
                return 0.91;
            case RUB:
                return 90.04;
            default:
                return 1.0;
        }
    }

    public double getMultiplier(Currency otherCurrency) {
        return otherCurrency.getMultiplier() / getMultiplier();
    }
}
