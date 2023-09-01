package expression;

public class Count implements binaryAndTripleInterface {
    binaryAndTripleInterface c;
    public Count(binaryAndTripleInterface c) {
        this.c = c;
    }

    public int evaluate(int val) {
        return Integer.bitCount(val);
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.bitCount(c.evaluate(x, y, z));
    }

    public String toString() {
        return "count(" + c.toString() + ")";
    }

}
