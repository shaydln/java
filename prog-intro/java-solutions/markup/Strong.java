package markup;

import java.util.List;

public class Strong extends Token{
    public Strong (List<Token> list) {
        super(list);
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        forTokentoMarkdown(sb, "__", "__");
    }
    @Override
    public void toHtml(StringBuilder sb) {
        forTokenHtml(sb, "<strong>", "</strong>");
    }
}
