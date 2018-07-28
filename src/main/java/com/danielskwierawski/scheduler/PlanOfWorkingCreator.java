package com.danielskwierawski.scheduler;

public class PlanOfWorkingCreator {


    public static boolean[][] createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(int amountOfDays, int amountOfNonWorkingDays) {
        int amountOfCombinations = getAmountOfCombinations(amountOfDays, amountOfNonWorkingDays);
        return new boolean[0][];
    }

    public static int getAmountOfCombinations(int amountOfDays, int amountOfNonWorkingDays) {
        return (factorial(amountOfDays))/((factorial(amountOfNonWorkingDays))*(amountOfDays-amountOfNonWorkingDays));
    }

    private static int factorial(int input) {
        int result = 1;
        for (int factor = 2; factor <= input; factor++) {
            result *= factor;
        }
        return result;
    }
}
