package org.example.example2;

public class Quiz02 {
    public static void main(String[] args) {
        ThreadCounter threadCounter = new ThreadCounter("threadCounter", 10);
        threadCounter.run();
    }
}

class ThreadCounter extends Thread {
    String name;
    int maxCount;

    public ThreadCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < maxCount; i++) {
                Thread.sleep(1000);
                System.out.println(this.name + " : " + (i + 1));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

