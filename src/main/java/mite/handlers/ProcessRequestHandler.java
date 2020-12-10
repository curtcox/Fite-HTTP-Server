package mite.handlers;

import mite.*;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/**
 * Simple handler mostly for demonstration and debugging.
 */
public final class ProcessRequestHandler {

    public interface ParameterMap extends Function<HTTPRequest,List<String>>{}

    public static HTTPRequestHandler of() {
        return of(httpRequest -> Arrays.asList(httpRequest.filename.substring(1)));
    }

    public static HTTPRequestHandler of(ParameterMap f) {
        return FunctionRequestHandler.of(httpRequest -> run(f.apply(httpRequest)));
    }

    private static String run(List<String> params) {
        try {
            return runCommandForOutput(params);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<String> outputOf(Process process) {
        return new BufferedReader(new InputStreamReader(process.getInputStream())).lines();
    }

    private static String runCommandForOutput(List<String> params) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(params);
        Process process = builder.start();

        StringJoiner stringJoiner = new StringJoiner(System.getProperty("line.separator"));
        outputOf(process).iterator().forEachRemaining(stringJoiner::add);

        process.waitFor();
        process.destroy();

        return stringJoiner.toString();
    }

}
