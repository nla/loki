package loki.ui.client.elements.chart;

/**
 * A data instance to be rendered with a Pie Chart.
 * 
 * An instance of this class represents a category that takes up a percentage of the pie chart.
 */

public class PieChartData
{
    private String label;
    private double value;
    private String backgroundColor;
    private String hoverBackgroundColor;
    private String borderColor;
    
    /**
     * Creates a new instance with the specified label, value, and colors.
     * 
     * @param label                  The label of this category.
     * @param value                  The value associated with this category. This is weighted against the values of other data to determine the size of the pie slice.
     * @param backgroundColor        The color of the pie slice representing this category.
     * @param hoverBackgroundColor   The color of the pie slice representing this category when the mouse is hovering over it.
     * @param borderColor            The color of the border of the pie slice representing this category.
     */
    
    public PieChartData(String label, double value, String backgroundColor, String hoverBackgroundColor, String borderColor)
    {
        this.label = label;
        this.value = value;
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.borderColor = borderColor;
    }
    
    /**
     * Gets the label of this category.
     * 
     * @return      the label of this category.
     */
    
    public String getLabel()
    {
        return label;
    }
    
    /**
     * Gets the value of this category.
     * 
     * @return      the value of this category.
     */
    
    public double getValue()
    {
        return value;
    }
    
    /**
     * Gets the background color of this category.
     * 
     * @return      the background color of this category.
     */

    public String getBackgroundColor()
    {
        return backgroundColor;
    }
    
    /**
     * Gets the background color of this category when hovered.
     * 
     * @return      the background color of this category when hovered.
     */
    
    public String getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }
    
    /**
     * Gets the border color of this category.
     * 
     * @return      the border color of this category.
     */
    
    public String getBorderColor()
    {
        return borderColor;
    }
}