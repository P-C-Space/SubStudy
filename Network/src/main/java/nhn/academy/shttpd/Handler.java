package nhn.academy.shttpd;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

public class Handler implements HttpHandler {
    private String root = "./";

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String[] args = exchange.getRequestURI().toString().split("/");
        String requestMethod = exchange.getRequestMethod();

        if ("GET".equals(exchange.getRequestMethod())) {
            File directory = new File(System.getProperty("user.dir"));
            File[] files = directory.listFiles();

            StringBuilder response = new StringBuilder("<html><body><ul>");
            for (File file : files) {
                response.append("<li>").append(file.getName()).append("</li>");
            }
            response.append("</ul></body></html>");

            byte[] responseBytes = response.toString().getBytes();
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        }

//        if (requestMethod.equalsIgnoreCase("GET")){
//
//
//
//            System.out.println(exchange.getRequestURI());
//            System.out.println(exchange.getRequestURI().getPath());
//
//            File folder = new File(root);
//            File files[] = folder.listFiles();
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            for(File f : files){
//                stringBuilder.append(f.getPath() + "\n");
//            }
//            String response = stringBuilder.toString();
//            exchange.sendResponseHeaders(200,response.length());
//            OutputStream outputStream = exchange.getResponseBody();
//            outputStream.write(response.getBytes());
//            outputStream.close();
//
//        }
    }
}
