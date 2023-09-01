package expression.exceptions;

import expression.binaryAndTripleInterface;

public class CheckedNegate implements binaryAndTripleInterface {

    private binaryAndTripleInterface val;

    public CheckedNegate(binaryAndTripleInterface val) {
        this.val = val;
    }


    public int evaluate(int x, int y, int z) {
        if (val.evaluate(x, y, z) == Integer.MIN_VALUE) {
            throw new OverFlowException("overflow");
        } else {
            return -1 * val.evaluate(x, y, z);
        }
    }


    @Override
    public String toString() {
        return "-(" + val.toString() + ")";
    }
}