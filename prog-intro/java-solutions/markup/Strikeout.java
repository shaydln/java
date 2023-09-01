package markup;

import java.util.List;

public class Strikeout extends Token{
    public Strikeout (List<Token> list) {
        super(list);
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        forTokentoMarkdown(sb, "~", "~");
    }
    @Override
    public void toHtml(StringBuilder sb) {
        forTokenHtml(sb, "<s>", "</s>");
    }
}
