package org.example.example5;

public class Quiz02 {
    public static void main(String[] args) {
        ThreadUnlimitedCounter thread = new ThreadUnlimitedCounter("hello");
        thread.start();
    }
}

class ThreadUnlimitedCounter extends Thread {
    String name;
    int count;

    boolean flag;

    public ThreadUnlimitedCounter(String name) {
        this.name = name;
        count = 0;
        this.flag = true;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                ++count;
                System.out.println(name + " : " + count);
                Thread.sleep(1000);
                if(count > 5){
                    throw new InterruptedException("이걸 원해?");
                }
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();

            }
        }
    }
}