package com.nhnacademy;

import java.util.Iterator;
import java.util.Random;

public class MonteCarloIter {
    static Random random = new Random();
    static double maxCount = 10000.0;

    public static void main(String[] args) {
        Iterator<Double> iter = new Iterator<Double>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count >= maxCount;
            }

            @Override
            public Double next() {
                count++;
                return Math.pow(random.nextDouble(), 2.0) + Math.pow(random.nextDouble(), 2.0);
            }
        };

        int count = 0;
        while (iter.hasNext()) {
            if (iter.next() <= 1) {
                count++;
            }
        }

        System.out.println(count / maxCount * 4.0);
    }
}
