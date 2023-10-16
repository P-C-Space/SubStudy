package com.nhnacademy;

import java.util.Random;

public class MonteCarlo {
    public static void main(String[] args) {
        Random random = new Random();
        double x, y;
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            x = random.nextDouble();
            y = random.nextDouble();

            if (x * x + y * y <= 1) {
                count++;
            }
        }

        System.out.println(count / 10000.0 * 4.0);



    }
}


