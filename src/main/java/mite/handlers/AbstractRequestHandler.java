package mite.handlers;

import mite.*;

/**
 * Skeletal implementation of HTTPRequestHandler that
 * determines if it can do something by trying to.
 */
public abstract class AbstractRequestHandler
    implements HTTPRequestHandler
{

    public final boolean handles(HTTPRequest request) {
        try {
            return handle(request)!=null;
        } catch (Throwable t) {
            debug(t);
            return false;
        }
    }

    private void debug(Throwable t) {
        t.printStackTrace();
    }
}
