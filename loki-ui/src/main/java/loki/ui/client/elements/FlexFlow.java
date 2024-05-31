package loki.ui.client.elements;

import loki.ui.Element;

/**
 * A utility class that represents a DIV container that uses a flex box CSS layout.
 * Applications that use this class should include the flex.css resource.
 */

public class FlexFlow extends Element
{
    private static final String BASE_CLASS_NAME = "flex-flow";
    
    private static final String DIRECTION_DOWN = "direction-down";
    private static final String DIRECTION_UP = "direction-up";
    private static final String DIRECTION_RIGHT = "direction-right";
    private static final String DIRECTION_LEFT = "direction-left";
    
    private static final String WRAP_NONE = "wrap-none";
    private static final String WRAP_FORWARD = "wrap-forward";
    private static final String WRAP_REVERSE = "wrap-reverse";
    
    private static final String JUSTIFY_START = "justify-start";
    private static final String JUSTIFY_END = "justify-end";
    private static final String JUSTIFY_CENTER = "justify-center";
    private static final String JUSTIFY_SPACE_BETWEEN = "justify-space-between";
    private static final String JUSTIFY_SPACE_AROUND = "justify-space-around";
    private static final String JUSTIFY_SPACE_EVENLY = "justify-space-evenly";
    
    private static final String ALIGN_START = "align-start";
    private static final String ALIGN_END = "align-end";
    private static final String ALIGN_CENTER = "align-center";
    private static final String ALIGN_STRETCH = "align-stretch";
    private static final String ALIGN_BASELINE = "align-baseline";
    
    private String className;
    private String direction;
    private String wrap;
    private String justification;
    private String alignment;
    
    public FlexFlow()
    {
        super("div");
        this.className = BASE_CLASS_NAME;
        this.direction = DIRECTION_DOWN;
        this.wrap = WRAP_NONE;
        this.justification = JUSTIFY_CENTER;
        this.alignment = ALIGN_CENTER;
        resetClassNames();
    }
    
    private void resetClassNames()
    {
        setClassNames(className+" "+direction+" "+wrap+" "+justification+" "+alignment);
    }
    
    /**
     * Sets additional CSS classes to the underlying DIV. This should be used to apply custom class names
     * instead of the Element.setClassNames() method, as this method preserves the class names used
     * to specify the layout of the flex box.
     * 
     * @param additional        Additional CSS classes to add to the underlying DIV.
     */
    
    public FlexFlow setAdditionalClassNames(String additional)
    {
        className = BASE_CLASS_NAME+" "+additional;
        resetClassNames();
        return this;
    }
    
    /**
     * Sets the flex box to lay out children from left to right.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow directionRight()
    {
        direction = DIRECTION_RIGHT;
        resetClassNames();
        return this;
    }
    
    /**
     * Sets the flex box to lay out children from right to left.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow directionLeft()
    {
        direction = DIRECTION_LEFT;
        resetClassNames();
        return this;
    }
    
    /**
     * Sets the flex box to lay out children from bottom to top.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow directionUp()
    {
        direction = DIRECTION_UP;
        resetClassNames();
        return this;
    }
    
    /**
     * Sets the flex box to lay out children from top to bottom.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow directionDown()
    {
        direction = DIRECTION_DOWN;
        resetClassNames();
        return this;
    }
    
    /**
     * Forces the flex box to never wrap its children.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow wrapNone()
    {
        wrap = WRAP_NONE;
        resetClassNames();
        return this;
    }
    
    /**
     * Specifies that the flex box can wrap its children when required.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow wrapForward()
    {
        wrap = WRAP_FORWARD;
        resetClassNames();
        return this;
    }
    
    /**
     * Specifies that the flex box can wrap its children when required. Wrapping will be in reverse.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow wrapReverse()
    {
        wrap = WRAP_REVERSE;
        resetClassNames();
        return this;
    }
    
    /**
     * Tightly packs children at the start of the direction.
     * 
     * For example: 
     *      for directionRight(), the row of children will be left justified.
     *      for directionDown(), the column of children will be top justified.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow justifyStart()
    {
        justification = JUSTIFY_START;
        resetClassNames();
        return this;
    }
    
    /**
     * Tightly packs children at the end of the direction.
     * 
     * For example: 
     *      for directionRight(), the row of children will be right justified.
     *      for directionDown(), the column of children will be bottom justified.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow justifyEnd()
    {
        justification = JUSTIFY_END;
        resetClassNames();
        return this;
    }
    
    /**
     * Tightly packs children around the center of the direction.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow justifyCenter()
    {
        justification = JUSTIFY_CENTER;
        resetClassNames();
        return this;
    }
    
    /**
     * Distributes children evenly over the direction, with the first item flush against the start of the direction
     * and the last item flush against the end of the direction.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow justifySpaceBetween()
    {
        justification = JUSTIFY_SPACE_BETWEEN;
        resetClassNames();
        return this;
    }
    
    /**
     * Distributes children evenly over the direction, with the gaps between the edges of the direction and the first and last items
     * half the size of the gap between items.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow justifySpaceAround()
    {
        justification = JUSTIFY_SPACE_AROUND;
        resetClassNames();
        return this;
    }
    
    /**
     * Distributes children evenly over the direction so that all gaps are equally sized.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow justifySpaceEvenly()
    {
        justification = JUSTIFY_SPACE_EVENLY;
        resetClassNames();
        return this;
    }
    
    /**
     * Aligns children on the cross-axis with the start of the perpendicular direction.
     * 
     * For example:
     *              For directionRight() and alignStart(), children will be laid out left to right so that
     *              the top of each child is on the same position on the Y axis.
     *              
     *              For directionDown() and alignStart(), children will be laid out top to bottom so that
     *              the left of each child is on the same position on the X axis.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow alignStart()
    {
        alignment = ALIGN_START;
        resetClassNames();
        return this;
    }
    
    /**
     * Aligns children on the cross-axis with the end of the perpendicular direction.
     * 
     * For example:
     *              For directionRight() and alignEnd(), children will be laid out left to right so that
     *              the bottom of each child is on the same position on the Y axis.
     *              
     *              For directionDown() and alignEnd(), children will be laid out top to bottom so that
     *              the right of each child is on the same position on the X axis.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow alignEnd()
    {
        alignment = ALIGN_END;
        resetClassNames();
        return this;
    }
    
    /**
     * Center aligns children on the cross-axis.
     * 
     * For example:
     *              For directionRight() and alignEnd(), children will be laid out left to right so that
     *              the center of each child is on the same position on the Y axis.
     *              
     *              For directionDown() and alignEnd(), children will be laid out top to bottom so that
     *              the center of each child is on the same position on the X axis.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow alignCenter()
    {
        alignment = ALIGN_CENTER;
        resetClassNames();
        return this;
    }
    
    /**
     * Stretches each child on the cross-axis so that it has the same length on that axis as this container.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow alignStretch()
    {
        alignment = ALIGN_STRETCH;
        resetClassNames();
        return this;
    }
    
    /**
     * Aligns children on the cross-axis so that their flex container baselines match.
     * 
     * @return  this flex box.
     */
    
    public FlexFlow alignBaseline()
    {
        alignment = ALIGN_BASELINE;
        resetClassNames();
        return this;
    }
    
    /**
     * A utility method for creating a FlexFlow.
     * 
     * Example usage:
     * 
     * FlexFlow.create().directionDown().alignCenter().with(myLabel, myPanel);
     * 
     * @return  a new flex box.
     */
    
    public static FlexFlow create()
    {
        return new FlexFlow();
    }
}