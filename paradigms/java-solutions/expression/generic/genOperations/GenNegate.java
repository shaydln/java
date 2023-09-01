package expression.generic.genOperations;

import expression.generic.type.GenType;

public class GenNegate<T> extends GenAbstractUnary<T> {
    public GenNegate(GenType<T> genType, GenBinaryAndTripleInterface<T> val) {
        super(genType, val);
    }
    @Override
    protected T genAbstractEvaluate(T val) {
        return genType.negate(val);
    }
}
