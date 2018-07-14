package com.danielskwierawski.scheduler;

public class Checker {

    public static final int AMOUNT_OF_WORKING_HOURS_PER_DAY = 8;

    public static boolean checkStartHours(int[][] plan) {
        for (int i = 0; i < plan.length; i++) {
            for (int j = 1; j < plan[i].length; j++) {
                if (dayIsNotDayOff(plan[i][j])) {
                    if (dayStartsBeforeThanDayBefore(plan, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean dayIsNotDayOff(int i) {
        return i != 0;
    }

    private static boolean dayStartsBeforeThanDayBefore(int[][] plan, int i, int j) {
        return plan[i][j] < plan[i][j - 1];
    }

    public static boolean checkIfEveryWorkerDoesNotExceedGivenAmountOfWorkingHours(int[][] plan, int standardAmountOfWorkingHours) {
        for (int i = 0; i < plan.length; i++) {
            if (calculateWorkingHoursOfWorker(plan[i]) > standardAmountOfWorkingHours) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfEveryWorkerDoesNotHaveWorkingHoursUnderTheGivenStandard(int[][] plan, int standardAmountOfWorkingHours) {
        for (int i = 0; i < plan.length; i++) {
            if (calculateWorkingHoursOfWorker(plan[i]) < standardAmountOfWorkingHours) {
                return false;
            }
        }
        return true;
    }

    private static int calculateWorkingHoursOfWorker(int[] plan) {
        int amountOfWorkingHoursOfWorker = 0;
        for (int j = 0; j < plan.length; j++) {
            if (dayIsNotDayOff(plan[j])) {
                amountOfWorkingHoursOfWorker += AMOUNT_OF_WORKING_HOURS_PER_DAY;
            }
        }
        return amountOfWorkingHoursOfWorker;
    }
}
