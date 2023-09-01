import java.util.Arrays;
import java.util.Map;


public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    private final int m;
    private final int n;
    private final int k;
    public TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        try {
            if (!isValid(move)) {
                return Result.LOSE;
            }
            cells[move.getRow()][move.getColumn()] = move.getValue();

            int inDiag1 = 0;
            int inDiag2 = 0;
            int empty = 0;

            for (int u = 0; u < m; u++) {
                int inRow = 0;
                for (int v = 0; v < n; v++) {
                    if (cells[u][v] == turn) {
                        inRow++;
                        if (inRow == k) {
                            return Result.WIN;
                        }
                    } else {
                        inRow = 0;
                    }
                    if (cells[u][v] == Cell.E) {
                        empty++;
                    }
                }
                if (inRow == k) {
                    return Result.WIN;
                }
            }

            for (int u = 0; u < n; u++) {
                int inColumn = 0;
                for (int v = 0; v < m; v++) {
                    if (cells[v][u] == turn) {
                        inColumn++;
                        if (inColumn == k) {
                            return Result.WIN;
                        }
                    } else {
                        inColumn = 0;
                    }
                }
            }

        //справа снизу диагональ 1
        for (int i = 0; move.getRow()+i < m && move.getColumn()+i < n; i++) {
            if (cells[move.getRow()+i][move.getColumn()+i] == turn) {
                inDiag1++;
                if (inDiag1 == k) {
                    return Result.WIN;
                }
            } else {
                break;
            }
        }
        //слева сверху диагональ 1
        for (int i = 0; move.getRow()-i >= 0 && move.getColumn()-i >= 0; i++) {
            if (cells[move.getRow()-i][move.getColumn()-i] == turn) {
                inDiag1++;
                if (inDiag1 >= k+1) {
                    return Result.WIN;
                }
            } else {
                break;
            }
        }

        //справа сверху диагональ 2
        for (int i = 0; move.getRow()-i >= 0 && move.getColumn()+i < n; i++) {
            if (cells[move.getRow()-i][move.getColumn()+i] == turn) {
                inDiag2++;
                if (inDiag2 == k) {
                    return Result.WIN;
                }
            } else {
                break;
            }
        }
        //слева сверху диагональ 2
        for (int i = 0; move.getRow()+i < m && move.getColumn()-i >= 0; i++) {
            if (cells[move.getRow()+i][move.getColumn()-i] == turn) {
                inDiag2++;
                if (inDiag2 >= k+1) {
                    return Result.WIN;
                }
            } else {
                break;
            }
        }
            if (empty == 0) {
                return Result.DRAW;
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public int getM() {
        return this.m;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
