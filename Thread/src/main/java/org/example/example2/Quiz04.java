package org.example.example2;

public class Quiz04 {
    public static void main(String[] args) {
        ThreadCounter counter1 = new ThreadCounter("counter1", 10);
        ThreadCounter counter2 = new ThreadCounter("counter2", 10);

        counter1.start();
        counter2.start();
    }
}
