package expression.generic.genOperations;

import expression.generic.type.GenType;

public class GenVariable<T> implements GenBinaryAndTripleInterface<T> {
    private final String letter;
    public GenVariable(String letter) {
        this.letter = letter;
    }
    @Override
    public T evaluate(T x, T y, T z) {
        if (letter.equals("x")) {
            return x;
        } else if (letter.equals("y")) {
            return y;
        } else if (letter.equals("z")) {
            return z;
        } else {
            return null;
        }
    }
    @Override
    public T evaluate(T x) {
        return x;
    }
}
