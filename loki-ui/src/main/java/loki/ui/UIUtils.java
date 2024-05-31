package loki.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Static utility methods that can be used within GWT web client code.
 */

public class UIUtils
{
    /**
     * Logs the specified message to the JavaScript console.
     * 
     * @param message       The message to log.
     */
    
    public static void log(String message)
    {
        GWT.log(message);
    }
    
    /**
     * Logs the specified message and stack trace to the JavaScript console.
     * 
     * @param message       The message to log.
     * @param e             The stack trace to log.
     */
    
    public static void log(String message, Throwable e)
    {
        GWT.log(message, e);
    }
    
    /**
     * Logs the specified JavaScript object to the JavaScript console.
     * 
     * @param object        The object to log.
     */
    
    public static void log(JavaScriptObject object)
    {
        logObject(object);
    }
    
    private static native void logObject(JavaScriptObject object)
    /*-{
        console.log(object);
    }-*/;
}