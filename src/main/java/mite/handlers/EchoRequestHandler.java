package mite.handlers;

/**
 * Simple handler mostly for demonstration and debugging.
 */
public final class EchoRequestHandler {

    private static final String R = "\r";

    public static FunctionRequestHandler of() {
        return FunctionRequestHandler.of(request -> "<html>" +
            "<body>" +
            "<pre>" +
            "request =" + request          + R +
            "method  =" + request.method   + R +
            "filename=" + request.filename + R +
            "</pre>" +
            "</body>" +
            "</html>"
        );
    }

}
