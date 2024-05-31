package loki.ui.client.elements.chart;

/**
 * Data to be rendered in a Bar Chart.
 * 
 * An instance of this class represents a Y axis value for a specific X axis position and belongs to a dataset.
 */

public class BarChartData
{  
    private String label;
    private int value;
    
    /**
     * Creates a new instance with the specified label and value.
     * 
     * @param label         The label associated with this data, which maps to a point across the X axis.
     * @param value         The Y axis value.
     */
    
    public BarChartData(String label, int value)
    {
        this.label = label;
        this.value = value;
    }
    
    /**
     * Returns the label.
     * 
     * @return              the label associated with this data.
     */
    
    public String getLabel()
    {
        return label;
    }
    
    /**
     * Returns the value.
     * 
     * @return              the Y value of this data.
     */
    
    public int getValue()
    {
        return value;
    }
}