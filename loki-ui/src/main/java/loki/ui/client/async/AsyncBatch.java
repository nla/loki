package loki.ui.client.async;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import loki.Single;

/**
 * A utility class that runs a number of asynchronous tasks together and triggers the completion callback
 * when all asynchronous tasks are complete.
 * 
 * Example usage:
 * 
 * AsyncBatch batch = new AsyncBatch(completionCallback);
 * batch.addTask(taskA);
 * batch.addTask(taskB);
 * batch.run();
 */

public class AsyncBatch
{
    private AsyncCallback<Void> onComplete;
    private List<AsyncTask> tasks;
    private int completedCount;
    private Single<Throwable> error;
    
    /**
     * Creates a new instance with the specified completion callback.
     * 
     * @param onComplete        The callback to be called when all tasks are complete.
     */
    
    public AsyncBatch(AsyncCallback<Void> onComplete)
    {
        this.onComplete = onComplete;
        this.tasks = new ArrayList<AsyncTask>();
    }
    
    /**
     * Adds an asynchronous task. This task will be run when run() is called on this batch instance.
     * 
     * @param task              The task to be added.
     */

    public void addTask(AsyncTask task)
    {
        tasks.add(task);
    }
    
    /**
     * Runs all asynchronous tasks, and notifies the completion handler when all tasks are complete.
     */
    
    public void run()
    {
        error = Single.create(null);
        completedCount = 0;
        
        for(AsyncTask task: tasks)
        {
            task.run(new AsyncCallback<Void>(){
                public void onFailure(Throwable caught)
                {
                    error.setItem(caught);
                    onTaskComplete();
                }
                
                public void onSuccess(Void result)
                {
                    onTaskComplete();
                }
            });
        }
    }
    
    private void onTaskComplete()
    {
        completedCount++;
        
        if(completedCount==tasks.size())
        {
            if(error.getItem()!=null)
            {
                onComplete.onFailure(error.getItem());
            }
            else
            {
                onComplete.onSuccess(null);
            }
        }
    }
}