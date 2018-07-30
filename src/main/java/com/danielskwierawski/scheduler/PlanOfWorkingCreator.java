package com.danielskwierawski.scheduler;

public class PlanOfWorkingCreator {


    public static boolean[][] createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(int amountOfDays, int amountOfNonWorkingDays) {
        int amountOfCombinations = getAmountOfCombinations(amountOfDays, amountOfNonWorkingDays);
        boolean[][] result = new boolean[amountOfCombinations][];
        fillWithAppropriatePlan(amountOfDays, amountOfNonWorkingDays, result);
        return result;
    }

    private static void fillWithAppropriatePlan(int amountOfDays, int amountOfNonWorkingDays, boolean[][] result) {
        boolean[] plan = Creator.initializePlanOfWorkingDays(amountOfDays);
        int index = 0;
        do {
            if (Checker.checkAmountOfNonWorkingDays(plan, amountOfNonWorkingDays)) {
                result[index] = plan.clone();
                index++;
            }
        } while (Creator.increaseByOnePlanOfWorkingDays(plan));
    }

    private static int getAmountOfCombinations(int amountOfDays, int amountOfNonWorkingDays) {
        if (amountOfDays == amountOfNonWorkingDays) {
            return 1;
        }
        return ((factorial(amountOfDays)) / ((factorial(amountOfNonWorkingDays)) * factorial(amountOfDays - amountOfNonWorkingDays)));
    }

    private static int factorial(int input) {
        int result = 1;
        for (int factor = 2; factor <= input; factor++) {
            result *= factor;
        }
        return result;
    }

    public static boolean[][] initializePlanForWorkersFromGivenArray(int amountOfWorkers, boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays) {
        boolean[][] result = new boolean[amountOfWorkers][];
        for (int i = 0; i < amountOfWorkers; i++) {
            result[i] = everyPossibleCombinationOfWorkingAndNonWorkingDays[0].clone();
        }
        return result;
    }
}
