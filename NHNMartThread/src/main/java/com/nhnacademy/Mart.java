package com.nhnacademy;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mart {

    private HashMap<String,Store> stores;
    ExecutorService consumerPool;
    ExecutorService producerPool;

    public Mart() {
        stores = new HashMap<>();
        consumerPool = Executors.newFixedThreadPool(5);
        producerPool = Executors.newFixedThreadPool(1);
        init();
    }

    /*
    품목
     */
    private void init(){
        Item[] items = Item.values();

        for(Item item : items){
            stores.put(item.getName(),new Store(item.getName()));
        }
    }

    public Store getStore(String storeName){
        return stores.get(storeName);
    }

    public void shutdown() {
        consumerPool.shutdown();
        producerPool.shutdown();
    }

    public void put(List<Item> itemList){
        Store store;
        for(Item item : itemList){
             store = getStore(item.getName());
             store.put(item);
        }
    }

    public void enter(Consumer consumer) {
        consumerPool.submit(consumer);
    }


    public void exit(Consumer consumer) {
        System.out.println(consumer.getName() + " Mart에서 퇴장");
    }
}
