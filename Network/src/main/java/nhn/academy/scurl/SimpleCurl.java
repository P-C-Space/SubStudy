package nhn.academy.scurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SimpleCurl {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        URL url = null;
        HttpURLConnection http = null;
        String method = "GET";
        Options options = addoption();
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);


            if (cmd.hasOption("v")) {
                List<String> arg = List.of(cmd.getArgs());
                url = new URL(arg.get(0));

                http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod(method);


                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String userAgent = "";
                String accept = "";
                String message;
                List<String> messageList = new LinkedList<>();
                while ((message = bufferedReader.readLine()) != null) {
                    if (message.contains("User-Agent")) {
                        userAgent = message.replaceAll("\"", "").trim();
                    } else if (message.contains("Accept")) {
                        accept = message.replaceAll("\"", "").trim();
                    }
                    messageList.add(message);
                }

                System.out.println(http.getHeaderFields());
                stringBuilder.append("* Connected to " + http.getURL().getHost() + " port " + http.getURL().getPort())
                        .append("\n> " + http.getRequestMethod() + " " + http.getURL().getPath() + " " +
                                http.getHeaderField(0).split(" ")[0])
                        .append("\n> Host: " + http.getURL().getHost())
                        .append("\n> " + userAgent)
                        .append("\n> " + accept)
                        .append("\n>")
                        .append("\n< " + http.getHeaderField(0))
                        .append("\n< Date: " + http.getHeaderField("Date"))
                        .append("\n< Content-Type: " + http.getHeaderField("Content-Type"))
                        .append("\n< Content-Length: " + http.getHeaderField("Content-Length"))
                        .append("\n< Connection: " + http.getHeaderField("Connection"))
                        .append("\n< Server: " + http.getHeaderField("Server"))
                        .append("\n< Access-Control-Allow-Origin: " +
                                http.getHeaderField("Access-Control-Allow-Origin"))
                        .append("\n< Access-Control-Allow-Credentials: " +
                                http.getHeaderField("Access-Control-Allow-Credentials"))
                        .append("\n< \n");

                System.out.println(stringBuilder);

            }
            if (cmd.hasOption("X")) {
                System.out.println("옵션 -X 실행");
                List<String> arg = List.of(cmd.getArgs());
                method = cmd.getOptionValue("X");
                url = new URL(arg.get(0));

                if (!method.equals("GET")) {
                    http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod(method);
                }
            }
            
            if (cmd.hasOption("H")) {
                String requestHeader = cmd.getOptionValue("H");
                requestHeader.replaceAll("\"", "");

                String[] requestHeaderList = requestHeader.split(" ");

                int length = requestHeaderList[0].length();
                requestHeaderList[0] = requestHeaderList[0].substring(0, length - 1);

                List<String> arg = List.of(cmd.getArgs());
                url = new URL(arg.get(0));

                http = (HttpURLConnection) url.openConnection();
                http.addRequestProperty(requestHeaderList[0], requestHeaderList[1]);
            }
            if (url == null) {
                url = new URL(args[0]);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String message;
            while ((message = bufferedReader.readLine()) != null) {
                System.out.println(message);
            }


        } catch (ParseException e) {
            System.out.println("잘못된 인수");
        } catch (IOException e) {
            System.out.println("잘못된 URL");
        }
    }


    static Options addoption() {
        Options options = new Options();

        Option verbose = new Option("v", "verbose, 요청, 응답 헤더를 출력합니다.");

        Option header = Option.builder("H")
                .argName("line")
                .hasArg()
                .desc("임의의 헤더를 서버로 전송합니다.")
                .build();

        Option data = Option.builder("d")
                .argName("data")
                .hasArg()
                .desc("POST, PUT 등에 데이타를 전송합니다.")
                .build();

        Option method = Option.builder("X")
                .argName("command")
                .hasArg()
                .desc("사용할 method 를 지정합니다. 지정되지 않은 경우 기본값은 GET 입니다.")
                .build();

        Option response = new Option("L", "서버의 응답이 30x 계열이면 다음 응답을 따라 갑니다.");

        Option fileName = Option.builder("F")
                .argName("name")
                .hasArg()
                .desc("multipart/form-data 를 구성하여 전송합니다. content 부분에 @filename 을 사용할 수 있습니다.")
                .build();

        options.addOption(verbose);
        options.addOption(header);
        options.addOption(data);
        options.addOption(method);
        options.addOption(response);
        options.addOption(fileName);

        return options;
    }
}
