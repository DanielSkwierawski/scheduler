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
}
