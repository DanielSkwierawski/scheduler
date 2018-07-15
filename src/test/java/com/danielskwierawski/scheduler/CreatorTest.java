package com.danielskwierawski.scheduler;

import org.junit.Test;

import static com.danielskwierawski.scheduler.Checker.DAY_OFF;
import static com.danielskwierawski.scheduler.Checker.EARLIEST_HOUR_OF_START_WORKING;
import static com.danielskwierawski.scheduler.Checker.LATEST_HOUR_OF_START_WORKING;
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
        final int[][] result = Creator.initializePlanWithGivenValue(amountOfWorkers, amountOfDays, initValue);
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
                {initValue, initValue, initValue, initValue, (initValue + 1)}};
        // when
        final int[][] result = Creator.copyAndIncreaseByOne(givenPlan);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldResetLeastSignificantDayAndIncreaseByOneNextDayWhenLeastSignificantDayIsLatestHour() throws Exception {
        // given
        final int initValue = EARLIEST_HOUR_OF_START_WORKING;
        final int latestHour = LATEST_HOUR_OF_START_WORKING;
        final int[][] givenPlan = {
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, initValue},
                {initValue, initValue, initValue, initValue, latestHour}};
        final int[][] expectedPlan = {
                {initValue, initValue, initValue, initValue,        initValue},
                {initValue, initValue, initValue, initValue,        initValue},
                {initValue, initValue, initValue, (initValue + 1),  DAY_OFF}};
        // when
        final int[][] result = Creator.copyAndIncreaseByOne(givenPlan);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }
}