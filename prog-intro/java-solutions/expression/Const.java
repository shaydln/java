package expression;

public class Const implements binaryAndTripleInterface{
    private final int c;

    public Const(int c) {
        this.c = c;
    }

    public String toString() {
        return Integer.toString(c);
    }

    @Override
    public int evaluate(int x) {
        return c;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return c;
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Const obj)) {
            return false;
        }
        return c == obj.c;
    }

    @Override
    public int hashCode() {
        return c;
    }

}
