import java.io.*;
import java.util.Arrays;

public class ReverseOct {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = new String[0];
        for (int i = 0; sc.hasNextLine(); i++) {
            if (i >= str.length) {
                str = Arrays.copyOf(str, (str.length + 1) * 2);
            }
            str[i] = sc.nextLine();
        }
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == null) {
                continue;
            }
            Scanner line = new Scanner(str[i]);
            String[] numbers = new String[0];
            int len = 0;
            for (int j = 0; line.hasNextOct(); j++) {
                String num = line.nextOct();
                len++;
                if (j >= numbers.length) {
                    numbers = Arrays.copyOf(numbers, (numbers.length + 1) * 2);
                }
                numbers[j] = num;
            }
            for (int j = len - 1; j >= 0; j--) {
                System.out.print(numbers[j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}

