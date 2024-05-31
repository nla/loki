package loki.server.session;

/**
 * Thrown by the {@link SessionManager} class if an attempt is made to retrieve or create a session after termination.
 */

public class SessionManagerTerminatedException extends Exception
{
    private static final long serialVersionUID = 1L;
}
