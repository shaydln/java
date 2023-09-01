package expression.generic.type;

import expression.exceptions.InvalidExpressionException;
import expression.generic.genOperations.GenBinaryAndTripleInterface;

public interface GenTripleParser<T> {
    GenBinaryAndTripleInterface<T> parse(String s) throws InvalidExpressionException;
}
