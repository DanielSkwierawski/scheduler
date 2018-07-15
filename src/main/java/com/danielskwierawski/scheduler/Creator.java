package com.danielskwierawski.scheduler;

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
        int[][] result = copyOf(givenPlan);
        int amountOfWorkers = result.length;
        for (int worker = (amountOfWorkers - 1); worker >= 0; worker--) {
            int amountOfDays = result[worker].length;
            for (int day = (amountOfDays - 1); day >= 0; day--) {
                System.out.println(result[worker][day]);
                result[worker][day]++;
                System.out.println(result[worker][day]);
                break;
            }
            break;
        }
        return result;
    }

    private static int[][] copyOf(int[][] givenPlan) {
        int amountOfWorkers = givenPlan.length;
        int[][] result = new int[amountOfWorkers][];
        for (int worker = 0; worker < amountOfWorkers; worker++) {
            int amountOfDays = givenPlan[worker].length;
            result[worker] = new int[amountOfDays];
            for (int day = 0; day < amountOfDays; day++) {
                result[worker][day] = givenPlan[worker][day];
            }
        }
        return result;
    }
}
