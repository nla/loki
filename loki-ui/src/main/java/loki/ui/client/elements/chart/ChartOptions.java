package loki.ui.client.elements.chart;

/**
 * A common set of options that apply to most Chart implementations.
 */

public class ChartOptions
{
    private boolean displayLegend;
    private String legendPosition;
    private String textColor;

    private ChartOptions()
    {
        this.displayLegend = true;
        this.legendPosition = "bottom";
        this.textColor = "white";
    }
    
    /**
     * Called by Chart implementations.
     * Returns whether or not the options specify that the legend should be displayed in the chart.
     * 
     * @return  true if the legend should be displayed, otherwise false.
     */
    
    public boolean shouldDisplayLegend()
    {
        return displayLegend;
    }
    
    /**
     * Called by Chart implementations.
     * Returns the legend position.
     * 
     * @return  the legend position.
     */
    
    public String getLegendPosition()
    {
        return legendPosition;
    }
    
    /**
     * Specifies whether or not the chart should display the legend.
     * 
     * @param displayLegend     Whether or not to display the legend.
     * @return                  this options instance.
     */

    public ChartOptions displayLegend(boolean displayLegend)
    {
        this.displayLegend = displayLegend;
        return this;
    }
    
    /**
     * Specifies that the legend should be displayed above the chart.
     * 
     * @return                  this options instance.
     */
    
    public ChartOptions legendPositionTop()
    {
        this.legendPosition = "top";
        return this;
    }
    
    /**
     * Specifies that the legend should be displayed to the left of the chart.
     * 
     * @return                  this options instance.
     */
    
    public ChartOptions legendPositionLeft()
    {
        this.legendPosition = "left";
        return this;
    }
    
    /**
     * Specifies that the legend should be displayed to the right of the chart.
     * 
     * @return                  this options instance.
     */
    
    public ChartOptions legendPositionRight()
    {
        this.legendPosition = "right";
        return this;
    }
    
    /**
     * Specifies that the legend should be displayed below the chart.
     * 
     * @return                  this options instance.
     */
    
    public ChartOptions legendPositionBottom()
    {
        this.legendPosition = "bottom";
        return this;
    }
    
    /**
     * Specifies that the legend should be displayed inside the chart area.
     * 
     * @return                  this options instance.
     */
    
    public ChartOptions legendPositionChartArea()
    {
        this.legendPosition = "chartArea";
        return this;
    }
    
    /**
     * Specifies the general text color of the chart.
     * 
     * @param color             The general text color of the chart.
     * @return                  this options instance.
     */
    
    public ChartOptions textColor(String color)
    {
        this.textColor = color;
        return this;
    }
    
    /**
     * Called by Chart implementations.
     * Returns the general text color.
     * 
     * @return  the general text color.
     */
    
    public String getTextColor()
    {
        return textColor;
    }
    
    /**
     * A utility method for creating a new instance.
     * Example usage:
     * 
     * ChartOptions.create().textColor("black").legendPositionTop();
     */

    public static ChartOptions create()
    {
        return new ChartOptions();
    }
}