package com.danielskwierawski.scheduler.config;

import java.util.Arrays;

public class Configuration {

    private int amountOfDays;
    private int amountOfNonWorkingDays;
    private int amountOfWorkers;
    private int[][] workersCoverage;

    public Configuration() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration that = (Configuration) o;

        if (amountOfDays != that.amountOfDays) return false;
        if (amountOfNonWorkingDays != that.amountOfNonWorkingDays) return false;
        if (amountOfWorkers != that.amountOfWorkers) return false;
        return Arrays.deepEquals(workersCoverage, that.workersCoverage);
    }

    @Override
    public int hashCode() {
        int result = amountOfDays;
        result = 31 * result + amountOfNonWorkingDays;
        result = 31 * result + amountOfWorkers;
        result = 31 * result + Arrays.deepHashCode(workersCoverage);
        return result;
    }
}
