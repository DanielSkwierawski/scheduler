package com.danielskwierawski.scheduler;

import java.util.Arrays;

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

    public static int[][] increaseByOne(int[][] givenPlan) {
        boolean overflow = false;
        int[][] result = copyOf(givenPlan);
        int amountOfWorkers = result.length;
        for (int worker = (amountOfWorkers - 1); worker >= 0; worker--) {
            int amountOfDays = result[worker].length;
            for (int day = (amountOfDays - 1); day >= 0; day--) {
                if (isNotLatestHourOfStartWorking(result[worker][day])) {
                    result[worker][day]++;
                    overflow = false;
                } else {
                    result[worker][day] = DAY_OFF;
                    overflow = true;
                }
                if (!overflow) {
                    break;
                }
            }
            if (!overflow) {
                break;
            }
        }
        return result;
    }

    private static boolean isNotLatestHourOfStartWorking(int i) {
        return i != LATEST_HOUR_OF_START_WORKING;
    }

    private static int[][] copyOf(int[][] givenPlan) {
        int amountOfWorkers = givenPlan.length;
        int[][] result = new int[amountOfWorkers][];
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            result[worker] = Arrays.copyOf(givenPlan[worker], givenPlan[worker].length);
        }
        return result;
    }
}
