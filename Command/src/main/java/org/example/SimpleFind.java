package org.example;

public class SimpleFind {
    private static String[] command = {"-ac","--path","-p"};
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {

            } else {
                System.out.println(args[i]);
            }

        }
    }
}
