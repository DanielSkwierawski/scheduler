package com.danielskwierawski.scheduler;

import com.danielskwierawski.scheduler.config.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SimpleAppBasedOnWorkingDaysPlan {

    public static void main(String[] args) throws IOException {

        long timeStart;
        long timeStop;
        int counter = 1;
        long[] totals = new long[counter];

        ObjectMapper objectMapper = new ObjectMapper();
        Configuration configuration = objectMapper.readValue(new File("configuration.json"), Configuration.class);

        final int amountOfDays = configuration.getAmountOfDays();
        final int amountOfNonWorkingDays = configuration.getAmountOfNonWorkingDays();
        final int amountOfWorkers = configuration.getAmountOfWorkers();
        final int[][] workersCoverage = configuration.getWorkersCoverage();

        System.out.println("amountOfDays=" + amountOfDays + " amountOfNonWorkingDays=" + amountOfNonWorkingDays + " amountOfWorkers=" + amountOfWorkers);
        for (int i = 0; i < counter; i++) {
            timeStart = System.nanoTime();

            int[][][] everyPossiblePlans = PlanOfWorkingCreator.createEveryPossiblePlans(amountOfDays, amountOfNonWorkingDays, amountOfWorkers, workersCoverage);

            timeStop = System.nanoTime();

            deepPrint(everyPossiblePlans);

            totals[i] = timeStop - timeStart;
            System.out.println(totals[i]);
        }
    }

    private static void deepPrint(int[][][] everyPossiblePlans) {
        for (int plan = 0; plan < everyPossiblePlans.length; plan++) {
            System.out.println(Arrays.deepToString(everyPossiblePlans[plan]));
        }

    }
}
