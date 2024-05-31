package loki.ui.client.elements.chart;

/**
 * Implementations will receive an event when a legend item within the associated Chart JS chart is clicked.
 */

public interface ChartLegendClickListener 
{
    /**
     * Called when a legend item in the associated chart has been clicked by the user.
     * 
     * @param index     The index of the dataset to which the legend item relates.
     */
    
    public void onLegendItemClicked(int index);
}