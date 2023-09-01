package expression.generic.type;

public interface GenType<T> {
    T add(final T left, final T right);
    T subtract(final T left, final T right);
    T divide(final T left, final T right);
    T multiply(final T left, final T right);
    T negate(final T val);
    T parseNumber(final String val);
}
