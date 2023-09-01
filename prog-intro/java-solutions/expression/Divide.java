package expression;

public class Divide extends Abstract implements Expression{

    public Divide(binaryAndTripleInterface left, binaryAndTripleInterface right) {
        super(left, right, "/");
    }

    @Override
    int abstractEvaluate(int left, int right) {
        return left/right;
    }
}
