package loki.server.adapter.request;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import loki.Attributes;
import loki.ErrorHandler;
import loki.server.ServerOperations;
import loki.server.adapter.Adapter;
import loki.server.request.RequestServlet;
import loki.server.util.ServerUtils;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.net.httpserver.*;

/**
 * Used to listen for and respond to method calls via implementations of {@link com.google.gwt.user.client.rpc.RemoteService} from web clients made
 * over HTTP. This class creates a service that listens for GWT RPC requests over HTTP. This adapter can be used instead of embedding the application into
 * an application server framework. If using a third-party application server framework, this adapter need not be added to the adapter.properties file.
 */

@SuppressWarnings("restriction")
public class HTTPRequestAdapter<Ops extends ServerOperations> implements Adapter<Ops>
{
    private HttpServer server;
    private int shutdownDelay;

    private boolean allowCors;
    private String corsAllowedHosts;
    private String corsAllowedHeaders;

    public void initialise(Attributes<String> properties, ErrorHandler errorHandler, Ops operations) throws Exception
    {
        String hostName = properties.get("loki.http.request.adapter.host", "");
        int port = Integer.parseInt(properties.get("loki.http.request.adapter.port", "2901"));
        int backlog = Integer.parseInt(properties.get("loki.http.request.adapter.backlog", "100"));
        int threadPoolSize = Integer.parseInt(properties.get("loki.http.request.adapter.thread_pool", "10"));
        shutdownDelay = Integer.parseInt(properties.get("loki.http.request.adapter.max_shutdown_delay_seconds", "0"));
        allowCors = Boolean.parseBoolean(properties.get("loki.http.request.adapter.cors_allowed", "false"));
        corsAllowedHosts = properties.get("loki.http.request.adapter.cors_allowed_hosts", "");
        corsAllowedHeaders = properties.get("loki.http.request.adapter.cors_allowed_headers", "");

        ExecutorService executor = threadPoolSize == 0 ? Executors.newCachedThreadPool():Executors.newFixedThreadPool(threadPoolSize);

        server = HttpServer.create((hostName.isEmpty() ? new InetSocketAddress(port) : new InetSocketAddress(hostName, port)), backlog);
        server.setExecutor(executor);

        Attributes<String> mappings = ServerUtils.loadFromFile(properties.get("loki.http.request.adapter.servlet_mapping_file", "config/request_mappings.properties"));

        for(String key: mappings.keys())
        {
            @SuppressWarnings("unchecked")
            RequestServlet<Ops> servlet = (RequestServlet<Ops>) Class.forName(mappings.get(key)).getDeclaredConstructor().newInstance();
            servlet.setOperations(operations);
            server.createContext(key, new RequestHandler(servlet, errorHandler));
        }

        mappings = ServerUtils.loadFromFile(properties.get("loki.http.request.adapter.content_mapping_file", "config/content_mappings.properties"));

        for(String key: mappings.keys())
        {
            server.createContext(key, new StaticHandler(mappings.get(key), errorHandler));
        }

        server.start();
    }

    public void terminate()
    {
        server.stop(shutdownDelay<=0 ? Integer.MAX_VALUE:shutdownDelay);
    }

    private class RequestHandler implements HttpHandler
    {
        private static final String ENDLINE = "\n";
        private RemoteServiceServlet servlet;
        private ErrorHandler errorHandler;

        public RequestHandler(RemoteServiceServlet servlet, ErrorHandler errorHandler)
        {
            this.servlet = servlet;
            this.errorHandler = errorHandler;
        }

        public void handle(HttpExchange exchange) throws IOException
        {
            try
            {
                if(allowCors)
                {
                    exchange.getResponseHeaders().put("Access-Control-Allow-Headers", Arrays.asList(corsAllowedHeaders));
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Arrays.asList(corsAllowedHosts));
                }
                if(exchange.getRequestMethod().equals("OPTIONS"))
                {
                    if(allowCors)
                    {
                        exchange.sendResponseHeaders(200, 0);
                        exchange.close();
                        return;
                    }
                    else
                    {
                        exchange.sendResponseHeaders(400, 0);
                        exchange.close();
                        return;
                    }
                }

                RPCRequest rpcRequest = RPC.decodeRequest(getPayload(exchange), servlet.getClass(), null);
                byte[] data = servlet.processCall(rpcRequest).getBytes();

                exchange.sendResponseHeaders(200, data.length);
                exchange.getResponseBody().write(data);
                exchange.getResponseBody().flush();
                exchange.close();
                return;
            }
            catch(Exception e)
            {
                exchange.sendResponseHeaders(500, 0);
                exchange.close();
                errorHandler.handleError(e);
                throw new IOException(e);
            }
        }

        private String getPayload(HttpExchange exchange) throws IOException
        {
            StringBuilder b = new StringBuilder();

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody())))
            {
                String line;

                while((line = reader.readLine()) != null)
                {
                    b.append(line).append(ENDLINE);
                }
            }

            return b.toString();
        }
    }

    private class StaticHandler implements HttpHandler
    {
        private String directory;
        private ErrorHandler errorHandler;

        public StaticHandler(String directory, ErrorHandler errorHandler)
        {
            this.directory = directory;
            this.errorHandler = errorHandler;
        }

        public void handle(HttpExchange exchange) throws IOException
        {
            try
            {
                if(allowCors)
                {
                    exchange.getResponseHeaders().put("Access-Control-Allow-Headers", Arrays.asList(corsAllowedHeaders));
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Arrays.asList(corsAllowedHosts));
                }
                if(exchange.getRequestMethod().equals("OPTIONS"))
                {
                    if(allowCors)
                    {
                        exchange.sendResponseHeaders(200, 0);
                        exchange.close();
                        return;
                    }
                    else
                    {
                        exchange.sendResponseHeaders(400, 0);
                        exchange.close();
                        return;
                    }
                }

                String fileName = exchange.getRequestURI().toString();
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

                if(fileName.isEmpty())
                {
                    fileName = "index.html";
                }
                else
                {
                    int qIndex = fileName.indexOf("?");

                    if(qIndex >= 0)
                    {
                        fileName = fileName.substring(0, qIndex);
                    }
                }

                File file = new File("content/"+directory+"/"+fileName);

                if(file.exists())
                {
                    byte[] byteArray = Files.readAllBytes(file.toPath());
                    exchange.sendResponseHeaders(200, byteArray.length);
                    exchange.getResponseBody().write(byteArray);
                    exchange.getResponseBody().flush();
                }
                else
                {
                    exchange.sendResponseHeaders(400, 0);
                }

                exchange.close();
                return;
            }
            catch(Exception e)
            {
                exchange.sendResponseHeaders(500, 0);
                exchange.close();
                errorHandler.handleError(e);
                throw new IOException(e);
            }
        }
    }
}