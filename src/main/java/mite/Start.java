package mite;

import mite.handlers.*;

import java.io.IOException;

public final class Start {

    public static void main(String[] args) throws IOException {
        MiteHTTPServer.startListeningOnPort(
                8000,
                CompositeRequestHandler.of(
                    ProcessRequestHandler.of(),
                    EchoRequestHandler.of(),
                    UnsupportedRequestHandler.of()
                )
         );
    }

}
