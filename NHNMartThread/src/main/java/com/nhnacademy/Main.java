package com.nhnacademy;



public class Main {
    public static void main(String[] args) {

        Mart mart = new Mart();
        Producer producer = new Producer(mart);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        for (int i = 0; i < 15; i++) {
            mart.enter((new Consumer(("consumer" + (i + 1)), mart)));
        }

        mart.shutdown();
    }
}
