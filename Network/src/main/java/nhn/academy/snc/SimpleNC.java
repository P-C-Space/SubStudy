package nhn.academy.snc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SimpleNC {
    public static void main(String[] args) {


        int port = 1234;
        Options options = new Options();
        Option listen = Option.builder("l")
                .argName("hostname").argName("port")
                .hasArg()
                .desc("서버 모드로 동작, 입력 받은 포트로 listen")
                .build();
        // options.addOption("l", "listen", true, "서버 모드로 동작, 입력 받은 포트로 listen");
        options.addOption(listen);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('l')) {
                System.out.println("서버로 접속");

                if (cmd.getArgs().length < 1) {
                    throw new NullPointerException("없는 인자");
                }


                port = Integer.parseInt(cmd.getArgs()[0]);

                serverMode(port);
            } else {
                System.out.println("클라이언트로 접속");

                if (cmd.getArgs().length < 2) {
                    throw new NullPointerException("없는 인자");
                }

                port = Integer.parseInt(cmd.getArgs()[1]);
                clientMode(cmd.getArgs()[0], port);
            }
        } catch (ParseException ignore) {
            System.out.println("인수가 잘못되었습니다");
        } catch (NumberFormatException e){
            System.out.println("잘못된 포트 번호");
        }
    }

    public static void serverMode(int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("서버 열기 성공");
            Socket socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
            String message;
            while ((message = serverInput.readLine()) != null) {
                output.write(message.getBytes());
                output.flush();
            }

        } catch (IOException ignore) {
            System.out.println("서비스 열기 실패");
        }
    }

    static class ServerThread extends Thread {
        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
            System.out.println(socket.getInetAddress().getHostAddress() + " : " + socket.getPort() + "가 연결");
        }

        @Override
        public void run() {
            try {
                BufferedInputStream input = new BufferedInputStream(socket.getInputStream());

                byte[] buffer = new byte[2048];
                String message;
                int length;

                while ((length = input.read(buffer)) > 0) {
                    message = new String(buffer, 0, length);
                    System.out.println(message);
                }

            } catch (IOException e) {
                System.out.println("입출력 오류");
            }

        }
    }

    private static void clientMode(String host, int port) {

        try {
            Socket socket = new Socket(host, port);
            System.out.println("서버에 연결 되었습니다.");

            BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ClientThread thread = new ClientThread(socket);
            thread.start();

            String message;
            while (((message = terminalIn.readLine()) != null)) {
                writer.write(message);
                writer.flush();
            }

        } catch (ConnectException e) {
            System.out.println(host + " : " + port + "에 연결할 수 없습니다.");
        } catch (IOException e) {

        }
    }

    private static class ClientThread extends Thread {
        Socket socket;

        public ClientThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            char[] buffer = new char[2048];
            String message;
            int length;
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while ((length = input.read(buffer)) > 0) {
                    message = new String(buffer, 0, length);
                    System.out.println(message);
                }
                socket.close();
                System.exit(1);
            } catch (IOException ignore) {

            }
        }
    }
}
