package loki.ui.client.elements.chart;

/**
 * A dataset to be rendered with a Bar Chart.
 * 
 * An instance of this class represents a category that is plotted across multiple X-axis positions, and contains a data value at each position to
 * represents its value on the Y axis at that position.
 */

public class BarChartDataset
{
    private String label;
    private String backgroundColor;
    private String hoverBackgroundColor;
    private String borderColor;
    private BarChartData[] data;
    
    /**
     * Creates a new instance with the given label, colors, and data.
     * 
     * @param label                The label of this dataset. This label will be used in the legend and will be associated with its colors on the chart.
     * @param backgroundColor      The background color of each bar segment this dataset occupies.
     * @param hoverBackgroundColor The background color when hovering over the bar segments this dataset occupies.
     * @param borderColor          The color of the border for each bar segment this dataset occupies.
     * @param data                 The array of data representing the values for this dataset at each position on the X axis.
     */

    public BarChartDataset(String label, String backgroundColor, String hoverBackgroundColor, String borderColor, BarChartData... data)
    {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.borderColor = borderColor;
        this.data = data;
    }
    
    /**
     * Returns the label of this dataset.
     * 
     * @return  The label of this dataset.
     */

    public String getLabel()
    {
        return label;
    }
    
    /**
     * Returns the background color of this dataset.
     * 
     * @return  The background color of this dataset.
     */
    
    public String getBackgroundColor()
    {
        return backgroundColor;
    }
    
    /**
     * Returns the hover background color of this dataset.
     * 
     * @return  The hover background color of this dataset.
     */

    public String getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }
    
    /**
     * Returns the border color of this dataset.
     * 
     * @return  The border color of this dataset.
     */

    public String getBorderColor()
    {
        return borderColor;
    }
    
    /**
     * Returns the array of data representing the values for this dataset at each position on the X axis.
     * 
     * @return  The array of data.
     */

    public BarChartData[] getData()
    {
        return data;
    }
}