package com.mycompany.app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

// Referance https://www.educative.io/answers/how-do-you-dockerize-a-maven-project

public class App 
{
    public static void main( String[] args ) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String architecture = System.getProperty("os.arch");
            String response = "<html><body><h1> Hello World!!!! </h1><p>I am running on Processor architecture: " + architecture + "</p></body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
