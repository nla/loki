package loki.ui.client.elements.map;

import loki.ui.Element;

/**
 * A wrapper class for a LeafletJS map.
 * Prior to using this class, Map.enableMapRendering() must have been called in the application.
 * 
 * This class is a work-in-progress.
 */

public class Map extends Element
{
    private static boolean leafletInitialised;
    
    public Map() throws Exception
    {
        super("div");
        
        if(!leafletInitialised)
        {
            throw new Exception("Map.enableMapRendering() was not called.");
        }
    }
    
    public static void enableMapRendering(Runnable onLoaded)
    {
        leafletInitialised = true;
        loadLeafletJS(onLoaded);
    }
    
    private static native void loadLeafletJS(Runnable onLoaded)
    /*-{
        var styleSheet = document.createElement('link');
        styleSheet.onload = function()
        {
            var script = document.createElement('script');
            script.onload = function()
            {
                console.log('script loaded');
                onLoaded.@java.lang.Runnable::run()();
            };
            
            script.src = '/static/leaflet.js';
            document.body.appendChild(script);
        };
        
        styleSheet.setAttribute('rel', 'stylesheet');
        styleSheet.setAttribute('type', 'text/css');
        styleSheet.setAttribute('href', '/static/leaflet.css');
        document.head.appendChild(styleSheet);
    }-*/;
}
