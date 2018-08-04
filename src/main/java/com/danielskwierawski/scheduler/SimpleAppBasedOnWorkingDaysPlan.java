package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class SimpleAppBasedOnWorkingDaysPlan {

    public static void main(String[] args) {

        long timeStart;
        long timeStop;
        int counter = 1;
        long[] totals = new long[counter];

        final int amountOfDays = 4;
        final int amountOfNonWorkingDays = 1;
        final int amountOfWorkers = 3;
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//1
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//2
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//3
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//4
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//5
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  2,  2,  1,  1,  1,  1,  1,  1,  0,  0},//6
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  2,  2,  1,  1,  1,  1,  1,  1,  0,  0},//7
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//8
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//9
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//10
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//11
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//12
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},//13
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},//14
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//15
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//16
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//17
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//18
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//19
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},//20
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},//21
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//22
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//23
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//24
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//25
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//26
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},//27
//                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0}//28
        };
        final int[] workersCoverageFlat = Creator.getTheBiggestValueForEachDay(workersCoverage);
        final int amountOfDaysFromWorkersCoverage = workersCoverageFlat.length;

        boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = PlanOfWorkingCreator.createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays);
        final int amountOfCombinations = everyPossibleCombinationOfWorkingAndNonWorkingDays.length;
        boolean[][][] everyPossiblePlansOfWorkingAndNonWorkingDays = PlanOfWorkingCreator.createEveryPossiblePlansOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays, amountOfWorkers, workersCoverage);
        int amountOfPlans = everyPossiblePlansOfWorkingAndNonWorkingDays.length;
        System.out.println("amountOfDays=" + amountOfDays + " amountOfNonWorkingDays=" + amountOfNonWorkingDays + " amountOfWorkers=" + amountOfWorkers + " amountOfDaysFromWorkersCoverage=" + amountOfDaysFromWorkersCoverage + " amountOfCombinations=" + amountOfCombinations + " amountOfPlans=" + amountOfPlans);
        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();

            for (int plan = 0; plan < amountOfPlans; plan++) {
                System.out.println(Arrays.deepToString(everyPossiblePlansOfWorkingAndNonWorkingDays[plan]));
                int[][] currentPlan = Creator.createStandardPlanFromPlanOfWorkingAndNonWorkingDays(everyPossiblePlansOfWorkingAndNonWorkingDays[plan]);
                do {
                    if (Checker.checkStartHours(currentPlan)) {
                        if (Checker.checkIfWorkingCoverageIsFulfilled(currentPlan, workersCoverage)) {
                            System.out.println(Arrays.deepToString(currentPlan));
                        }
                    }
                } while (Creator.increaseOnlyWorkingDays(currentPlan));
            }

            timeStop = System.nanoTime();
            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }
}
