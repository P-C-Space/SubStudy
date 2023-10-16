package com.nhnacademy;

import java.util.Random;
import java.util.stream.DoubleStream;

public class MonteCarloStream {
    public static void main(String[] args) {
        Random random = new Random();
        double maxCount = 10000.0;

        int count = 0;
        DoubleStream doubleStream = DoubleStream
                .generate(()->Math.pow(random.nextDouble(), 2.0) + Math.pow(random.nextDouble(), 2.0))
                .limit((int)maxCount)
                .filter(x -> x <= 1);
        count = (int) doubleStream.count();

        System.out.println(count / maxCount * 4.0);
    }
}
