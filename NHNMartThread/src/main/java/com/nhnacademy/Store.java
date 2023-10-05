package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;

public class Store {

    private int maxItemCount;
    private int itemCount;
    private String name;
    private List<Item> items;

    public Store(String name){
        this.name = name;
        this.itemCount = 0;
        this.maxItemCount = 10;
        items = new LinkedList<>();
    }


    public synchronized void put(Item item) {
        while (items.size() >= maxItemCount) {
            try {
                wait();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(item.getName() + " 적재");
        items.add(item);

        notifyAll();
    }

    public synchronized void sell(Consumer consumer) {
        while (items.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        Item item = items.remove(0);
        System.out.println(consumer.getName() + "가 " + item.getName() + " 구매");
        notifyAll();
    }
}
