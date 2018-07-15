package com.danielskwierawski.scheduler;

import static com.danielskwierawski.scheduler.Checker.DAY_OFF;
import static com.danielskwierawski.scheduler.Checker.LATEST_HOUR_OF_START_WORKING;

public class Creator {
    public static int[][] initializePlanWithGivenValue(int amountOfWorkers, int amountOfDays, int initValue) {
        int[][] result = new int[amountOfWorkers][amountOfDays];
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            for (int day = 0; day < amountOfDays; day++) {
                result[worker][day] = initValue;
            }
        }
        return result;
    }

    public static void increaseByOne(int[][] givenPlan) {
        boolean overflow = false;
        int amountOfWorkers = givenPlan.length;
        for (int worker = (amountOfWorkers - 1); worker >= 0; worker--) {
            int amountOfDays = givenPlan[worker].length;
            for (int day = (amountOfDays - 1); day >= 0; day--) {
                if (isNotLatestHourOfStartWorking(givenPlan[worker][day])) {
                    givenPlan[worker][day]++;
                    overflow = false;
                } else {
                    givenPlan[worker][day] = DAY_OFF;
                    overflow = false;
                }
                if (!overflow) {
                    break;
                }
            }
            if (!overflow) {
                break;
            }
        }
    }

    private static boolean isNotLatestHourOfStartWorking(int i) {
        return i != LATEST_HOUR_OF_START_WORKING;
    }
}
