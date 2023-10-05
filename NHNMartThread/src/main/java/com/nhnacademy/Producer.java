package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private Mart mart;
    private Thread thread;
    List<Item> itemList;

    public Producer(Mart mart) {
        thread = new Thread(this);
        this.mart = mart;
    }

    private void createItem() {
        itemList = new LinkedList<>();
        Item[] items = Item.values();
        for(int i = 0;i<5;i++){
            itemList.add(items[(int) (Math.random() * items.length)]);
        }
    }

    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            try {
                createItem();
                mart.put(itemList);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
