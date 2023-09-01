package expression.exceptions;

import expression.Abstract;
import expression.binaryAndTripleInterface;

public class CheckedSubtract extends Abstract {
    public CheckedSubtract(binaryAndTripleInterface left, binaryAndTripleInterface right) {
        super(left, right, "-");
    }

    @Override
    protected int abstractEvaluate(int left, int right) {
        if ((left < 0 && right > 0 && left - right > 0) || (left >= 0 && right < 0 && left - right <= 0) ||
                (left < 0 && right > 0 && left - right >= 0) || (left > 0 && right < 0 && left - right < 0)) {
            throw new OverFlowException("overflow");
        } else {
            return left - right;
        }
    }
}
