package loki.ui.client.elements;

import loki.ui.Element;

/**
 * A utility class that creates an element that takes up space with either width or height.
 */

public class Spacer extends Element
{
    private Spacer()
    {
        super("div");
    }
    
    /**
     * Creates a new instance with the width specified.
     * The width should be a CSS unit.
     * 
     * For example:
     * Spacer.wide("1em");
     */
    
    public static Spacer wide(String width)
    {
        Spacer spacer = new Spacer();
        spacer.setStyle("minWidth", width);
        return spacer;
    }
    
    /**
     * Creates a new instance with the height specified.
     * The height should be a CSS unit.
     * 
     * For example:
     * Spacer.tall("1em");
     */
    
    public static Spacer tall(String height)
    {
        Spacer spacer = new Spacer();
        spacer.setStyle("minHeight", height);
        return spacer;
    }
}