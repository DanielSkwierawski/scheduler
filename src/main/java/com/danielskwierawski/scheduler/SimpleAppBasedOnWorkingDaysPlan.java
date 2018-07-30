package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleAppBasedOnWorkingDaysPlan {

    public static void main(String[] args) {

        final int amountOfDays = 7;
        final int amountOfNonWorkingDays = 2;
        final int amountOfWorkers = 3;
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},
        };

        boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = PlanOfWorkingCreator.createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays);

        boolean[][] plan = PlanOfWorkingCreator.initializePlanForWorkersFromGivenArray(amountOfWorkers, everyPossibleCombinationOfWorkingAndNonWorkingDays);

        do {
            if (Checker.checkIfWorkingCoverageCouldBeFulfilled(plan, workersCoverage)) {
                System.out.println(Arrays.deepToString(plan));
            }

        } while (PlanOfWorkingCreator.increaseByOne(plan, everyPossibleCombinationOfWorkingAndNonWorkingDays));

    }

}
