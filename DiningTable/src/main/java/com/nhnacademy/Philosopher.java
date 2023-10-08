package com.nhnacademy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {

    private Thread thread;
    private int id;
    private boolean isEating;


    Philosopher(int id) {
        thread = new Thread(this);
        isEating = false;
        this.id = id;
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        while (!isEating) {
            thinking();
            tryEat();
        }
        System.out.println("Philosopher " + id + "는 다먹고 나감 ㅂㅇ ^_^\n");
//        thread.interrupt();
    }

    public void pickUpLeft(Semaphore fork) throws InterruptedException {
        fork.acquire();
        System.out.println("Philosopher " + id + "는 왼쪽 포크를 들었습니다.");
    }

    public boolean pickUpRight(Semaphore fork) throws InterruptedException {
        return fork.tryAcquire();
    }

    public void putDownLeft(Semaphore fork) {
        System.out.println("Philosopher " + id + "는 왼쪽 포크를 내려놓습니다.");
        fork.release();
    }

    public void putDownRight(Semaphore fork) {
        System.out.println("Philosopher " + id + "는 오른쪽 포크를 내려놓습니니다.");
        fork.release();
    }

    public void thinking() {
        try {
            System.out.println("Philosopher " + id + "는 고뇌에 빠졌습니다.");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void tryEat() {
        try {
            pickUpLeft(Dining.forks[id]); // 대기중 집은 후-> 다음 고
            if (pickUpRight(Dining.forks[(id + 1) % 5])) { // 오른쪽 집어봐
                // 밥 쳐먹고 오른쪽 포크 내려놔
                System.out.println("Philosopher " + id + "는 오른쪽 포크를 들었습니다.");
                System.out.println("Philosopher " + id + " is Eating\n");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
                System.out.println("Philosopher " + id + "는 밥을 다쳐먹었어요 :)");
//                isEating = true;
                putDownRight(Dining.forks[(id + 1) % 5]);
            }
            putDownLeft(Dining.forks[id]); // 안집어져? 놔, 집었어도 놔
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
