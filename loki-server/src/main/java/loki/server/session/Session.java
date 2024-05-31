package loki.server.session;

import thor.Storable;

/**
 * A representation of a user's authenticated session.
 * Contains a session ID and user ID.
 */

public class Session implements Storable
{
    private static final long serialVersionUID = 1L;
    
    private String sessionId;
    private String userId;
    private long lastAccessed;
        
    protected Session(String sessionId, String userId, long lastAccessed)
    {
        this.sessionId = sessionId;
        this.userId = userId;
        this.lastAccessed = lastAccessed;
    }

    /**
     * @return      the session ID.
     */
    
    public String getSessionId()
    {
        return sessionId;
    }
    
    /**
     * @return      the user ID.
     */

    public String getUserId()
    {
        return userId;
    }

    protected long getLastAccessed()
    {
        return lastAccessed;
    }

    protected void setLastAccessed(long lastAccessed)
    {
        this.lastAccessed = lastAccessed;
    }
}