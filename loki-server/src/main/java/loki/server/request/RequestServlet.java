package loki.server.request;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import loki.server.ServerOperations;
import loki.server.session.SessionManager;

/**
 * Implementations should extend this class as a server-side implementation of a {@link com.google.gwt.user.client.rpc.RemoteService} to respond to requests from web-clients.
 * 
 * If using in-built session management to identify the user of requests, getSessionManager() can be called by implementations.
 */

public abstract class RequestServlet<Ops extends ServerOperations> extends RemoteServiceServlet
{
    private static final long serialVersionUID = 1L;
    private SessionManager sessionManager;
    private Ops operations;
    
    /**
     * @return a session manager that can be used to identify the user of the request if they have been authenticated.
     */
    
    public SessionManager getSessionManager()
    {
        return sessionManager;
    }
    
    /**
     * @return If using in-built session management, this method is called when this servlet is instantiated to set the reference to the session manager.
     */
    
    public void setSessionManager(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }
    
    /**
     * @return the implementation-dependant operations object that may contain useful objects and methods.
     */

    public Ops getOperations()
    {
        return operations;
    }
    
    /**
     * This is called by the server when this servlet is instantiated in order to set the reference to the implementation-dependant operations object, which may contain useful objects and methods.
     * 
     * @param the operations object.
     */

    public void setOperations(Ops operations)
    {
        this.operations = operations;
    }
}