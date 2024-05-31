package loki;

/**
 * Implementations can receive and handle exceptions.
 */

public interface ErrorHandler
{
    /**
     * Called to inform this handler that an exception should be handled.
     * 
     * @param e The exception to be handled.
     */
    
    public void handleError(Throwable e);
}