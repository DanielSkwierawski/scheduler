package com.danielskwierawski.scheduler;

public class CreatingArrayWithEveryCombination {

    public static void main(String[] args) {
        long timeStart;
        long timeStop;
        int counter = 15;
        long[] totals = new long[counter];

        final int amountOfDays = 28;
        final int amountOfNonWorkingDays = 8;
        System.out.println("amountOfDays=" + amountOfDays + " amountOfNonWorkingDays=" + amountOfNonWorkingDays);

        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();

            boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = PlanOfWorkingCreator.createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays);

            timeStop = System.nanoTime();
            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }
}
