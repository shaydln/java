package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class Md2Html {
    static class Element {
        public String text;
        public Integer position;
        public Element(String text, Integer position) {
            this.text = text;
            this.position = position;
        }
    }
    public static StringBuilder caretka(StringBuilder src) {
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < src.length(); i++) {
            switch (src.charAt(i)) {
                case('<'):
                    res.append("&lt;");
                    continue;
                case('>'):
                    res.append("&gt;");
                    continue;
                case('&'):
                    res.append("&amp;");
                    continue;
            }
            res.append(src.charAt(i));
        }
        return res;
    }
    static class Variables {
        StringBuilder cur;
        Stack<Element> stack;
        Stack<Element> tag;
        Integer pos;
        public Variables (Stack<Element> stack, Stack<Element> tag, StringBuilder cur, Integer pos) {
            this.stack = stack;
            this.tag = tag;
            this.cur = cur;
            this.pos = pos;
        }
    }
    public static StringBuilder parseMain(StringBuilder src) {
        StringBuilder cur = new StringBuilder("");
        Stack<Element> stack = new Stack<>();
        Stack<Element> tag = new Stack<>();
        Integer pos = 0;
        Variables v = new Variables(stack, tag, cur, pos);
        for (int i = 0; i < src.length(); i++) {
            v.cur.append(src.charAt(i));
            if (src.charAt(i) == '\\') {
                v.cur.deleteCharAt(v.cur.length()-1);
                if (i < src.length()-2 && (src.charAt(i+1) == '*' && src.charAt(i+2) == '*'
                        || src.charAt(i+1) == '_' && src.charAt(i+2) == '_' || src.charAt(i+1) == '-' && src.charAt(i+2) == '-')) {
                    switch (src.charAt(i+1)) {
                        case ('*'):
                            v.cur.append("**");
                        case ('_'):
                            v.cur.append("__");
                        case ('-'):
                            v.cur.append("--");
                    }
                    i += 2;
                } else if (i < src.length()-1 && (src.charAt(i+1) == '*'
                        || src.charAt(i+1) == '_' || src.charAt(i+1) == '`' || src.charAt(i+1) == '~')) {
                    switch (src.charAt(i+1)) {
                        case ('*') :
                            v.cur.append("*");
                            break;
                        case ('_') :
                            v.cur.append("_");
                            break;
                        case ('`') :
                            v.cur.append("`");
                            break;
                        case ('~') :
                            v.cur.append("~");
                            break;
                    }
                    i++;
                }
            } else if (src.charAt(i) == '`') {
                oneLenghtTag(v, "`");
            } else if (i < src.length()-1 && src.charAt(i) == '-' && src.charAt(i+1) == '-') {
                twoLengthTag(v, "--");
                i++;
            } else if (i < src.length()-1 && src.charAt(i) == '_' && src.charAt(i+1) == '_') {
                twoLengthTag(v, "__");
                i++;
            } else if (src.charAt(i) == '_') {
                oneLenghtTag(v, "_");
            } else if (i < src.length()-1 && src.charAt(i) == '*' && src.charAt(i+1) == '*') {
                twoLengthTag(v, "**");
                i++;
            } else if (src.charAt(i) == '*') {
                oneLenghtTag(v, "*");
            } else if (src.charAt(i) == '~') {
                oneLenghtTag(v, "~");
            }
            if (i == src.length()-1) {
                v.stack.push(new Element(v.cur.toString(), pos));
                v.pos++;
            }
        }
        StringBuilder res = new StringBuilder("");
        for (Element el : stack) {
            res.append(el.text);
        }
        return res;
    }
    public static StringBuilder parseBegin(StringBuilder src) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (src.charAt(i) == '#') {
            i++;
            if (src.charAt(i) == ' ') {
                sb.append("<h").append(i).append(">");
                i++;
                break;
            }
        }
        if (sb.isEmpty())  {
            sb.append("<p>");
            for (int j = 0; j < i; j++) {
                sb.append("#");
            }
        }
        StringBuilder caret = new StringBuilder("");
        caret.append(caretka(src));
        sb.append(parseMain(new StringBuilder().append(caret, i, caret.length())));
        if (sb.charAt(sb.length()-1) == '\n') {
            sb.deleteCharAt(sb.length()-1);
        }
        if (sb.charAt(1) == 'h') {
            sb.append("</h").append(sb.charAt(2)).append(">");
        }
        if (sb.charAt(1) == 'p') {
            sb.append("</p>");
        }
        sb.append('\n');
        return sb;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        char c = 0, pc;
        while (true) {
            pc = c;
            c = (char) in.read();
            if (c == '\uFFFF') {
                if (!sb.isEmpty()) {
                    out.write(String.valueOf(parseBegin(sb)));
                }
                break;
            }
            if (pc == '\n' && c == '\n' && sb.length() > 0) {
                out.write(String.valueOf(parseBegin(sb)));
                sb = new StringBuilder();
                continue;
            }
            if ((pc == '\n' && c == '\n') || (c == '\n' && pc == 0)) {
                continue;
            }
            sb.append(c);
        }
        in.close();
        out.close();
    }
    public static void oneLenghtTag(Variables v, String symbolTag) {
        String parseTagOpen = new String("");
        String parseTagClose = new String("");
        if (symbolTag.equals("*") || symbolTag.equals("_")) {
            parseTagOpen = "<em>";
            parseTagClose = "</em>";
        } else if (symbolTag.equals("`")) {
            parseTagOpen = "<code>";
            parseTagClose = "</code>";
        } else if (symbolTag.equals("~")) {
            parseTagOpen = "<mark>";
            parseTagClose = "</mark>";
        }
        if (!v.tag.isEmpty() && v.tag.peek().text.equals(symbolTag)) {
            v.cur.deleteCharAt(v.cur.length() - 1);
            v.stack.push(new Element(v.cur.toString(), v.pos));
            v.pos++;
            StringBuilder midRes = new StringBuilder("");
            midRes.append(parseTagClose);
            while (!v.stack.isEmpty() && !v.tag.isEmpty() && v.stack.peek().position > v.tag.peek().position) {
                midRes.insert(0, v.stack.peek().text);
                v.stack.pop();
            }
            midRes.insert(0, parseTagOpen);
            v.stack.pop();
            v.tag.pop();
            v.stack.push(new Element(midRes.toString(), v.pos));
            v.pos++;
            v.cur = new StringBuilder("");
        } else {
            v.cur.deleteCharAt(v.cur.length() - 1);
            v.stack.push(new Element(v.cur.toString(), v.pos));
            v.pos++;
            v.stack.push(new Element(symbolTag, v.pos));
            v.tag.push(new Element(symbolTag, v.pos));
            v.pos++;
            v.cur = new StringBuilder("");
        }
    }
    public static void twoLengthTag(Variables v, String symbol) {
        String parseTagOpen = new String("");
        String parseTagClose = new String("");
        if (symbol.equals("__") || symbol.equals("**")) {
            parseTagOpen = "<strong>";
            parseTagClose = "</strong>";
        } else if (symbol.equals("--")) {
            parseTagOpen = "<s>";
            parseTagClose = "</s>";
        }
        v.cur.deleteCharAt(v.cur.length()-1);
        v.stack.push(new Element(v.cur.toString(), v.pos));
        v.pos++;
        if (!v.tag.isEmpty() && v.tag.peek().text.equals(symbol)) {
            StringBuilder midRes = new StringBuilder("");
            midRes.append(parseTagClose);
            while (!v.stack.isEmpty()&& !v.tag.isEmpty() && v.stack.peek().position > v.tag.peek().position) {
                midRes.insert(0, v.stack.peek().text);
                v.stack.pop();
            }
            midRes.insert(0, parseTagOpen);
            v.stack.pop();
            v.tag.pop();
            v.stack.push(new Element(midRes.toString(), v.pos));
        } else {
            v.stack.push(new Element(symbol, v.pos));
            v.tag.push(new Element(symbol, v.pos));
        }
        v.pos++;
        v.cur = new StringBuilder("");
    }
}
