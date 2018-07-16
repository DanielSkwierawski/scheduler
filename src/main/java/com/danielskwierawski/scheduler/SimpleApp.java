package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleApp {

    public static void main(String[] args) {

        long timeStart;
        long timeStop;
        int counter = 15;
        long[] totals = new long[counter];

        final int amountOfWorkers = 1;
        final int amountOfDays = 10;

        int[][] plan = Creator.initializePlanWithGivenValue(amountOfWorkers, amountOfDays);

        System.out.println("amountOfWorkers=" + amountOfWorkers + " amountOfDays:" + amountOfDays);
        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();
            do {
//            System.out.println(Arrays.deepToString(plan));
            } while (Creator.increaseByOne(plan));
            timeStop = System.nanoTime();
            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }

}
