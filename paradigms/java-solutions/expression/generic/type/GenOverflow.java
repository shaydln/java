package expression.generic.type;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverFlowException;


public class GenOverflow implements GenType<Integer>{
    @Override
    public Integer add(Integer left, Integer right) {
        if ((left < 0 && right < 0 && left + right >= 0)
                || (left > 0 && right > 0 && left + right <= 0)) {
            throw new OverFlowException("overflow");
        } else {
            return left + right;
        }
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        if ((left < 0 && right > 0 && left - right > 0) || (left >= 0 && right < 0 && left - right <= 0) ||
                (left < 0 && right > 0 && left - right >= 0) || (left > 0 && right < 0 && left - right < 0)) {
            throw new OverFlowException("overflow");
        } else {
            return left - right;
        }
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (right == 0 || (left == Integer.MIN_VALUE && right == -1)) {
            throw new DivisionByZeroException("division by zero");
        } else {
            return left / right;
        }
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        if ((right != 0 && left * right / right != left)
                || (left != 0 && left * right / left != right)) {
            throw new OverFlowException("overflow");
        } else {
            return left * right;
        }
    }

    @Override
    public Integer negate(Integer val) {
        if (val == Integer.MIN_VALUE) {
            throw new OverFlowException("overflow");
        } else {
            return -1 * val;
        }
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
