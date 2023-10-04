package org.example.example6.exam01;


public class SharedCount {
    static int count;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        SharedCount.count = count;
    }

    public static void increment() {
        count++;
    }
}