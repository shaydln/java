package expression;

public class Multiply extends Abstract{

    public Multiply(binaryAndTripleInterface left, binaryAndTripleInterface right) {
        super(left, right, "*");
    }

    @Override
    public int abstractEvaluate(int left, int right) {
        return right*left;
    }
}
