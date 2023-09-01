import java.io.*;

public class Scanner {
    private Reader reader;
    private char[] buffer = new char[1024];
    private StringBuilder bigbuffer = new StringBuilder();
    private int cur = 0;
    private int n = 0;
    public Scanner(InputStream in) {
        this.reader = new InputStreamReader(in);
    }
    public Scanner(String str) {
        this.reader = new StringReader(str);
    }
    public Scanner(InputStreamReader in) {
        this.reader = in;
    }
    public String nextOct() {
        try {
            while (n != -1) {
                for (int i = cur; i < n; i++) {
                    if (Character.isWhitespace(buffer[i]) || buffer[i] == 0) {
                        cur = i;
                        String temp = bigbuffer.toString();
                        bigbuffer = new StringBuilder("");
                        return temp;
                    }
                    bigbuffer.append(buffer[i]);
                }
                n = reader.read(buffer);
                cur = 0;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return bigbuffer.toString();
    }

    public boolean hasNextOct() {
        try {
            while (n != -1) {
                for (int i = cur; i < n; i++) {
                    if (Character.isDigit(buffer[i]) || buffer[i] == '-') {
                        cur = i;
                        return true;
                    }
                }
                n = reader.read(buffer);
                cur = 0;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public int nextInt() {
        return Integer.parseInt(nextOct());
    }

    public boolean hasNextInt() {
        return hasNextOct();
    }
    public boolean hasNextLine() {
        try {
            while (n != -1) {
                for (int i = cur; i < n; i++) {
                    if (buffer[i] == '\n' ) {
                        cur = i + 1;
                        return true;
                    }
                    bigbuffer.append(buffer[i]);
                }
                n = reader.read(buffer);
                cur = 0;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    public String nextLine() {
        String temp = bigbuffer.toString();
        bigbuffer = new StringBuilder("");
        return temp;
    }
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
 