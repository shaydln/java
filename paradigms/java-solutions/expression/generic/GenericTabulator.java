package expression.generic;

import expression.exceptions.InvalidExpressionException;
import expression.generic.genOperations.GenBinaryAndTripleInterface;
import expression.generic.type.*;

public class GenericTabulator implements Tabulator {
    private static <T> Object[][][] countTabulator(String expression, int x1, int x2, int y1, int y2, int z1, int z2, GenType<T> genType) throws InvalidExpressionException {
        final Object[][][] answer = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        final GenBinaryAndTripleInterface<T> genExpression = new GenExpressionParser<>(genType).parse(expression);
        for (int i = 0; i < x2 - x1 + 1; i++)
            for (int j = 0; j < y2 - y1 + 1; j++)
                for (int t = 0; t < z2 - z1 + 1; t++)
                    try {
                        answer[i][j][t] = genExpression.evaluate(genType.parseNumber(String.valueOf(x1 + i)), genType.parseNumber(String.valueOf(y1 + j)), genType.parseNumber(String.valueOf(z1 + t)));
                    } catch (ArithmeticException e) {
                        answer[i][j][t] = null;
                    }
        return answer;
    }
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        GenType<?> genType;
        switch (mode) {
            case "bi" -> genType = new TypeBigInteger();
            case "i" -> genType = new GenOverflow();
            case "d" -> genType = new TypeDouble();
            case "s" -> genType = new TypeShort();
            case "f" -> genType = new TypeFloat();
            case "u" -> genType = new TypeInt();
            default -> throw new AssertionError("this mode is incorrect");
        }
        return countTabulator(expression, x1, x2, y1, y2, z1, z2, genType);
    }
}
