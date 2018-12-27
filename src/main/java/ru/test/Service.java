package ru.test;

import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.PortRange;
import org.glassfish.grizzly.http.server.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Class Service is responsible for server handling
 * @author  Mikhaleva Maria
 * @version dated 27 Dec 2018
 */

public class Service {

    public static void main(String[] args) {
        /** Server listening to 8080 port
         * @see WebService#createServer
         */
        final HttpServer server = createServer("0.0.0.0", 8080);

        final ServerConfiguration config = server.getServerConfiguration();

        try {
            /** Marking way to server handler
             * @see Handler  */
            config.addHttpHandler(new Handler(), "/request");

            server.start();
            System.in.read();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

            server.shutdownNow();
        }
    }

    /**
     * Creation server procedure
     *
     * @param host - connection host
     * @param port - listening port
     */
    private static HttpServer createServer(String host, int port) {
        HttpServer server = new HttpServer();
        NetworkListener listener = new NetworkListener("listener", host, new PortRange(port));
        server.addListener(listener);
        return server;
    }

    /**
     * Server handler class
     */
    private static class Handler extends HttpHandler {

        /**
         * Server work procedure
         *
         * @param request  - request to server
         * @param response - response from server
         */
        @Override
        public void service(Request request, Response response) throws IOException {
            response.setContentType("text/plain");
            if ("POST".equals(request.getMethod().getMethodString())) {
                try {
                    File file = new File(IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8));
                    response.getWriter().write(Converter.returnResult(file.getAbsolutePath()));
                } catch (Exception e) {
                    response.getWriter().write("BAD REQUEST");
                }
            }
        }
    }
}
