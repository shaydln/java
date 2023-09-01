package expression.exceptions;

import expression.Abstract;
import expression.binaryAndTripleInterface;

public class CheckedAdd extends Abstract {
    public CheckedAdd(binaryAndTripleInterface vx, binaryAndTripleInterface vy) {
        super(vx, vy, "+");
    }

    // INT.MIN <= vx + vy <= INT.MAX

    @Override
    protected int abstractEvaluate(int left, int right) {
        if ((left < 0 && right < 0 && left + right >= 0)
                || (left > 0 && right > 0 && left + right <= 0)) {
            throw new OverFlowException("overflow");
        }
        return left + right;
    }
}
