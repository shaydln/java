package expression.generic.type;


public class TypeShort implements GenType<Short>{
    @Override
    public Short add(Short left, Short right) {
        return (short) (left + right);
    }

    @Override
    public Short subtract(Short left, Short right) {
        return (short) (left - right);
    }

    @Override
    public Short divide(Short left, Short right) {
        return (short) (left / right);
    }

    @Override
    public Short multiply(Short left, Short right) {
        return (short) (left * right);
    }

    @Override
    public Short negate(Short val) {
        return (short) (-1 * val);
    }

    @Override
    public Short parseNumber(String val) {
        try {
            return (short) Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new ArithmeticException(e.getMessage() + val);
        }
    }

}
