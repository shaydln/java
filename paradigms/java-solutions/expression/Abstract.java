package expression;

import java.util.Objects;

public abstract class Abstract implements binaryAndTripleInterface{
    binaryAndTripleInterface left;
    binaryAndTripleInterface right;
    String sign;

    public Abstract (binaryAndTripleInterface left, binaryAndTripleInterface right, String sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }

    public String toString() {
        return "(" + left + " " + sign + " " + right + ")";
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return abstractEvaluate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract int abstractEvaluate(int left, int right);

    @Override
    public String toMiniString() {
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Abstract obj)) {
            return false;
        }
        return left.equals(obj.left) && right.equals(obj.right) && sign.equals(obj.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, sign);
    }
}
