package com.danielskwierawski.scheduler.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JsonImporter {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Configuration configuration = objectMapper.readValue(new File("configuration.json"), Configuration.class);

        System.out.println(configuration.getAmountOfDays());
        System.out.println(configuration.getAmountOfNonWorkingDays());
        System.out.println(configuration.getAmountOfWorkers());
        System.out.println(Arrays.deepToString(configuration.getWorkersCoverage()));
    }

}
