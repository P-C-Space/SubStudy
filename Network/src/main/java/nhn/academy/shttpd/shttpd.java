package nhn.academy.shttpd;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class shttpd {
    public static void main(String[] args) {
        int port = 8000;
        HttpServer httpServer = null;

        try {
            if (args.length > 0) {
                port = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e){
            System.out.println(e.toString());
        }

        try {
            httpServer = HttpServer.create(new InetSocketAddress(port),0);
            httpServer.createContext("/", new Handler());
            server.createContext("/file-path", new FileHandler());
            server.createContext("/upload", new FileUploadHandler());
            server.createContext("/delete", new FileDeleteHandler());

            httpServer.setExecutor(Executors.newCachedThreadPool());
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//import com.sun.net.httpserver.HttpServer;
//        import com.sun.net.httpserver.HttpHandler;
//        import com.sun.net.httpserver.HttpExchange;
//
//        import java.io.*;
//        import java.net.InetSocketAddress;
//        import java.nio.file.Files;
//        import java.nio.file.Path;
//        import java.nio.file.StandardCopyOption;
//
//public class SimpleHttpServer {
//    public static void main(String[] args) throws Exception {
//        int port = args.length > 0 ? Integer.parseInt(args[0]) : 80;
//
//        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
//        server.createContext("/", new RootHandler());
//        server.createContext("/file-path", new FileHandler());
//        server.createContext("/upload", new FileUploadHandler());
//        server.createContext("/delete", new FileDeleteHandler());
//        server.setExecutor(null); // 기본 executor 사용
//        server.start();
//
//        System.out.println("Server is running on port " + port);
//    }
//
//    static class RootHandler implements HttpHandler {
//        @Override
//        public void handle(HttpExchange exchange) throws IOException {
//            if ("GET".equals(exchange.getRequestMethod())) {
//                File directory = new File(System.getProperty("user.dir"));
//                File[] files = directory.listFiles();
//
//                StringBuilder response = new StringBuilder("<html><body><ul>");
//                for (File file : files) {
//                    response.append("<li>").append(file.getName()).append("</li>");
//                }
//                response.append("</ul></body></html>");
//
//                byte[] responseBytes = response.toString().getBytes();
//                exchange.getResponseHeaders().set("Content-Type", "text/html");
//                exchange.sendResponseHeaders(200, responseBytes.length);
//                OutputStream os = exchange.getResponseBody();
//                os.write(responseBytes);
//                os.close();
//            }
//        }
//    }
//
//    static class FileHandler implements HttpHandler {
//        @Override
//        public void handle(HttpExchange exchange) throws IOException {
//            if ("GET".equals(exchange.getRequestMethod())) {
//                String path = exchange.getRequestURI().getPath().substring("/file-path".length());
//                File file = new File(System.getProperty("user.dir"), path);
//
//                if (!file.getAbsolutePath().startsWith(System.getProperty("user.dir"))) {
//                    sendErrorResponse(exchange, 403, "Forbidden");
//                    return;
//                }
//
//                if (!file.exists() || !file.isFile()) {
//                    sendErrorResponse(exchange, 404, "Not Found");
//                    return;
//                }
//
//                byte[] fileContent = Files.readAllBytes(file.toPath());
//                Headers headers = exchange.getResponseHeaders();
//                headers.set("Content-Type", getMimeType(file.getName()));
//                headers.set("Content-Length", String.valueOf(fileContent.length));
//                exchange.sendResponseHeaders(200, fileContent.length);
//                OutputStream os = exchange.getResponseBody();
//                os.write(fileContent);
//                os.close();
//            }
//        }
//    }
//
//    static class FileUploadHandler implements HttpHandler {
//        @Override
//        public void handle(HttpExchange exchange) throws IOException {
//            if ("POST".equals(exchange.getRequestMethod()) && exchange.getRequestHeaders().get("Content-Type").contains("multipart/form-data")) {
//                try (InputStream is = exchange.getRequestBody()) {
//                    String contentDisposition = exchange.getRequestHeaders().getFirst("Content-Disposition");
//                    String[] contentDispositionParts = contentDisposition.split(";");
//                    String filename = contentDispositionParts[2].trim().split("=")[1].replace("\"", "");
//                    Path uploadPath = new File(System.getProperty("user.dir"), filename).toPath();
//                    if (Files.exists(uploadPath)) {
//                        sendErrorResponse(exchange, 409, "Conflict");
//                        return;
//                    }
//                    Files.copy(is, uploadPath, StandardCopyOption.REPLACE_EXISTING);
//                    exchange.sendResponseHeaders(204, -1);
//                }
//            }
//        }
//    }
//
//    static class FileDeleteHandler implements HttpHandler {
//        @Override
//        public void handle(HttpExchange exchange) throws IOException {
//            if ("DELETE".equals(exchange.getRequestMethod())) {
//                String path = exchange.getRequestURI().getPath().substring("/delete".length());
//                File file = new File(System.getProperty("user.dir"), path);
//
//                if (!file.getAbsolutePath().startsWith(System.getProperty("user.dir"))) {
//                    sendErrorResponse(exchange, 403, "Forbidden");
//                    return;
//                }
//
//                if (!file.exists() || !file.isFile()) {
//                    exchange.sendResponseHeaders(204, -1);
//                } else if (file.delete()) {
//                    exchange.sendResponseHeaders(204, -1);
//                } else {
//                    sendErrorResponse(exchange, 403, "Forbidden");
//                }
//            }
//        }
//    }
//
//    private static void sendErrorResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
//        exchange.sendResponseHeaders(statusCode, message.length());
//        OutputStream os = exchange.getResponseBody();
//        os.write(message.getBytes());
//        os.close();
//    }
//
//    private static String getMimeType(String fileName) {
//        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
//            return "text/html";
//        } else if (fileName.endsWith(".txt")) {
//            return "text/plain";
//        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
//            return "image/jpeg";
//        } else if (fileName.endsWith(".png")) {
//            return "image/png";
//        } else if (fileName.endsWith(".pdf")) {
//            return "application/pdf";
//        } else {
//            return "application/octet-stream";
//        }
//    }
//}