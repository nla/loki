package loki.server.text;

/**
 * Implementations will receive lines of text.
 */

public interface TextFileLineReceiver
{
    /**
     * Implementations should override this to process lines of text received.
     * 
     * @param index         The line index.
     * @param line          The text of the line.
     */
    
    public void onLine(int index, String line) throws Exception;
}