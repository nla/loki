package loki.ui;

/**
 * A short-hand utility class that is a DIV dom element that contains text.
 */

public class Label extends Element
{
    /**
     * Creates a new DIV element with the specified text as inner HTML.
     * 
     * @param text The text to add to this DIV.
     */

    public Label(String text)
    {
        super("div");
        setText(text);
    }

    /**
     * Returns the text of this element, obtained from its inner HTML.
     * 
     * @return the text of this element.
     */

    public String getText()
    {
        return getInnerHTML();
    }

    /**
     * Replaces the text of this element with the specified text as inner HTML.
     * 
     * @param text The text to set.
     */

    public void setText(String text)
    {
        setInnerHTML(text);
    }
}