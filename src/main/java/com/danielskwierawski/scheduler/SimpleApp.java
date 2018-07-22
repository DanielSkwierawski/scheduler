package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleApp {

    public static void main(String[] args) {

        long timeStart;
        long timeStop;
        int counter = 1;
        long[] totals = new long[counter];

        final int length = 28;
        final int amountOfNonWorkingDays = 8;

        boolean[] plan = Creator.initializePlanOfWorkingDays(length);

        System.out.println("1DimensionalPlanOfWorkingDays length=" + length);
        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();
            int amountOfCombination = 0;
            do {
                if (Checker.checkAmountOfNonWorkingDays(plan, amountOfNonWorkingDays)) {
                    amountOfCombination++;
                }
            } while (Creator.increaseByOnePlanOfWorkingDays(plan));
            System.out.println(amountOfCombination);
            timeStop = System.nanoTime();
            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }

}
