import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new InputStreamReader
                    (new FileInputStream(args[0]), StandardCharsets.UTF_8));
            try {
                Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
                String line;
                int len = 1;
                while (in.hasNextLine() && (line = in.nextLine()) != null) {

                    char[] ch = line.toCharArray();
                    String word;
                    int s = -1, e;
                    for (int i = 0; i < line.length(); i++) {
                        if (ch[i] == '\'' || Character.isLetter(ch[i]) ||
                                Character.DASH_PUNCTUATION == Character.getType(ch[i])) {
                            if (s == -1) {
                                s = i;
                            }
                            if (i == line.length() - 1) {
                                e = i + 1;
                                word = line.substring(s, e);
                                word = word.toLowerCase();
                                if (e - 1 >= s && map.get(word) != null) {
                                    ArrayList<Integer> newarr = map.get(word);
                                    newarr.add(len);
                                    len++;
                                    map.put(word, newarr);
                                } else if (e - 1 >= s) {
                                    ArrayList<Integer> newarr = new ArrayList<>();
                                    newarr.add(len);
                                    len++;
                                    map.put(word, newarr);
                                }
                                s = -1;
                            }
                        } else if (s != -1){
                            e = i;
                            word = line.substring(s, e);
                            word = word.toLowerCase();
                            if (e - 1 >= s && map.get(word) != null) {
                                ArrayList<Integer> newarr = map.get(word);
                                newarr.add(len);
                                len++;
                                map.put(word, newarr);
                            } else if (e - 1 >= s) {
                                ArrayList<Integer> newarr = new ArrayList<>();
                                newarr.add(len);
                                len++;
                                map.put(word, newarr);
                            }
                            s = -1;
                        }
                    }
                }
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(args[1]), StandardCharsets.UTF_8));
                try {
                    for (Map.Entry<String, ArrayList<Integer>> item:map.entrySet()) {
                        out.write(item.getKey() + " " + item.getValue().size());
                        for (int num : item.getValue()) {
                            out.write( " " + num);
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

