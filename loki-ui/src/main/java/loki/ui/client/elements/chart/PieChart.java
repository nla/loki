package loki.ui.client.elements.chart;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A wrapper class for a ChartJS Pie Chart.
 * Prior to using this class, Chart.enableChartRendering() must have been called in the application.
 */

public class PieChart extends Chart
{
    private JavaScriptObject chart;
    
    /**
     * Create a new Pie Chart. The underlying element is the chart's canvas.
     */
    
    public PieChart() throws Exception
    {
        super();
    }
    
    /**
     * Renders the Bar Chart onto this canvas, using the provided options and data.
     * 
     * @param options                   The options for rendering the chart.
     * @param dataset                  The array of data to render.
     */
    
    public void render(ChartOptions options, PieChartData... dataset)
    {
        chart = render(getReference(), options, dataset);
    }
    
    private native JavaScriptObject render(JavaScriptObject ctx, ChartOptions options, PieChartData[] dataset)
    /*-{
        var dataLabels = [];
        var dataValues = [];
        var dataBackgrounds = [];
        var dataHoverBackgrounds = [];
        var dataBorders = [];
        
        for(var i=0; i<dataset.length; i++)
        {
            dataLabels[i] = dataset[i].@loki.ui.client.elements.chart.PieChartData::getLabel()();
            dataValues[i] = dataset[i].@loki.ui.client.elements.chart.PieChartData::getValue()();
            dataBackgrounds[i] = dataset[i].@loki.ui.client.elements.chart.PieChartData::getBackgroundColor()();
            dataHoverBackgrounds[i] = dataset[i].@loki.ui.client.elements.chart.PieChartData::getHoverBackgroundColor()();
            dataBorders[i] = dataset[i].@loki.ui.client.elements.chart.PieChartData::getBorderColor()();
        }
        
        return new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: dataLabels,
                datasets: [{
                    data: dataValues,
                    backgroundColor: dataBackgrounds,
                    hoverBackgroundColor: dataHoverBackgrounds,
                    borderColor: dataBorders
                }]
            },
            options: {
                plugins: {
                    legend: {
                        display: options.@loki.ui.client.elements.chart.ChartOptions::shouldDisplayLegend()(),
                        position: options.@loki.ui.client.elements.chart.ChartOptions::getLegendPosition()(),
                        labels: {
                            color: options.@loki.ui.client.elements.chart.ChartOptions::getTextColor()()
                        }
                    }
                }
            }
        });
    }-*/;
}