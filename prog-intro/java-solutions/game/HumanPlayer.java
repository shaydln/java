import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private Scanner in;

    public HumanPlayer(final PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int tempRow = 0, tempColumn= 0;

            while (true) {
                try {
                    tempRow = in.nextInt();
                    tempColumn = in.nextInt();
                    in = new Scanner(System.in);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Введите пожалуйста заново оба числа");
                    in = new Scanner(System.in);
                } catch (NoSuchElementException e) {
                    System.err.println("Ввод закончился");
                    System.exit(0);
                }
            }

            final Move move = new Move(tempRow, tempColumn, cell);
            if (position.isValid(move)) {
                return move;
            }
            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + move + " is invalid");
        }
    }
}
