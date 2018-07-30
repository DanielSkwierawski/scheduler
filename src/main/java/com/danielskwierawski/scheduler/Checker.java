package com.danielskwierawski.scheduler;

import java.util.Arrays;

public class Checker {

    public static final int AMOUNT_OF_WORKING_HOURS_PER_DAY = 8;
    public static final int DAY_OFF = -1;
    public static final int EARLIEST_HOUR_OF_START_WORKING = 6;
    public static final int STEP = 2;
    public static final int LATEST_HOUR_OF_START_WORKING = 14;
    public static final boolean WORKING_DAY = true;
    public static final boolean NON_WORKING_DAY = false;

    public static boolean checkStartHours(int[][] plan) {
        int amountOfWorkers = plan.length;
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            int amountOfDays = plan[worker].length;
            for (int day = 1; day < amountOfDays; day++) {
                if (dayIsNotDayOff(plan[worker][day])) {
                    if (dayStartsBeforeThanPreviousDay(plan, worker, day)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean dayIsNotDayOff(int hourOfStartWorking) {
        return hourOfStartWorking != DAY_OFF;
    }

    private static boolean dayStartsBeforeThanPreviousDay(int[][] plan, int worker, int day) {
        return plan[worker][day] < plan[worker][day - 1];
    }

    public static boolean checkIfEveryWorkerDoesNotExceedGivenAmountOfWorkingHours(int[][] plan, int standardAmountOfWorkingHours) {
        int amountOfWorkers = plan.length;
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            if (isStandardAmountOfWorkingHoursExceeded(plan[worker] , standardAmountOfWorkingHours)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfEveryWorkerDoesNotHaveWorkingHoursUnderTheGivenStandard(int[][] plan, int standardAmountOfWorkingHours) {
        int amountOfWorkers = plan.length;
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            if (isGivenStandardBiggerThanWorkerWorkingHours(plan[worker], standardAmountOfWorkingHours)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStandardAmountOfWorkingHoursExceeded(int[] planOfWorker, int standardAmountOfWorkingHours) {
        return calculateWorkingHoursOfWorker(planOfWorker) > standardAmountOfWorkingHours;
    }

    public static boolean isGivenStandardBiggerThanWorkerWorkingHours(int[] planOfWorker, int standardAmountOfWorkingHours) {
        return calculateWorkingHoursOfWorker(planOfWorker) < standardAmountOfWorkingHours;

    }

    private static int calculateWorkingHoursOfWorker(int[] planOfWorker) {
        int amountOfDays = planOfWorker.length;
        int amountOfWorkingHoursOfWorker = 0;
        for (int day = 0; day < amountOfDays; day++) {
            if (dayIsNotDayOff(planOfWorker[day])) {
                amountOfWorkingHoursOfWorker += AMOUNT_OF_WORKING_HOURS_PER_DAY;
            }
        }
        return amountOfWorkingHoursOfWorker;
    }

    public static boolean checkIfWorkingCoverageIsFulfilled(int[][] plan, int[][] workersCoverage) {
        int amountOfDaysFromCoverage = workersCoverage.length;
        for (int day = 0; day < amountOfDaysFromCoverage; day++) {
            int amountOfHoursFromCoverage = workersCoverage[day].length;
            for (int currentHour = 0; currentHour < amountOfHoursFromCoverage; currentHour++) {
                if (isWorkingCoverageNotFulfilledForGivenHour(plan, workersCoverage[day][currentHour], day, (currentHour))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkIfWorkingCoverageCouldBeFulfilled(boolean[][] plan, int[][] workersCoverage) {
        int amountOfDaysFromCoverage = workersCoverage.length;
        for (int day = 0; day < amountOfDaysFromCoverage; day++) {
            int maxAmountOfNeededWorkers = Arrays.stream(workersCoverage[day]).max().getAsInt();
            int amountOfWorkersFromPlan = plan.length;
            int amountOfAvailableWorkers = 0;
            for (int worker = 0; worker < amountOfWorkersFromPlan; worker++) {
                if (plan[worker][day]==true) {
                    amountOfAvailableWorkers++;
                }
            }
            if (maxAmountOfNeededWorkers > amountOfAvailableWorkers) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWorkingCoverageNotFulfilledForGivenHour(int[][] plan, int neededWorkersCoverage, int day, int currentHour) {
        int amountOfWorkers = plan.length;
        int amountOfAvailableWorkers = 0;
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            int hourOfStartWorkingOfWorker = plan[worker][day];
            if (dayIsNotDayOff(hourOfStartWorkingOfWorker)) {
                if (isWorkerWorkingAtGivenHour(currentHour, hourOfStartWorkingOfWorker)) {
                    amountOfAvailableWorkers++;
                }
            }
        }
        return amountOfAvailableWorkers < neededWorkersCoverage;
    }

    private static boolean isWorkerWorkingAtGivenHour(int givenHour, int hourOfStartWorking) {
        return givenHour >= hourOfStartWorking && givenHour < (hourOfStartWorking + AMOUNT_OF_WORKING_HOURS_PER_DAY);
    }

    public static boolean checkAmountOfNonWorkingDays(boolean[] plan, int amountOfNonWorkingDays) {
        int counterOfNonWorkingDays = 0;
        int lenght = plan.length;
        for (int i = 0; i < lenght; i++) {
            if (dayIsNonWorkingDay(plan[i])) {
                counterOfNonWorkingDays++;
            }
        }
        return counterOfNonWorkingDays == amountOfNonWorkingDays;
    }

    private static boolean dayIsNonWorkingDay(boolean day) {
        return day == NON_WORKING_DAY;
    }
}
