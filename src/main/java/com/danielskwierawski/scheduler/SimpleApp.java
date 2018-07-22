package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleApp {

    public static void main(String[] args) {

        long timeStart;
        long timeStop;
        int counter = 15;
        long[] totals = new long[counter];

        final int length = 28;

        boolean[] plan = Creator.initializePlanOfWorkingDays(length);

        System.out.println("1DimensionalPlanOfWorkingDays length=" + length);
        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();
            do {
//            System.out.println(Arrays.toString(plan));
            } while (Creator.increaseByOnePlanOfWorkingDays(plan));
            timeStop = System.nanoTime();
            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }

}
