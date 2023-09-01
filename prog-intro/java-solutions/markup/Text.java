package markup;

public class Text extends Token {
    public Text(String sb) {
        str.append(sb);
    }
    public void toMarkdown(StringBuilder sb) {
        sb.append(str);
    }
    public void toHtml(StringBuilder sb) {
        sb.append(str);
    }
}
