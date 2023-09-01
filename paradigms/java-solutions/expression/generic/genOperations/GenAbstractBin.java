package expression.generic.genOperations;

import expression.generic.type.GenType;

public abstract class GenAbstractBin<T> implements GenBinaryAndTripleInterface<T> {
    protected GenType<T> genType;
    private final GenBinaryAndTripleInterface<T> left;
    private final GenBinaryAndTripleInterface<T> right;
    public GenAbstractBin(final GenType<T> genType, final GenBinaryAndTripleInterface<T> left, final GenBinaryAndTripleInterface<T> right) {
        this.genType = genType;
        this.left = left;
        this.right = right;
    }
    protected abstract T genAbstractEvaluate(T left, T right);
    public T evaluate(T x) {
        return genAbstractEvaluate(left.evaluate(x), right.evaluate(x));
    }
    public T evaluate(T x, T y, T z) {
        return genAbstractEvaluate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }
}
