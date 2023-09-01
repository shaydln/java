package expression.generic.type;

public class TypeInt implements GenType<Integer>{
    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        return left / right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer negate(Integer val) {
        return -1 * val;
    }


    @Override
    public Integer parseNumber(String val) {
        try {
            return Integer.valueOf(val);
        } catch (NumberFormatException e) {
            throw new ArithmeticException(e.getMessage() + val);
        }
    }

}
