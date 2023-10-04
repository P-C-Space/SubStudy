package org.example.example5;

public class Quiz01 {
    public static void main(String[] args) {
        RunnableCounter runnableCounter1 = new RunnableCounter("counter1", 10);
        RunnableCounter runnableCounter2 = new RunnableCounter("counter2", 5);

        Thread thread1 = new Thread(runnableCounter1);
        Thread thread2 = new Thread(runnableCounter2);

        thread1.start();
        thread2.start();
    }
}

class RunnableCounter implements Runnable {
    String name;
    int maxCount;

    int count;
    boolean runningFlag;

    public RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 1;
        runningFlag = true;
    }

    @Override
    public void run() {
        try {
            while(runningFlag){
                Thread.sleep(1000);
                System.out.println(this.name + " : "  + count++);
                if(count > maxCount){
                    runningFlag = false;
                }
            }

        }catch (InterruptedException exception){
            System.out.println(exception.getMessage());
        }
    }
}