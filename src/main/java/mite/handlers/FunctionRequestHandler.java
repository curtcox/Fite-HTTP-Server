package mite.handlers;

import mite.*;

import java.util.function.Function;

/**
 * A handler that uses a function to produce its responses.
 */
public final class FunctionRequestHandler
    extends AbstractRequestHandler
{
    public interface RequestToString extends Function<HTTPRequest,String> {}

    final RequestToString f;

    public static FunctionRequestHandler of(RequestToString f) {
        return new FunctionRequestHandler(f);
    }

    private FunctionRequestHandler(RequestToString f) {
        this.f = f;
    }

    public HTTPResponse handle(HTTPRequest request) {
        return HTTPResponse.of(f.apply(request),StatusCode.OK);
    }

}
