package com.danielskwierawski.scheduler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    private final int amountOfDays = 4;
    private final int amountOfNonWorkingDays = 1;
    private final int amountOfWorkers = 3;
    private final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
            {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//1
            {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//2
            {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//3
            {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//4
    };
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldProduceJsonFromObject() throws Exception {
        // given
        Configuration configuration = new Configuration(amountOfDays, amountOfNonWorkingDays, amountOfWorkers, workersCoverage);
        final String expectedJsonString = "{\"amountOfDays\":4,\"amountOfNonWorkingDays\":1,\"amountOfWorkers\":3,\"workersCoverage\":[[0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0],[0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0],[0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0],[0,0,0,0,0,0,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,0,0]]}";
        // when
        String result = objectMapper.writeValueAsString(configuration);
        // then
        assertThat(result).isEqualTo(expectedJsonString);
    }

    @Test
    public void shouldProduceObjectFromJson() throws Exception {
        // given
        final String jsonString = "{\"amountOfDays\":4,\"amountOfNonWorkingDays\":1,\"amountOfWorkers\":3,\"workersCoverage\":[[0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0],[0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0],[0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0],[0,0,0,0,0,0,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,0,0]]}";
        Configuration expectedConfiguration = new Configuration(amountOfDays, amountOfNonWorkingDays, amountOfWorkers, workersCoverage);
        // when
        Configuration result = objectMapper.readValue(jsonString, Configuration.class);
        // then
        assertThat(result).isEqualTo(expectedConfiguration);
    }
}