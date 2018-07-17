package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleApp {

    public static void main(String[] args) {

        long timeStart;
        long timeStop;
        int counter = 15;
        long[] totals = new long[counter];

        final int length = 10;

        int[] plan = Creator.initializePlanWithGivenValue(length);

        System.out.println("1DimensionalPlan length=" + length + " HP ProBook 640 G3");
        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();
            do {
//            System.out.println(Arrays.toString(plan));
            } while (Creator.increaseByOne(plan));
            timeStop = System.nanoTime();
            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }

}
