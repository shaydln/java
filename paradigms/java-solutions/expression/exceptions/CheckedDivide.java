package expression.exceptions;

import expression.Abstract;
import expression.binaryAndTripleInterface;

public class CheckedDivide extends Abstract {
    public CheckedDivide(binaryAndTripleInterface vx, binaryAndTripleInterface vy) {
        super(vx, vy, "/");
    }

    @Override
    protected int abstractEvaluate(int left, int right) {
        if (right == 0 || (left == Integer.MIN_VALUE && right == -1)) {
            throw new DivisionByZeroException("division by zero");
        } else {
            return left / right;
        }
    }
}
