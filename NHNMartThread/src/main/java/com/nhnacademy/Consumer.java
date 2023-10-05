package com.nhnacademy;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
    private Mart mart;
    private Store store;
    private String name;
    private String toBuyItemName;

    public Consumer(String name, Mart mart) {
        Item[] items = Item.values();
        this.toBuyItemName = items[(int) (Math.random() * items.length)].getName();
        this.mart = mart;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            store = mart.getStore(toBuyItemName);
            store.sell(this);
            mart.exit(this);
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }
}
