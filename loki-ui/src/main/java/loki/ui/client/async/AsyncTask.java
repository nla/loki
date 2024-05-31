package loki.ui.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A task to be run asynchronously within a batch {@see AsyncBatch}.
 */

public interface AsyncTask
{
    /**
     * This is called by the {@link AsyncBatch} instance that is running this task.
     * 
     * Implementations should override this method to run the task required. When the implemented task is complete,
     * onComplete.onSuccess() should be called by the implementation, or onComplete.onFailure() if there was an exception.
     */
    
    public void run(AsyncCallback<Void> onComplete);
}