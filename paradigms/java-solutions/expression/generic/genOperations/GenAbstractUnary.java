package expression.generic.genOperations;

import expression.generic.type.GenType;

public abstract class GenAbstractUnary<T> implements GenBinaryAndTripleInterface<T> {
    protected GenType<T> genType;
    private final GenBinaryAndTripleInterface<T> val;
    public GenAbstractUnary(final GenType<T> genType, final GenBinaryAndTripleInterface<T> val) {
        this.genType = genType;
        this.val = val;
    }
    protected abstract T genAbstractEvaluate(T val);
    public T evaluate(T x) {
        return genAbstractEvaluate(val.evaluate(x));
    }
    public T evaluate(T x, T y, T z) {
        return genAbstractEvaluate(val.evaluate(x, y, z));
    }

}
