package org.example;

public class Example2 {
    public static void main(String[] args) {
        int firstArg;
        if (args.length > 0) {
            try {
                firstArg = Integer.parseInt(args[0]);
            } catch (NumberFormatException exception) {
                System.err.println("Argument" + args[0] + " must be an integer. ");
                System.exit(1);
            }
        }
    }
}
