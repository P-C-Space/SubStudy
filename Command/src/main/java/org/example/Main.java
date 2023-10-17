package org.example;

import java.util.Arrays;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("v", "version", false, "print the version");
        options.addOption("t", "date", false, "print the data");
        options.addOption("a", false, "a 옵션이 추가되어 있습니다.");
        options.addOption("b", false, "b 옵션이 추가되어 있습니다.");
        options.addOption("c", true, "국가 코드");
        options.addOption("A", true, "을 추가합니다.");
        options.addOption("R", "remove", true, "을 제거합니다.");


        Option help = new Option("help", "print this message");
        Option projecthelp = new Option("projecthelp", "print project help information");
        Option version = new Option("version", "print the version information and exit");
        Option quiet = new Option("quiet", "be extra quiet");
        Option verbose = new Option("verbose", "be extra verbose");
        Option debug = new Option("debug", "print debugging information");
        Option emacs = new Option("emacs",
                "produce logging information without adornments");

        Option logfile = Option.builder("logfile")
                .argName("file")
                .hasArg()
                .desc("use given file for log")
                .build();

        Option logger = Option.builder("logger")
                .argName("classname")
                .hasArg()
                .desc("the class which it to perform logging")
                .build();

        Option listener = Option.builder("listener")
                .argName("classname")
                .hasArg()
                .desc("add an instance of class as "
                        + "a project listener")
                .build();

        Option buildfile = Option.builder("buildfile")
                .argName("file")
                .hasArg()
                .desc("use given buildfile")
                .build();

        Option find = Option.builder("find")
                .argName("file")
                .hasArg()
                .desc("search for buildfile towards the "
                        + "root of the filesystem and use it")
                .build();

        Option property  = Option.builder("D")
                .hasArgs()
                .valueSeparator('=')
                .build();

        options.addOption(help);
        options.addOption(projecthelp);
        options.addOption(version);
        options.addOption(quiet);
        options.addOption(verbose);
        options.addOption(debug);
        options.addOption(emacs);
        options.addOption(logfile);
        options.addOption(logger);
        options.addOption(listener);
        options.addOption(buildfile);
        options.addOption(find);
        options.addOption(property);


        CommandLineParser parser = new DefaultParser();
        try {


            /*
            연습 2
             */
//            if (cmd.hasOption("t")) {
//                LocalDateTime dateTime = LocalDateTime.now();
//                String formatDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
//                System.out.println(formatDateTime);
//            } else {
//                LocalTime time = LocalTime.now();
//                String formatTime = time.format(DateTimeFormatter.ofPattern("HH시 mm분 ss초"));
//                System.out.println(formatTime);
//            }

            /*
            얀습 3
             */
//            String countryCode = cmd.getOptionValue("c");
//
//            if (countryCode == null) {
//                // 기본 날짜 출력
//            } else {
//                // 지정된 국가의 날짜 출력
//            }

            /*
            연습 4
             */
//            CommandLine cmd = parser.parse(options, args);
//            if (cmd.hasOption("a")) {
//                System.out.println(
//                        "-" + options.getOption("a").getOpt() + " : " + options.getOption("a").getDescription());
//            }
//            if (cmd.hasOption("b")) {
//                System.out.println(
//                        "-" + options.getOption("b").getOpt() + " : " + options.getOption("b").getDescription());
//            }
//            if (cmd.hasOption("v")) {
//                System.out.println(
//                        "-" + options.getOption("v").getOpt() + " : " + options.getOption("v").getDescription());
//            }


            /*
            연습 5
             */
//            if (cmd.hasOption("A")) {
//                System.out.println(cmd.getOptionValue("A") + options.getOption("A").getDescription());
//            }
//            if (cmd.hasOption("R")) {
//                System.out.println(cmd.getOptionValue("R") + options.getOption("R").getDescription());
//            }

            CommandLine cmd = parser.parse(options, args);

            for(Option option : cmd.getOptions()) {
                if (option.hasArgs()) {
                    System.out.println('-' + option.getOpt() + " : "
                            + Arrays.toString(option.getValues()));
                } else if (option.hasArg()) {
                    System.out.println('-' + option.getOpt() + " : "
                            + option.getValue());
                } else {
                    System.out.println('-' + option.getOpt());
                }
            }
        } catch (ParseException e) {
            System.err.println("잘못된 인수");
        }



    }
}