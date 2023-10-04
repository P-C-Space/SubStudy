package org.example.example7;

public class Quiz02 {
    public static void main(String[] args) {
        RunnableCounter counter1 = new RunnableCounter("counter1", 3);
        RunnableCounter counter2 = new RunnableCounter("counter2", 3);

        counter2.start();
        counter1.start();


        while (counter1.getThread().isAlive() || counter2.getThread().isAlive()) {}
        System.out.println("All threads stopped");
    }
}
