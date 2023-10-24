package nhn.academy.shttpd;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;


public class Handler implements HttpHandler {
    private String root = "./";

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("GET")){
            Headers responseHeaders = exchange.getResponseHeaders();

            File folder = new File(root);
            File files[] = folder.listFiles();

            StringBuilder stringBuilder = new StringBuilder();

            for(File f : files){
                stringBuilder.append(f.getPath() + "\n");
            }
                String response = stringBuilder.toString();
            exchange.sendResponseHeaders(200,response.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();

        }
    }
}
