package expression.generic.type;

import java.math.BigInteger;

public class TypeDouble implements GenType<Double> {
    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double negate(Double val) {
        return -1 * val;
    }

    @Override
    public Double parseNumber(String val) {
        try {
            return Double.valueOf(val);
        } catch (NumberFormatException e) {
            throw new ArithmeticException(e.getMessage() + val);
        }
    }
}
