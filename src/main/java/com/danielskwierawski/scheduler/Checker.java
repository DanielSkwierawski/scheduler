package com.danielskwierawski.scheduler;

public class Checker {

    public static final int AMOUNT_OF_WORKING_HOURS_PER_DAY = 8;

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

    private static boolean dayIsNotDayOff(int hourOfStartWorking) {
        return hourOfStartWorking != 0;
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
                if (isWorkingCoverageNotFulfilledForGivenHour(plan, workersCoverage[day][currentHour], day, (currentHour + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isWorkingCoverageNotFulfilledForGivenHour(int[][] plan, int neededWorkersCoverage, int day, int currentHour) {
        int amountOfWorkers = plan.length;
        int amountOfAvailableWorkers = 0;
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            int hourOfStartWorking = plan[worker][day];
            if (dayIsNotDayOff(hourOfStartWorking)) {
                if (isWorkerWorkingAtGivenHour(currentHour, hourOfStartWorking)) {
                    amountOfAvailableWorkers++;
                }
            }
        }
        return amountOfAvailableWorkers < neededWorkersCoverage;
    }

    private static boolean isWorkerWorkingAtGivenHour(int givenHour, int hourOfStartWorking) {
        return givenHour >= hourOfStartWorking && givenHour < (hourOfStartWorking + AMOUNT_OF_WORKING_HOURS_PER_DAY);
    }
}
