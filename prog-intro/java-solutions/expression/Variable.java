package expression;

import java.util.Objects;

public class Variable implements binaryAndTripleInterface{
    String var;
    public Variable(String x) {
        this.var = x;
    }

    public String toString() {
        return var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable obj))  {
            return false;
        }
        return Objects.equals(var, obj.var);
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;
        };
    }
}
