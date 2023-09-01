package expression.generic.type;


import expression.exceptions.InvalidExpressionException;
import expression.generic.genOperations.*;

import java.util.ArrayList;
import java.util.List;

public class GenExpressionParser<T> implements GenTripleParser<T> {
    private final GenType<T> genType;
    public GenExpressionParser(final GenType<T> genType) {
        this.genType = genType;
    }

    private static boolean closeMinus = false;
    private static boolean closeCount = false;
    private static boolean closeSet = false;
    private static boolean closeClear = false;

    @Override
    public GenBinaryAndTripleInterface<T> parse(String expression) throws InvalidExpressionException {
//        System.err.println(expression);
        return expressionParse(new TokenOrder(Tokenize(expression)));
    }


    public enum TOKENS {
        OPEN_BRACE, CLOSE_BRACE, ADD, SUBTRACT, MULT, DIV, VAR_X, VAR_Y, VAR_Z, NUM, COUNT, SET, CLEAR, END
    }


    public static class TOKEN {
        TOKENS symbols;
        String val;
        public TOKEN(TOKENS symbols, String val) {
            this.symbols = symbols;
            this.val = val;
        }
    }

    public static class TokenOrder {
        public int pos;
        public List<TOKEN> token;

        public TokenOrder(List<TOKEN> token) {
            this.token = token;
        }

        public TOKEN next() {
            return token.get(pos++);
        }
    }

    public static void changeSomeValue () {
        GenExpressionParser.closeMinus = false;
        GenExpressionParser.closeCount= false;
        GenExpressionParser.closeSet = false;
        GenExpressionParser.closeClear = false;
    }

