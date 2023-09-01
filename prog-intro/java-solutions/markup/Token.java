package markup;

import java.util.List;

public abstract class Token {
    StringBuilder str = new StringBuilder("");
    List<Token> list;
    public Token(List<Token> list) {
        this.list = list;
    }
    public Token() {

    }

    public void toMarkdown(StringBuilder sb) {
        forTokentoMarkdown(sb,"", "");
    }

    public void toHtml(StringBuilder sb) {
        forTokenHtml(sb, "", "");
    }

    public void forTokenHtml(StringBuilder sb, String markStart, String markEnd) {
        sb.append(markStart);
        for (Token token : list) {
            token.toHtml(sb);
        }
        sb.append(markEnd);
    }

    public void forTokentoMarkdown(StringBuilder sb, String markStart, String markEnd) {
        sb.append(markStart);
        for (Token token : list) {
            token.toMarkdown(sb);
        }
        sb.append(markEnd);
    }

}
