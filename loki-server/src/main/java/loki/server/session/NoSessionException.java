package loki.server.session;

/**
 * Thrown by the {@link SessionManager} if, when retrieving the user owning a specific session, that session was not found.
 */

public class NoSessionException extends Exception
{
    private static final long serialVersionUID = 1L;
}
