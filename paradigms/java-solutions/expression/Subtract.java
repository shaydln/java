package expression;

public class Subtract extends Abstract{
    public Subtract(binaryAndTripleInterface left, binaryAndTripleInterface right) {
        super(left, right, "-");
    }

    @Override
    public int abstractEvaluate(int left, int right) {
        return left - right;
    }

}
