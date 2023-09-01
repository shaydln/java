package expression.generic.genOperations;

import expression.generic.type.GenType;

public class GenSubtract<T> extends GenAbstractBin<T> {

    public GenSubtract(GenType<T> genType, GenBinaryAndTripleInterface<T> left, GenBinaryAndTripleInterface<T> right) {
        super(genType, left, right);
    }

    @Override
    protected T genAbstractEvaluate(T left, T right) {
        return genType.subtract(left, right);
    }
}
