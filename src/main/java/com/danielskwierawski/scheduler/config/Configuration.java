package com.danielskwierawski.scheduler.config;

public class Configuration {

    private final int amountOfDays;
    private final int amountOfNonWorkingDays;
    private final int amountOfWorkers;
    private final int[][] workersCoverage;

    public Configuration(int amountOfDays, int amountOfNonWorkingDays, int amountOfWorkers, int[][] workersCoverage) {
        this.amountOfDays = amountOfDays;
        this.amountOfNonWorkingDays = amountOfNonWorkingDays;
        this.amountOfWorkers = amountOfWorkers;
        this.workersCoverage = workersCoverage;
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public int getAmountOfNonWorkingDays() {
        return amountOfNonWorkingDays;
    }

    public int getAmountOfWorkers() {
        return amountOfWorkers;
    }

    public int[][] getWorkersCoverage() {
        return workersCoverage;
    }
}
