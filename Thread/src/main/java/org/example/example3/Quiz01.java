package org.example.example3;

public class Quiz01 {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableCounter("runnableCounter", 10));
        thread.start();
    }
}

class RunnableCounter implements Runnable {
    String name;
    int maxCount;

    public RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= maxCount; i++) {
                Thread.sleep(1000);
                System.out.println(this.name + " : "  + i);
            }
        }catch (InterruptedException exception){
            System.out.println(exception.getMessage());
        }
    }
}
