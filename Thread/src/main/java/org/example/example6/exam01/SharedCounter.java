package org.example.example6.exam01;

public class SharedCounter extends Thread {
    int count;
    int maxCount;

    public SharedCounter(String name, int maxCount) {
        setName(name);
        this.maxCount = maxCount;
        count = 0;
    }

    @Override
    public void run() {
        while (count < maxCount) {
            count++;
            SharedCount.increment();
        }
    }
}
