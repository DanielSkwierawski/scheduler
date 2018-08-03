package com.danielskwierawski.scheduler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanOfWorkingCreator {


    public static boolean[][] createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(int amountOfDays, int amountOfNonWorkingDays) {
        List<boolean[]> listOfCombinations = new ArrayList<>();
        fillWithAppropriatePlan(amountOfDays, amountOfNonWorkingDays, listOfCombinations);
        return listOfCombinations.toArray(new boolean[0][]);
    }

    private static void fillWithAppropriatePlan(int amountOfDays, int amountOfNonWorkingDays, List<boolean[]> newResult) {
        boolean[] plan = Creator.initializePlanOfWorkingDays(amountOfDays);
        do {
            if (Checker.checkAmountOfNonWorkingDays(plan, amountOfNonWorkingDays)) {
                if (Checker.checkAmountOfWorkingDaysInARow(plan)) {
                    newResult.add(plan.clone());
                }
            }
        } while (Creator.increaseByOnePlanOfWorkingDays(plan));
    }

    static int getAmountOfCombinations(int amountOfDays, int amountOfNonWorkingDays) {
        if (amountOfDays == amountOfNonWorkingDays) {
            return 1;
        }
        return factorial(amountOfDays).divide(factorial(amountOfNonWorkingDays).multiply(factorial(amountOfDays - amountOfNonWorkingDays))).intValueExact();
    }

    static BigInteger factorial(int input) {
        BigInteger result = BigInteger.valueOf(1);
        for (int factor = 2; factor <= input; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
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

    public static boolean increaseByOne(boolean[][] plan, boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays) {
        final int lenghtOfPlan = plan.length;
        boolean overflow = false;
        for (int i = (lenghtOfPlan - 1); i >= 0; i--) {
            overflow = false;
            final int lengthOfEveryPossibleCombinationOfWorkingAndNonWorkingDays = everyPossibleCombinationOfWorkingAndNonWorkingDays.length;
            int j = 0;
            for (; j < (lengthOfEveryPossibleCombinationOfWorkingAndNonWorkingDays); j++) {
                if (Arrays.equals(plan[i], everyPossibleCombinationOfWorkingAndNonWorkingDays[j])) {
                    break;
                }
            }
            if ((j + 1) < lengthOfEveryPossibleCombinationOfWorkingAndNonWorkingDays) {
                plan[i] = everyPossibleCombinationOfWorkingAndNonWorkingDays[j + 1].clone();
            } else {
                plan[i] = everyPossibleCombinationOfWorkingAndNonWorkingDays[0].clone();
                overflow = true;
            }
            if (!overflow) {
                break;
            }
        }
        return !overflow;
    }

    public static int[] initializePlanOfIndexes(int amountOfWorkers) {
        return new int[amountOfWorkers];
    }

    public static boolean increaseByOnePlanOfIndexes(int[] planOfIndexes, int amountOfCombinations) {
        final int lenghtOfPlanOfIndexes = planOfIndexes.length;
        boolean overflow = false;
        for (int i = (lenghtOfPlanOfIndexes - 1); i >= 0; i--) {
            overflow = false;
            if (planOfIndexes[i] < (amountOfCombinations - 1)) {
                planOfIndexes[i]++;
            } else {
                planOfIndexes[i] = 0;
                overflow = true;
            }
            if (!overflow) {
                break;
            }
        }
        return !overflow;
    }

    public static boolean[][] initializePlanForWorkersFromPlanOfIndexes(int[] planOfIndexes, boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays) {
        final int amountOfWorkers = planOfIndexes.length;
        boolean[][] result = new boolean[amountOfWorkers][];
        for (int i = 0; i < amountOfWorkers; i++) {
            result[i] = everyPossibleCombinationOfWorkingAndNonWorkingDays[planOfIndexes[i]];
        }
        return result;
    }

    public static boolean[][][] createEveryPossiblePlansOfWorkingAndNonWorkingDays(int amountOfDays, int amountOfNonWorkingDays, int amountOfWorkers, int[][] workersCoverage) {
        List<boolean[][]> listOfPlans = new ArrayList<>();
        int[] workersCoverageFlat = Creator.getTheBiggestValueForEachDay(workersCoverage);
        boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = PlanOfWorkingCreator.createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays);
        final int amountOfCombinations = everyPossibleCombinationOfWorkingAndNonWorkingDays.length;
        int[] planOfIndexes = PlanOfWorkingCreator.initializePlanOfIndexes(amountOfWorkers);
        do {
            boolean[][] plan = PlanOfWorkingCreator.initializePlanForWorkersFromPlanOfIndexes(planOfIndexes, everyPossibleCombinationOfWorkingAndNonWorkingDays);
            if (Checker.checkIfWorkingCoverageCouldBeFulfilledFromFlatWorkersCoverage(plan, workersCoverageFlat)) {
                listOfPlans.add(plan);
            }
        } while (PlanOfWorkingCreator.increaseByOnePlanOfIndexes(planOfIndexes, amountOfCombinations));
        return listOfPlans.toArray(new boolean[0][][]);
    }
}
