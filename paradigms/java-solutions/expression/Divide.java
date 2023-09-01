package expression;

public class Divide extends Abstract {

    public Divide(binaryAndTripleInterface left, binaryAndTripleInterface right) {
        super(left, right, "/");
    }

    @Override
    public int abstractEvaluate(int left, int right) {
        return left/right;
    }
}
