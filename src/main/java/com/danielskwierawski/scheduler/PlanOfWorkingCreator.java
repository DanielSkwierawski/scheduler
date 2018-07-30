package com.danielskwierawski.scheduler;

public class PlanOfWorkingCreator {


    public static boolean[][] createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(int amountOfDays, int amountOfNonWorkingDays) {
        int amountOfCombinations = getAmountOfCombinations(amountOfDays, amountOfNonWorkingDays);
        return new boolean[0][];
    }

    public static int getAmountOfCombinations(int amountOfDays, int amountOfNonWorkingDays) {
        if (amountOfDays == amountOfNonWorkingDays) {
            return 1;
        }
        return ((factorial(amountOfDays)) / ((factorial(amountOfNonWorkingDays)) * factorial(amountOfDays - amountOfNonWorkingDays)));
    }

    public static int factorial(int input) {
        int result = 1;
        for (int factor = 2; factor <= input; factor++) {
            result *= factor;
        }
        return result;
    }
}
