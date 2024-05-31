package loki.ui.client.elements.chart;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A wrapper class for a ChartJS Bar Chart.
 * Prior to using this class, Chart.enableChartRendering() must have been called in the application.
 */

public class BarChart extends Chart
{
    private JavaScriptObject chart;
    
    /**
     * Create a new Bar Chart. The underlying element is the chart's canvas.
     */
    
    public BarChart() throws Exception
    {
        super();
    }
    
    /**
     * Renders the Bar Chart onto this canvas, using the provided options and datasets.
     * 
     * @param options                   The options for rendering the chart.
     * @param legendClickListener       The instance that will handle clicks on legend items.
     * @param datasets                  The array of datasets to render.
     */
    
    public void render(ChartOptions options, ChartLegendClickListener legendClickListener, BarChartDataset... datasets)
    {
        chart = render(this, getReference(), options, datasets, legendClickListener);
    }
    
    /**
     * Returns the reference to the the chart JS object (not the canvas).
     * 
     * @return  the reference to the chart JS object.
     */
    
    private JavaScriptObject getChart()
    {
        return chart;
    }
    
    /**
     * Re-renders the chart. This should be called after any change to underlying data, or after a dataset is hidden or shown.
     */
    
    public void update()
    {
        update(chart);
    }
    
    private native void update(JavaScriptObject chart)
    /*-{
        chart.update();
    }-*/;
    
    /**
     * Tells ChartJS not to render the dataset at the specified index.
     * 
     * @param index     The index of the dataset to hide.
     */
    
    public void hideDataset(int index)
    {
        hideDataset(chart, index);
    }
    
    private native void hideDataset(JavaScriptObject chart, int index)
    /*-{
        chart.setDatasetVisibility(index, false);
    }-*/;
    
    /**
     * Tells ChartJS to render a previously hidden dataset at the specified index.
     * 
     * @param index     The index of the dataset to un-hide.
     */
    
    public void showDataset(int index)
    {
        showDataset(chart, index);
    }
    
    private native void showDataset(JavaScriptObject chart, int index)
    /*-{
        chart.setDatasetVisibility(index, true);
    }-*/;
    
    /**
     * Returns whether or not the dataset at the specific index is currently hidden.
     * 
     * @return      true if the dataset is hidden, false otherwise.
     */
    
    public boolean isDatasetVisible(int index)
    {
        return isDatasetVisibile(chart, index);
    }
    
    private native boolean isDatasetVisibile(JavaScriptObject chart, int index)
    /*-{
         return chart.isDatasetVisible(index);
    }-*/;
    
    private native JavaScriptObject render(BarChart parent, JavaScriptObject ctx, ChartOptions options, BarChartDataset[] datasets, ChartLegendClickListener legendClickListener)
    /*-{
        var seriesLabels = [];
        var initialData = datasets[0].@loki.ui.client.elements.chart.BarChartDataset::getData()();
        
        for(var i=0; i<initialData.length; i++)
        {
            seriesLabels[i] = initialData[i].@loki.ui.client.elements.chart.BarChartData::getLabel()();
        }
        
        var dataDatasets = [];
        
        for(var i=0; i<datasets.length; i++)
        {
            var dataValues = [];
            var dataset = datasets[i];
            var datasetEntries = dataset.@loki.ui.client.elements.chart.BarChartDataset::getData()();
            
            for(var n=0; n<datasetEntries.length; n++)
            {
                var entry = datasetEntries[n];
                dataValues[n] = entry.@loki.ui.client.elements.chart.BarChartData::getValue()();
            }
            
            dataDatasets[i] = {
                type: 'bar',
                label: dataset.@loki.ui.client.elements.chart.BarChartDataset::getLabel()(),
                data: dataValues,
                backgroundColor: dataset.@loki.ui.client.elements.chart.BarChartDataset::getBackgroundColor()(),
                hoverBackgroundColor: dataset.@loki.ui.client.elements.chart.BarChartDataset::getHoverBackgroundColor()(),
                borderColor: dataset.@loki.ui.client.elements.chart.BarChartDataset::getBorderColor()(),
            };
        }
        
        return new Chart(ctx, {
            type: 'bar',
            data: {
                labels: seriesLabels,
                datasets: dataDatasets
            },
            options: {
                responsive: true,
                scales: {
                    x: {
                        stacked: true,
                        ticks: {
                            color: options.@loki.ui.client.elements.chart.ChartOptions::getTextColor()()
                        }
                    },
                    y: {
                        beginAtZero: true,
                        stacked: true,
                        ticks: {
                            color: options.@loki.ui.client.elements.chart.ChartOptions::getTextColor()()
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: options.@loki.ui.client.elements.chart.ChartOptions::shouldDisplayLegend()(),
                        position: options.@loki.ui.client.elements.chart.ChartOptions::getLegendPosition()(),
                        labels: {
                            color: options.@loki.ui.client.elements.chart.ChartOptions::getTextColor()()
                        },
                        onClick: function (event, legendItem) {
                            var chartRef = parent.@loki.ui.client.elements.chart.BarChart::getChart()();
                            chartRef.setDatasetVisibility(legendItem.datasetIndex, !chartRef.isDatasetVisible(legendItem.datasetIndex));
                            chartRef.update();
                            legendClickListener.@loki.ui.client.elements.chart.ChartLegendClickListener::onLegendItemClicked(I)(legendItem.datasetIndex);
                        }
                    }
                }
            }
        });
    }-*/;
}