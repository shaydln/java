import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class WsppPosition {
     static void makeChanges(String word, int s, int e, Map<String, ArrayList<Integer>> map, int numstring, int len, String line) {
        word = line.substring(s, e);
        word = word.toLowerCase();
        ArrayList<Integer> newarr = map.getOrDefault(word, new ArrayList<>());
        if (e - 1 >= s && map.get(word) != null) {
            newarr = map.get(word);
        }
        newarr.add(numstring);
        newarr.add(len);
        map.put(word, newarr);
    }
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new InputStreamReader
                    (new FileInputStream(args[0]), StandardCharsets.UTF_8));
            try {
                Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
                String line;
                int numstring = 1;
                while (in.hasNextLine() && (line = in.nextLine()) != null) {
                    int len = 1;
                    char[] ch = line.toCharArray();
                    String word = null;
                    int s = -1, e;
                    for (int i = 0; i < line.length(); i++) {
                        if (ch[i] == '\'' || Character.isLetter(ch[i]) ||
                                Character.DASH_PUNCTUATION == Character.getType(ch[i])) {
                            if (s == -1) {
                                s = i;
                            }
                            if (i == line.length() - 1) {
                                e = i + 1;
                                makeChanges(word, s, e, map,  numstring,  len, line);
                                len++;
                                s = -1;
                            }
                        } else if (s != -1) {
                            e = i;
                            makeChanges(word, s, e, map,  numstring,  len, line);
                            len++;
                            s = -1;
                        }
                    }
                    numstring++;
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(args[1]), StandardCharsets.UTF_8));
                try {
                    for (Map.Entry<String, ArrayList<Integer>> item : map.entrySet()) {
                        out.write(item.getKey() + " " + item.getValue().size() / 2);
                        int cnt = 0;
                        for (int num : item.getValue()) {
                            if (cnt % 2 == 0) {
                                out.write(" " + num);
                            } else {
                                out.write(":" + num);
                            }
                            cnt++;
                        }
                        out.write('\n');
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
