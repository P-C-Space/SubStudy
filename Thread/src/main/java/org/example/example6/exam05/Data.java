package org.example.example6.exam05;

public class Data {
    private String packet;

    private boolean transfer = true;

    public synchronized  String receive(){
        while(transfer){
            try{
                wait();
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = true;

        String returnPacket = packet;
        notifyAll();
        return returnPacket;
    }

    public synchronized void send(String packet){
        while(!transfer){
            try{
                wait();
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = false;

        this.packet = packet;
        notifyAll();
    }
}
