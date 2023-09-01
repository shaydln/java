package expression;

public class Add extends Abstract{
    public Add (binaryAndTripleInterface left, binaryAndTripleInterface right) {
        super(left, right, "+");
    }

    @Override
    public int abstractEvaluate(int left, int right) {
        return left + right;
    }


}
