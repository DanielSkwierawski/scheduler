package com.danielskwierawski.scheduler;

import org.junit.Test;

import static com.danielskwierawski.scheduler.Checker.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CreatorTest {

    @Test
    public void shouldReturnPreFilledPlan() throws Exception {
        // given
        final int amountOfWorkers = 3;
        final int amountOfDays = 5;
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[][] expectedPlan = {
                {initValue,initValue,initValue,initValue,initValue},
                {initValue,initValue,initValue,initValue,initValue},
                {initValue,initValue,initValue,initValue,initValue}};
        // when
        final int[][] result = Creator.initializePlan(amountOfWorkers, amountOfDays);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldReturnPreFilled1DimensionalPlan() throws Exception {
        // given
        final int length = 5;
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[] expectedPlan = {initValue,initValue,initValue,initValue,initValue};
        // when
        final int[] result = Creator.initializePlan(length);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldReturnPreFilled1DimensionalPlanOfWorkingDays() throws Exception {
        // given
        final int length = 5;
        final boolean[] expectedPlan = {WORKING_DAY, WORKING_DAY, WORKING_DAY, WORKING_DAY, WORKING_DAY};
        // when
        final boolean[] result = Creator.initializePlanOfWorkingDays(length);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldIncreaseByOneLeastSignificantDay() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[][] givenPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue}};
        final int[][] expectedPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, (initValue + STEP)}};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldIncreaseByOneLeastSignificantDayIn1DimensionalPlan() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[] givenPlan =     {initValue, initValue, initValue, initValue, initValue};
        final int[] expectedPlan =  {initValue, initValue, initValue, initValue, (initValue + STEP)};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldChangeLeastSignificantDayIn1DimensionalPlanOfWorkingDays() throws Exception {
        // given
        final boolean[] givenPlan =     {WORKING_DAY, WORKING_DAY, WORKING_DAY, WORKING_DAY, WORKING_DAY};
        final boolean[] expectedPlan =  {WORKING_DAY, WORKING_DAY, WORKING_DAY, WORKING_DAY, NON_WORKING_DAY};
        // when
        final boolean result = Creator.increaseByOnePlanOfWorkingDays(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetDayOffToLeastSignificantDayWhenIsLatestHour() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int latestHour = LATEST_HOUR_OF_START_WORKING;
        final int[][] givenPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, latestHour}};
        final int[][] expectedPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, DAY_OFF}};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetDayOffToLeastSignificantDayWhenIsLatestHourIn1DimensionalPlan() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int latestHour = LATEST_HOUR_OF_START_WORKING;
        final int[] givenPlan = {initValue, initValue, initValue, initValue, latestHour};
        final int[] expectedPlan = {initValue, initValue, initValue, initValue, DAY_OFF};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetEarliestHourToLeastSignificantDayAndIncreaseByOneNextDayWhenLastSignificantDayIsDayOff() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[][] givenPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, DAY_OFF}};
        final int[][] expectedPlan = {
                {initValue, initValue, initValue, initValue,    initValue},
                {initValue, initValue, initValue, initValue,        initValue},
                {initValue, initValue, initValue, (initValue + STEP),  initValue}};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetEarliestHourToLeastSignificantDayAndIncreaseByOneNextDayWhenLastSignificantDayIsDayOffIn1DimensionalPlan() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[] givenPlan = {initValue, initValue, initValue, initValue, DAY_OFF};
        final int[] expectedPlan = {initValue, initValue, initValue, (initValue + STEP),  initValue};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetWorkingDayToLeastSignificantDayAndChangeNextDayWhenLastSignificantDayIsNonWorkingDayIn1DimensionalPlanOfWorkingDays() throws Exception {
        // given
        final boolean[] givenPlan =     {WORKING_DAY, WORKING_DAY, WORKING_DAY, WORKING_DAY,        NON_WORKING_DAY};
        final boolean[] expectedPlan =  {WORKING_DAY, WORKING_DAY, WORKING_DAY, NON_WORKING_DAY,    WORKING_DAY};
        // when
        final boolean result = Creator.increaseByOnePlanOfWorkingDays(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseAndSetEarliestHourToEveryDayWhenEveryDayIsDayOff() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[][] givenPlan = {
                {DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF},
                {DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF},
                {DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF}};
        final int[][] expectedPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue}};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseAndSetEarliestHourToEveryDayWhenEveryDayIsDayOffIn1DimensionalPlan() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int[] givenPlan = {DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF, DAY_OFF};
        final int[] expectedPlan = {initValue, initValue, initValue, initValue, initValue};
        // when
        final boolean result = Creator.increaseByOne(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseAndWorkingDayToEveryDayWhenEveryDayIsNonWorkingDayIn1DimensionalPlanOfWorkingDays() throws Exception {
        // given
        final boolean[] givenPlan =     {NON_WORKING_DAY,   NON_WORKING_DAY,    NON_WORKING_DAY,    NON_WORKING_DAY,    NON_WORKING_DAY};
        final boolean[] expectedPlan =  {WORKING_DAY,       WORKING_DAY,        WORKING_DAY,        WORKING_DAY,        WORKING_DAY};
        // when
        final boolean result = Creator.increaseByOnePlanOfWorkingDays(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldTransform2DimensionalArrayOfWorkersCoverageIn1DimensionalByTakingTheMaxValueFromEachRow() throws Exception {
        // given
        int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0}
        };
        int[] expectedResult = {3, 2, 1};
        // when
        int[] result = Creator.getTheBiggestValueForEachDay(workersCoverage);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldTransformPlanOfWorkingAndNonWorkingDaysToPlanWithHours() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        boolean[][] planOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        int[][] expectedPlan = {
                {initValue,initValue,DAY_OFF},
                {initValue,DAY_OFF,initValue},
                {DAY_OFF,initValue,initValue}
        };
        // when
        int[][] result = Creator.createStandardPlanFromPlanOfWorkingAndNonWorkingDays(planOfWorkingAndNonWorkingDays);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldIncreaseLeastSignificantWorkingDay() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        int[][] givenPlan = {
                {initValue, initValue,          DAY_OFF}
        };
        int[][] expectedPlan = {
                {initValue, (initValue + STEP), DAY_OFF}
        };
        // when
        final boolean result = Creator.increaseOnlyWorkingDays(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldSetEarliestHourToLeastSignificantWorkingDayAndIncreaseByOneNextWorkingDayWhenLastSignificantWorkingDayIsLatestHour() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int latestHour = LATEST_HOUR_OF_START_WORKING;
        int[][] givenPlan = {
                {initValue,          latestHour, DAY_OFF}
        };
        int[][] expectedPlan = {
                {(initValue + STEP), initValue,  DAY_OFF}
        };
        // when
        final boolean result = Creator.increaseOnlyWorkingDays(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseAndSetEarliestHourToEveryWorkingDayWhenEveryWorkingDayIsLatestHour() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int latestHour = LATEST_HOUR_OF_START_WORKING;
        int[][] givenPlan = {
                {latestHour,    latestHour, DAY_OFF}
        };
        int[][] expectedPlan = {
                {initValue,     initValue,  DAY_OFF}
        };
        // when
        final boolean result = Creator.increaseOnlyWorkingDays(givenPlan);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isFalse();
    }
}