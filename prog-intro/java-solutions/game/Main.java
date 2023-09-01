import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static int check() {
        Scanner sc = new Scanner(System.in);
        int m;
        while (true) {
            try {
                m = sc.nextInt();
                if (m < 1) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Введите пожалуйста именно число");
                sc.next();
            }
        }
        return m;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = 0, n = 0, k = 0, cntOfWins = 0;
        m = check();
        n = check();
        while (true) {
            try {
                k = sc.nextInt();
                if (k < 1 || k > m || k > n) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Введите пожалуйста число");
                sc.next();
            }
        }
        cntOfWins = check();

        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result, cntOfMatches = 0;
        do {
            result = game.play(new TicTacToeBoard(m, n, k), cntOfMatches%2);
            System.out.println("Game result: " + result);
            cntOfMatches++;
        } while (cntOfMatches < cntOfWins);
    }
}


