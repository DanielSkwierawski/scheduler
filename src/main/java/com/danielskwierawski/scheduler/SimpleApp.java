package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleApp {

    public static void main(String[] args) {
        final int amountOfWorkers = 2;
        final int amountOfDays = 2;

        int[][] plan = Creator.initializePlanWithGivenValue(amountOfWorkers, amountOfDays);

        do {
            System.out.println(Arrays.deepToString(plan));
        } while (Creator.increaseByOne(plan));
    }

}
