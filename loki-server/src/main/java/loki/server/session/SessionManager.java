package loki.server.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import loki.ErrorHandler;
import thor.storage.StorageReceiver;
import thor.storage.file.FileStorageService;

/**
 * This class can be used to manage authenticated user sessions if a third-party application server is not being used.
 */

public class SessionManager
{
    private ErrorHandler errorHandler;
    private FileStorageService<Session> sessionStorage;
    private boolean sessionPersistence;
    
    private HashMap<String, Session> sessionIdMap;
    private ReentrantLock sessionLock;
    
    private ScheduledExecutorService cullingService;
    private long cullingThreshold;
    private volatile boolean terminating;
    
    /**
     * Instantiates the session manager with the given error listener.
     * The session manager will cull sessions periodically according to the specified interval if they have not been used (requests made to this server) for the amount of time specified.
     * This constructor allows you to choose whether or not to persist sessions. If so, sessions are saved to disk using a {@link thor.storage.FileStorageService} and loaded back into memory from that
     * service when the server is next started.
     * 
     * @param errorListener                     The listener that handles errors from the culling thread.
     * @param cullingInterval                   How often to cull sessions (ms).
     * @param cullingThreshold                  How long a session must be idle for in order to be culled (ms).
     * @param persistentSessions                Whether or not to persist sessions between server restarts.
     * @param persistantSessionStoragePath      If persistentSessions is true, the file system path to use to store sessions.
     * @param persistantSessionStorageTempPath  The temporary storage location for the peristentSessionStoragePath.
     */
    
    public SessionManager(ErrorHandler errorListener, long cullingInterval, long cullingThreshold, boolean persistentSessions, 
            String persistantSessionStoragePath, String persistantSessionStorageTempPath) throws Exception
    {
        errorHandler = errorListener;
        sessionPersistence = persistentSessions;
        sessionIdMap = new HashMap<String, Session>();
        sessionLock = new ReentrantLock(true);
        
        if(sessionPersistence)
        {
            sessionStorage = new FileStorageService<Session>(persistantSessionStoragePath, persistantSessionStorageTempPath);
            sessionStorage.loadAll(new StorageReceiver<Session>(){
                public void take(Session item)
                {
                    item.setLastAccessed(System.currentTimeMillis());
                    sessionIdMap.put(item.getSessionId(), item);
                }
            });
        }
        
        cullingService = Executors.newSingleThreadScheduledExecutor();
        cullingService.scheduleWithFixedDelay(new Runnable(){
            public void run()
            {
                cullSessions();
            }
        }, cullingInterval, cullingInterval, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Shuts down the session manager, ensuring no sessions can be created or looked up.
     * Terminates the session culling service.
     */
    
    public void terminate()
    {
        cullingService.shutdown();
        sessionLock.lock();
        
        try
        {
            terminating = true;
        }
        finally
        {
            sessionLock.unlock();
        }
    }
    
    /**
     * Retrieves a {@link Session} for the specified session ID
     * 
     * @param sessionId                             The ID of the session to retrieve.
     * @throws NoSessionException                   if no session exists with the specified ID.
     * @throws SessionManagerTerminatedException    if the session manager has been terminated.
     * @return                                      the Session object for the specified session ID.
     */
    
    public Session getSession(String sessionId) throws NoSessionException, SessionManagerTerminatedException
    {
        sessionLock.lock();
        
        try
        {
            if(terminating)
            {
                throw new SessionManagerTerminatedException();
            }
            
            Session session = sessionIdMap.get(sessionId);
            
            if(session==null)
            {
                throw new NoSessionException();
            }
            
            session.setLastAccessed(System.currentTimeMillis());
            return session;
        }
        finally
        {
            sessionLock.unlock();
        }
    }
    
    /**
     * Creates a {@link Session} for the specified user ID
     * 
     * @param user                                  The ID of the user for which to create a session.
     * @throws SessionManagerTerminatedException    if the session manager has been terminated.
     * @return                                      the newly created Session object.
     */
    
    public Session createSession(String user) throws SessionManagerTerminatedException, Exception
    {
        sessionLock.lock();
        
        try
        {
            if(terminating)
            {
                throw new SessionManagerTerminatedException();
            }
            
            Session session = new Session(generateSessionId(), user, System.currentTimeMillis());
            sessionIdMap.put(session.getSessionId(), session);
            
            if(sessionPersistence)
            {
                sessionStorage.store(session.getUserId(), session);
            }
            
            return session;
        }
        finally
        {
            sessionLock.unlock();
        }
    }
    
    private String generateSessionId()
    {
        return System.currentTimeMillis()+"__"+UUID.randomUUID().toString();
    }
    
    private void cullSessions()
    {
        sessionLock.lock();
        
        try
        {
            List<Session> cullList = new ArrayList<Session>();
            
            for(Session session: sessionIdMap.values())
            {
                if(terminating)
                {
                    return;
                }
                if(System.currentTimeMillis()-session.getLastAccessed()>cullingThreshold)
                {
                    cullList.add(session);
                }
            }
            for(Session session: cullList)
            {
                if(terminating)
                {
                    return;
                }
                
                sessionIdMap.remove(session.getSessionId());
                
                if(sessionPersistence)
                {
                    sessionStorage.delete(session.getUserId());
                }
            }
        }
        catch(Exception e)
        {
            errorHandler.handleError(e);
        }
        finally
        {
            sessionLock.unlock();
        }
    }
}