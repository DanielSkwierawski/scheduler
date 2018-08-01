package com.danielskwierawski.scheduler;

import java.util.Arrays;

import static com.danielskwierawski.scheduler.Checker.*;

public class Creator {
    public static int[][] initializePlan(int amountOfWorkers, int amountOfDays) {
        int[][] result = new int[amountOfWorkers][amountOfDays];
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            Arrays.fill(result[worker], EARLIEST_HOUR_OF_START_WORKING);
        }
        return result;
    }

    public static int[] initializePlan(int length) {
        int[] result = new int[length];
        Arrays.fill(result, EARLIEST_HOUR_OF_START_WORKING);
        return result;
    }

    public static boolean[] initializePlanOfWorkingDays(int length) {
        boolean[] result = new boolean[length];
        Arrays.fill(result, WORKING_DAY);
        return result;
    }

    public static boolean increaseByOne(int[][] givenPlan) {
        boolean overflow = false;
        int amountOfWorkers = givenPlan.length;
        for (int worker = (amountOfWorkers - 1); worker >= 0; worker--) {
            int amountOfDays = givenPlan[worker].length;
            for (int day = (amountOfDays - 1); day >= 0; day--) {
                if (dayIsNotDayOff(givenPlan[worker][day])) {
                    if (isNotLatestHourOfStartWorking(givenPlan[worker][day])) {
                        givenPlan[worker][day]+=STEP;
                    } else {
                        givenPlan[worker][day] = DAY_OFF;
                    }
                    overflow = false;
                } else {
                    givenPlan[worker][day] = EARLIEST_HOUR_OF_START_WORKING;
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
        return !overflow;
    }

    public static boolean increaseByOne(int[] givenPlan) {
        boolean overflow = false;
        int length = givenPlan.length;
        for (int i = (length - 1); i >= 0 ; i--) {
            if (dayIsNotDayOff(givenPlan[i])) {
                if (isNotLatestHourOfStartWorking(givenPlan[i])) {
                    givenPlan[i]+=STEP;
                } else {
                    givenPlan[i] = DAY_OFF;
                }
                overflow = false;
            } else {
                givenPlan[i] = EARLIEST_HOUR_OF_START_WORKING;
                overflow = true;
            }
            if (!overflow) {
                break;
            }
        }
        return !overflow;
    }

    public static boolean increaseByOnePlanOfWorkingDays(boolean[] givenPlan) {
        boolean overflow = false;
        int length = givenPlan.length;
        for (int i = (length - 1); i >= 0; i--) {
            if (isWorkingDay(givenPlan[i])) {
                setNonWorkingDay(givenPlan, i);
                overflow = false;
            } else {
                setWorkingDay(givenPlan, i);
                overflow = true;
            }
            if (!overflow) {
                break;
            }
        }
        return !overflow;
    }

    private static void setWorkingDay(boolean[] givenPlan, int i) {
        givenPlan[i] = true;
    }

    private static void setNonWorkingDay(boolean[] givenPlan, int i) {
        givenPlan[i] = false;
    }

    private static boolean isWorkingDay(boolean day) {
        return day == WORKING_DAY;
    }

    private static boolean isNotLatestHourOfStartWorking(int i) {
        return i != LATEST_HOUR_OF_START_WORKING;
    }

    public static int[] getTheBiggestValueForEachDay(int[][] workersCoverage) {
        int amountOfDaysFromCoverage = workersCoverage.length;
        int[] result = new int[amountOfDaysFromCoverage];
        for (int day = 0; day < amountOfDaysFromCoverage; day++) {
            int maxAmountOfNeededWorkers = 0;
            int amountOfHoursFromCoverage = workersCoverage[day].length;
            for (int hour = 0; hour < amountOfHoursFromCoverage; hour++) {
                if (workersCoverage[day][hour] > maxAmountOfNeededWorkers) {
                    maxAmountOfNeededWorkers = workersCoverage[day][hour];
                }
            }
            result[day] = maxAmountOfNeededWorkers;
        }
        return result;
    }
}
