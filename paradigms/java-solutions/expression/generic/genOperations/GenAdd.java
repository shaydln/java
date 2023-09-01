package expression.generic.genOperations;

import expression.generic.type.GenType;

public class GenAdd<T> extends GenAbstractBin<T> {
    public GenAdd(GenType<T> genType, GenBinaryAndTripleInterface<T> left, GenBinaryAndTripleInterface<T> right) {
        super(genType, left, right);
    }

    @Override
    protected T genAbstractEvaluate(T left, T right) {
        return genType.add(left, right);
    }
}
