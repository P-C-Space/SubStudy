package org.example.example7;

import java.util.concurrent.TimeUnit;

public class Quiz01 {
    public static void main(String[] args) {
        Ticker ticker = new Ticker("10n",40);
        Thread thread = new Thread(ticker);
        thread.start();
    }
}

class Ticker implements Runnable {
    int count;
    int maxCount;
    long Time;
    boolean isNanoSecond;

    public Ticker(String seconds, int maxCount) {
        count = 0;
        this.maxCount = maxCount;
        if (seconds.charAt(seconds.length() - 1) == 'n') {
            seconds = seconds.substring(0, seconds.length() - 1);
            isNanoSecond = true;
        }
        try {
            Time = Long.parseLong(seconds);
        } catch (NumberFormatException exception) {
            System.out.println("잘못된 값 입력");
        }

    }

    @Override
    public void run() {
        try {
            while (count < maxCount) {
                if (isNanoSecond) {
                    TimeUnit.NANOSECONDS.sleep(Time);
                } else {
                    Thread.sleep(Time);
                }
                System.out.println("tick");
                count++;
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }
}
