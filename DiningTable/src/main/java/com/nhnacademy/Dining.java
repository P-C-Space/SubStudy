package com.nhnacademy;

import java.util.concurrent.Semaphore;

public class Dining {

    static Semaphore[] forks;
    private Philosopher[] philosophers;

    Dining() {
        forks = new Semaphore[5];
        philosophers = new Philosopher[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i);
            philosophers[i].start();
        }
    }
}