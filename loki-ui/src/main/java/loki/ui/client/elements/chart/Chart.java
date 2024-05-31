package loki.ui.client.elements.chart;

import loki.ui.Element;

/**
 * A base wrapper class for ChartJS charts.
 * Prior to using any implementations of this class, Chart.enableChartRendering() must have been called in the application.
 */

public abstract class Chart extends Element
{
    private static boolean chartJSInitialised;
    
    public Chart() throws Exception
    {
        super("canvas");
        
        if(!chartJSInitialised)
        {
            throw new Exception("Chart.enableChartRendering() was not called.");
        }
    }
    
    /**
     * Loads the resources required to render ChartJS charts.
     * 
     * @param onLoaded      The runnable to run once the resources are loaded.
     */
    
    public static void enableChartRendering(Runnable onLoaded)
    {
        chartJSInitialised = true;
        loadChartJS(onLoaded);
    }
    
    private static native void loadChartJS(Runnable onLoaded)
    /*-{
        var script = document.createElement('script');
        script.onload = function () {
            onLoaded.@java.lang.Runnable::run()();
        };
        script.src = '/static/chart.umd.js';

        document.body.appendChild(script);
    }-*/;
}