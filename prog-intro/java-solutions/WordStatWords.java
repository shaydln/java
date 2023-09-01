import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class WordStatWords {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader
                    (new FileInputStream(args[0]), StandardCharsets.UTF_8));
            try {
                Map<String, Integer> map = new TreeMap<>();
                String line;
                while ((line = in.readLine()) != null) {
                    char[] ch = line.toCharArray();
                    String word;
                    int s = -1, e;
                    for (int i = 0; i < line.length(); i++) {
                        if (ch[i] == '\'' || Character.isLetter(ch[i]) ||
                                Character.DASH_PUNCTUATION == Character.getType(ch[i])) {
//                            word += ch[i];
                            if (s == -1) {
                                s = i;
                            }
                            if (i == line.length() - 1) {
                                e = i + 1;
                                word = line.substring(s, e);
                                word = word.toLowerCase();
                                Integer w = map.get(word);
                                if (w != null && e - 1 >= s) {
                                    map.put(word, w + 1);
                                } else if (e - 1 >= s) {
                                    map.put(word, 1);
                                }
                                s = -1;
                            }
                        } else if (s != -1){
                            e = i;
                            word = line.substring(s, e);
                            word = word.toLowerCase();
                            Integer w = map.get(word);
                            if (w != null && e - 1 >= s) {
                                map.put(word, w + 1);
                            } else if (e - 1 >= s) {
                                map.put(word, 1);
                            }
                            s = -1;
                        }
                    }
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(args[1]), StandardCharsets.UTF_8));
                try {
                    for (Map.Entry<String, Integer> item:map.entrySet()) {
                        out.write(item.getKey() + ' ' + item.getValue() + '\n');
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
