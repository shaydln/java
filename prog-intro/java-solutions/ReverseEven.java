import java.util.Scanner;
import java.util.Arrays;

public class ReverseEven {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [][] numbers = new int[0][0];
        for (int i = 0; sc.hasNextLine(); i++) {
            String line = sc.nextLine();
            if (i >= numbers.length) {
                numbers = Arrays.copyOf(numbers, (numbers.length + 1) * 2);
            }
            Scanner scnum = new Scanner(line);
            for (int j = 0; scnum.hasNextInt(); j++) {
                int num = scnum.nextInt();
                if (numbers[i] == null) {
                    numbers[i] = Arrays.copyOf(new int[0], 2);
                }
                if (j >= numbers[i].length) {
                    numbers[i] = Arrays.copyOf(numbers[i], (numbers[i].length + 1) * 2);
                }
                numbers[i][j] = num;
            }
        }
        for (int i = numbers.length - 1; i >= 0; i--) {
            for (int j = numbers[i].length - 2; j >= 0; j--) {
                if (numbers[i][j] % 2 == 0) {
                    System.out.print(numbers[i][j]);
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
