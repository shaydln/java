package expression;

public class Negate implements binaryAndTripleInterface {
    binaryAndTripleInterface val;
    public Negate(binaryAndTripleInterface binaryAndTripleInterface) {
        val = binaryAndTripleInterface;
    }

    @Override
    public String toString() {
            return "-(" + val.toString() + ")";
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * val.evaluate(x, y, z);
    }
}
