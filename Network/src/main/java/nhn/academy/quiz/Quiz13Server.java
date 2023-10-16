package nhn.academy.quiz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Quiz13Server {
    /*
    아래의 요구 사항에 맞는 server를 구현해 보자.

실행시 서비스를 위한 port를 지정할 수 있다. 별도의 port 지정이 없는 경우, 1234를 기본으로 한다.

Server는 실행 후 대기 상태를 유지하고, client가 접속되면 client 정보를 출력한다.

Server는 하나 이상의 client가 접속되어도 동시에 지원 가능하도록 한다.

Server는 접속된 client로부터 ID를 받아 별도 관리한다.

Client 메시지 시작에 대상 client id가 추가되어 있는 경우, 해당 client에만 메시지를 전달한다.

대상 client id는 "@[ID] message"로 @ 다음에 붙여서 온다.

user1에 hello 메시지를 보내기 위해서는 "@user1 hello"로 보내면 된다.

제어 명령을 구현한다.

!list 명령은 접속되어 있는 client의 id list를 반환한다.

*/
    public static List<ChatHandler> chatHandlerList = new ArrayList<>();
    public static void main(String[] args) {
        int port = 1234;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
                System.out.println("Port 0 ~ 65535 까지 정수만");
                System.exit(1);
            }
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                ChatHandler handler = new ChatHandler(socket);
                chatHandlerList.add(handler);
                handler.start();
            }
        } catch (IOException ignore) {
            System.out.println("서비스 열기 실패");
        }
    }

}

class ChatHandler extends Thread {
    Socket socket;
    int bufferSize = 2048;
    private String ID;
    public ChatHandler(Socket socket) {
        this.socket = socket;
        System.out.println(socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "가 연결");
    }

    @Override
    public void run() {

        try (BufferedInputStream socketIn = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream socketOut = new BufferedOutputStream(socket.getOutputStream());) {
            byte[] buffer = new byte[bufferSize];
            int length;

            while((length = socketIn.read(buffer)) > 0){
                socketOut.write(buffer,0,length);
                if(buffer.toString().substring(0,2).equals("ID")){
                    System.out.println(buffer.toString().substring(3,buffer.length));
                }
                socketOut.flush();
            }
        } catch (IOException e) {
            System.out.println("연결 종료");
        }
    }
}