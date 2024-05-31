package loki.server;

import java.util.ArrayList;
import java.util.List;
import loki.Attributes;
import loki.ErrorHandler;
import loki.server.adapter.Adapter;
import loki.server.util.ServerUtils;

/**
 * An abstract class for all application server implementations.
 * Implementations should override createServerOperations to perform any custom actions and return a custom ServerOperations object to be passed into each servlet.
 */

public abstract class Server<Ops extends ServerOperations>
{
    private Ops ops;
    private List<Adapter<Ops>> adapters;
    private Thread mainThread;

    public Server()
    {
        this.adapters = new ArrayList<Adapter<Ops>>();
    }
    
    /**
     * Implementations should override this method to perform any custom actions and return a custom ServerOperations object to be passed into each servlet.
     * 
     * @return a custom ServerOperations implementation.
     */

    protected abstract Ops createServerOperations() throws Exception;

    /**
     * Implementations should call this method to start up the server.
     * The server will initialise all adapters as specified in (config/adapters.properties).
     * The server will then continue to operate until stopped.
     * When the server process is killed, it will perform a graceful shutdown of all adapters.
     * 
     * @param errorListener The listener that will handle any errors that occur during adapter operations.
     */
    
    protected void start(ErrorHandler errorListener) throws Exception
    {
        mainThread = Thread.currentThread();

        ops = createServerOperations();
        Attributes<String> adapterProperties = ServerUtils.loadFromFile("config/adapters.properties");

        for(String key: adapterProperties.keys())
        {
            @SuppressWarnings("unchecked")
            Adapter<Ops> adapter = (Adapter<Ops>)Class.forName(key).getDeclaredConstructor().newInstance();
            adapter.initialise(ServerUtils.loadFromFile(adapterProperties.get(key)), errorListener, ops);
            adapters.add(adapter);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            public void run()
            {
                stop();
            }
        }));

        try
        {
            ServerUtils.log("Server initialised.");
            Thread.sleep(Long.MAX_VALUE);
        }
        catch(InterruptedException e){}
    }

    private void stop()
    {
        ServerUtils.log("Shutdown requested.");
        ServerUtils.log("Terminating adapters...");

        for(Adapter<Ops> adapter: adapters)
        {
            adapter.terminate();
        }

        mainThread.interrupt();
        ServerUtils.log("Shutdown complete.");
    }
}