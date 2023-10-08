package nhn.academy.quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Quiz04 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;

        if(args.length > 0){
            host = args[0];
        }

        try {
            if (args.length > 1) {
                port = Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException ignore) {
            System.err.println("not same");
            System.exit(1);
        }

        try{
            Socket socket = new Socket(host,port);
            System.out.println("connected");

            OutputStream output = socket.getOutputStream();


            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                String line = reader.readLine();
                if(line.equals("exit")){
                    break;
                }

                output.write(line.getBytes());
                output.write("\n".getBytes());
                output.flush();

            }

            socket.close();
        } catch(ConnectException  e){
            System.out.println(host + " : ");
        } catch(IOException e){

        }
    }
}
