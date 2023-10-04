package org.example.example8;

public class Worker implements Runnable {
    String name;

    public Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(getName() + " started ");
        try {
            Thread.sleep(1999);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println(getName() + " finished");
    }
}
