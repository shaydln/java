package expression.generic.type;

import java.math.BigInteger;

public class TypeBigInteger implements GenType<BigInteger> {
    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger negate(BigInteger val) {
        return multiply(val, new BigInteger(String.valueOf(-1)));
    }

    @Override
    public BigInteger parseNumber(String val) {
        try {
            return new BigInteger(val);
        } catch (NumberFormatException e) {
            throw new ArithmeticException(e.getMessage() + val);
        }
    }


}
