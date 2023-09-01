package expression.exceptions;

import expression.Abstract;
import expression.binaryAndTripleInterface;

public class CheckedMultiply extends Abstract {
    public CheckedMultiply(binaryAndTripleInterface vx, binaryAndTripleInterface vy) {
        super(vx, vy, "*");
    }

    @Override
    protected int abstractEvaluate(int left, int right) {
        int res = left * right;
        if ((right != 0 && res / right != left)
                || (left != 0 && res / left != right)) {
            throw new OverFlowException("overflow");
        } else {
            return left * right;
        }
    }
}