    public static List<TOKEN> Tokenize(String expText) throws InvalidExpressionException {
        ArrayList<TOKEN> token = new ArrayList<>();
        int pos = 0;
        int bracketBalance = 0;
        while (pos < expText.length()) {
            char c = expText.charAt(pos);
            switch (c) {
                case 'x':
                    token.add(new TOKEN(TOKENS.VAR_X, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    continue;
                case 'y':
                    token.add(new TOKEN(TOKENS.VAR_Y, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    continue;
                case 'z':
                    token.add(new TOKEN(TOKENS.VAR_Z, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    continue;
                case '+':
                    token.add(new TOKEN(TOKENS.ADD, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    continue;
                case '-':
                    token.add(new TOKEN(TOKENS.SUBTRACT, String.valueOf(c)));
                    pos++;
                    changeSomeValue();
                    closeMinus = true;
                    continue;
                case '(':
                    token.add(new TOKEN(TOKENS.OPEN_BRACE, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    bracketBalance++;
                    continue;
                case ')':
                    token.add(new TOKEN(TOKENS.CLOSE_BRACE, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    bracketBalance--;
                    if (bracketBalance < 0) {
                        throw new InvalidExpressionException("Brackets are in wrong order");
                    }
                    continue;
                case '*':
                    token.add(new TOKEN(TOKENS.MULT, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    continue;
                case '/':
                    token.add(new TOKEN(TOKENS.DIV, String.valueOf(c)));
                    changeSomeValue();
                    pos++;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        StringBuilder sb = new StringBuilder();
                        while (c >= '0' && c <= '9') {
                            sb.append(c);
                            if (++pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        }
                        if (token.size() == 1 && token.get(0).symbols == TOKENS.SUBTRACT ||
                                closeMinus && token.size() >= 2 &&
                                        (token.get(token.size()-2).symbols == TOKENS.OPEN_BRACE
                                                ||token.get(token.size()-2).symbols == TOKENS.SUBTRACT
                                                ||token.get(token.size()-2).symbols == TOKENS.ADD
                                                ||token.get(token.size()-2).symbols == TOKENS.DIV
                                                ||token.get(token.size()-2).symbols == TOKENS.MULT
                                                ||token.get(token.size()-2).symbols == TOKENS.COUNT
                                                ||token.get(token.size()-2).symbols == TOKENS.SET
                                                ||token.get(token.size()-2).symbols == TOKENS.CLEAR
                                        )) {
                            token.remove(token.size()-1);
                            token.add(new TOKEN(TOKENS.NUM, "-" + sb.toString()));
                        } else {
                            token.add(new TOKEN(TOKENS.NUM, sb.toString()));
                        }
                        if (closeCount || closeSet || closeClear) {
                            throw new InvalidExpressionException("there is no space between function and argument");
                        }

                    } else if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                        closeMinus = false;
                        StringBuilder sb = new StringBuilder();
                        while (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                            sb.append(c);
                            if (++pos >= expText.length()) {
                                break;
                            }
                            c = expText.charAt(pos);
                        }

                        if (sb.length() == 5 && sb.charAt(0) == 'c' && sb.charAt(1) == 'o' && sb.charAt(2) == 'u' && sb.charAt(3) == 'n' && sb.charAt(4) == 't') {
                            token.add(new TOKEN(TOKENS.COUNT, ""));
                            changeSomeValue();
                            closeCount = true;
                        } else if (sb.length() == 3 && sb.charAt(0) == 's' &&  sb.charAt(1) == 'e' &&  sb.charAt(2) == 't') {
                            token.add(new TOKEN(TOKENS.SET, ""));
                            changeSomeValue();
                            closeSet = true;
                        } else if (sb.length() == 5 && sb.charAt(0) == 'c' && sb.charAt(1) == 'l' && sb.charAt(2) == 'e' && sb.charAt(3) == 'a' && sb.charAt(4) == 'r') {
                            token.add(new TOKEN(TOKENS.CLEAR, ""));
                            changeSomeValue();
                            closeClear = true;
                        } else {
                            throw new InvalidExpressionException("This function doesn't exist");
                        }
                    } else {
                        pos++;
                        changeSomeValue();
                        if (!Character.isWhitespace(c)) {
                            throw new InvalidExpressionException("This symbol doesn't exist");
                        }
                    }
            }
        }
        token.add(new TOKEN(TOKENS.END, ""));
        return token;
    }

    public GenBinaryAndTripleInterface<T> expressionParse(TokenOrder token) throws InvalidExpressionException {
        return addSubtract(token);
    }

    public GenBinaryAndTripleInterface<T> addSubtract(TokenOrder token) throws InvalidExpressionException {
        GenBinaryAndTripleInterface<T> value = multiplyDivide(token);
        while (true) {
            TOKEN TOKEN = token.next();
            switch (TOKEN.symbols) {
//                case SET:
//                    value = new GenSet<>(genType, value, addSubtract(token));
//                    break;
//                case CLEAR:
//                    value = new GenClear<>(genType, value, addSubtract(token));
//                    break;
                case ADD:
                    value = new GenAdd<>(genType, value, multiplyDivide(token));
                    break;
                case SUBTRACT:
                    value = new GenSubtract<>(genType, value, multiplyDivide(token));
                    break;
                case END:
                case CLOSE_BRACE:
                    token.pos--;
                    return value;
                default:
                    throw new InvalidExpressionException("Token mismatch");

            }
        }
    }

    public GenBinaryAndTripleInterface<T> multiplyDivide(TokenOrder token) throws InvalidExpressionException {
        GenBinaryAndTripleInterface<T> value = constVarCount(token);
        while (true) {
            TOKEN TOKEN = token.next();
            switch (TOKEN.symbols) {
                case DIV:
                    value = new GenDivide<>(genType, value, constVarCount(token));
                    break;
                case MULT:
                    value = new GenMultiply<>(genType, value, constVarCount(token));
                    break;
                case END:
                case CLOSE_BRACE:
                case ADD:
                case SUBTRACT:
                case SET:
                case CLEAR:
                    token.pos--;
                    return value;
                default:
                    throw new InvalidExpressionException("Token mismatch");

            }
        }
    }

    public GenBinaryAndTripleInterface<T> constVarCount(TokenOrder token) throws InvalidExpressionException {
        TOKEN TOKEN = token.next();
        switch (TOKEN.symbols) {
            case VAR_X:
                return new GenVariable<>("x");
            case VAR_Y:
                return new GenVariable<>("y");
            case VAR_Z:
                return new GenVariable<>("z");
//            case COUNT:
//                return new GenCount<>(genType, constVarCount(token));
            case SUBTRACT:
                GenBinaryAndTripleInterface<T> value = constVarCount(token);
                return new GenNegate<>(genType, value);
            case NUM:
                return new GenConst<>(genType.parseNumber(TOKEN.val));
            case OPEN_BRACE:
                value = addSubtract(token);
                TOKEN = token.next();
                if (TOKEN.symbols != TOKENS.CLOSE_BRACE) {
                    throw new InvalidExpressionException("Token mismatch");
                } else {
                    return value;
                }
            default:
                throw new InvalidExpressionException("Token mismatch");
        }
    }

}
