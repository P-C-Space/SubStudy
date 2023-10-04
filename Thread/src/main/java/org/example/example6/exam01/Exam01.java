package org.example.example6.exam01;

public class Exam01 {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter1 = new SharedCounter("counter1",10000);
        SharedCounter counter2 = new SharedCounter("counter2",10000);


        counter1.start();
        System.out.println(counter1.getName() + ": started");
        counter1.join();
        System.out.println(counter1.getName() + ": terminated");

        counter2.start();
        System.out.println(counter2.getName() + ": started");
        counter2.join();
        System.out.println(counter2.getName() + ": terminated");

        System.out.println("sharedCount : " + SharedCount.getCount());
    }
}
