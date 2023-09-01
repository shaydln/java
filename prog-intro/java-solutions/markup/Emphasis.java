package markup;

import java.util.List;

public class Emphasis extends Token{
    public Emphasis (List<Token> list) {
        super(list);
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        forTokentoMarkdown(sb, "*", "*");
    }
    @Override
    public void toHtml(StringBuilder sb) {
        forTokenHtml(sb, "<em>", "</em>");
    }
}
