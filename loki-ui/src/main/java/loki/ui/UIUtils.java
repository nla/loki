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
        logMessage(message);
    }
    
    /**
     * Logs the specified message and stack trace to the JavaScript console.
     * 
     * @param message       The message to log.
     * @param e             The stack trace to log.
     */
    
    public static void log(Throwable e)
    {
        logMessage(printThrowable(e));
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
    
    /**
     * Converts the specified throwable to a string representation, including its stack trace.
     * 
     * @param e     The throwable to print.
     * @return      A string representation of the throwable.
     */
    
    public static String printThrowable(Throwable e)
    {
        StringBuilder b = new StringBuilder();
        printThrowable(b, e);
        return b.toString();
    }
    
    private static void printThrowable(StringBuilder b, Throwable e)
    {
        if(e.getMessage()!=null)
        {
            b.append(e.getMessage()+":\n");
        }
        for(StackTraceElement element: e.getStackTrace())
        {
            b.append("\tat "+element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+":"+element.getLineNumber()+")\n");
        }
        if(e.getCause()!=null)
        {
            b.append("Caused by:\n");
            printThrowable(b, e.getCause());
        }
    }
    
    private static native void logMessage(String message)
    /*-{
        console.log(message);
    }-*/;
    
    private static native void logObject(JavaScriptObject object)
    /*-{
        console.log(object);
    }-*/;
}