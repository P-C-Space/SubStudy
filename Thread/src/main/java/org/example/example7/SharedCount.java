package org.example.example7;

public class SharedCount {
    int count = 0;

    public int getCount() {
        return count;
    }

    public synchronized void increment() {
        count++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
        }
    }
}