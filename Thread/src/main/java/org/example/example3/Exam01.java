package org.example.example3;


public class Exam01 {
    public static void main(String[] args) {
        RunnableCounter counter = new RunnableCounter("counter", 5);
        Thread thread = new Thread(counter);

        thread.start();
        System.out.println("종료");
    }
}
