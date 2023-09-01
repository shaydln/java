package expression.generic.genOperations;

public interface GenBinaryAndTripleInterface<T> {
    T evaluate(T x, T y, T z);
    T evaluate(T x);
}
