package loki.server.adapter;

import loki.Attributes;
import loki.ErrorHandler;
import loki.server.Server;
import loki.server.ServerOperations;

/**
 * Implementations will be initialised on server startup. Generally, this is for the in-build adapters within this project, however customer adapters can also be implemented if desired.
 */

public interface Adapter<Ops extends ServerOperations>
{
    /**
     * Called when the server starts up. Implementations should initialise anything they need to use over the lifetime of the server. Any resources initialised here should be disposed of with the terminate() method.
     * 
     * @param properties   Properties read from the adapter-specific properties file, as specified in the adapters.properties file.
     * @param errorHandler Implementations should inform this handler of any exceptions encountered.
     * @param ops          Specific to the implementation of {@link Server}, this class can host objects and methods useful to custom adapters
     */

    public void initialise(Attributes<String> properties, ErrorHandler errorHandler, Ops operations) throws Exception;

    /**
     * Called when the server shuts down. Any resources or connections should be terminated.
     */

    public void terminate();
}