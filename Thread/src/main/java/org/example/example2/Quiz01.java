package org.example.example2;

public class Quiz01 {

    public static void main(String[] args) {
        Counter counter = new Counter("counter", 10);
        counter.run();
    }
}

class Counter {
    String name;
    int maxCount;

    public Counter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    public void run() {
        try {
            for (int i = 1; i <= maxCount; i++) {
                Thread.sleep(1000);
                System.out.println(this.name + " : " + i);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}