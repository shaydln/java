package expression.generic.genOperations;

public class GenConst<T> implements GenBinaryAndTripleInterface<T> {
    private final T c;
    public GenConst(T c) {
        this.c = c;
    }
    @Override
    public T evaluate(T x, T y, T z) {
        return c;
    }
    @Override
    public T evaluate(T x) {
        return c;
    }
}
