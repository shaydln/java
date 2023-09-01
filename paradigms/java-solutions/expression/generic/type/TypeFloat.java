package expression.generic.type;


public class TypeFloat implements GenType<Float>{
    @Override
    public Float add(Float left, Float right) {
        return left + right;
    }

    @Override
    public Float subtract(Float left, Float right) {
        return left - right;
    }

    @Override
    public Float divide(Float left, Float right) {
        return left / right;
    }

    @Override
    public Float multiply(Float left, Float right) {
        return left * right;
    }

    @Override
    public Float negate(Float val) {
        return -1 * val;
    }

    @Override
    public Float parseNumber(String val) {
        try {
            return Float.valueOf(val);
        } catch (NumberFormatException e) {
            throw new ArithmeticException(e.getMessage() + val);
        }
    }
}
