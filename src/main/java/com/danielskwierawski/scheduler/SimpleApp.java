package com.danielskwierawski.scheduler;

import java.util.Arrays;

import static com.danielskwierawski.scheduler.Checker.EARLIEST_HOUR_OF_START_WORKING;

public class SimpleApp {

    public static void main(String[] args) {
        final int amountOfWorkers = 2;
        final int amountOfDays = 2;
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;

        int[][] plan = Creator.initializePlanWithGivenValue(amountOfWorkers, amountOfDays, initValue);

        do {
            System.out.println(Arrays.deepToString(plan));
        } while (Creator.increaseByOne(plan));
    }

}
