package com.danielskwierawski.scheduler;

import static com.danielskwierawski.scheduler.Checker.DAY_OFF;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class CheckerTest {

    @Test
    public void shouldReturnTrueWhenEveryDayStartsAtTheSameTimeThanDayBeforeOrLater() throws Exception {
        // given
        final int[][] plan = {
                {6,         6,          8,      8,      14},
                {14,        14,         14,     14,     14},
                {DAY_OFF,   DAY_OFF,    6,      6,      8}};
        // when
        final boolean result = Checker.checkStartHours(plan);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfAnyDayStartsEarlierThanDayBefore() throws Exception {
        // given
        final int[][] plan = {{14,  6}};
        // when
        final boolean result = Checker.checkStartHours(plan);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldTreatZeroAsDayOff() throws Exception {
        // given
        final int[][] plan = {{14,  DAY_OFF}};
        //when
        final boolean result = Checker.checkStartHours(plan);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueIfEveryWorkerDoesNotExceedGivenAmountOfWorkingHours() throws Exception {
        // given
        final int standardAmountOfWorkingHours = 40;
        final int[][] plan = {
                {6,         6,          6,      6,          6,          DAY_OFF,    DAY_OFF},
                {DAY_OFF,   DAY_OFF,    14,     14,         14,         14,         14},
                {14,        14,         14,     DAY_OFF,    DAY_OFF,    6,          6}};
        //when
        final boolean result = Checker.checkIfEveryWorkerDoesNotExceedGivenAmountOfWorkingHours(plan, standardAmountOfWorkingHours);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfAnyWorkerExceedGivenAmountOfWorkingHours() throws Exception {
        // given
        final int standardAmountOfWorkingHours = 40;
        final int[][] plan = {
                {6,  6,  6,  6,  6,  6,  6}};
        // when
        final boolean result = Checker.checkIfEveryWorkerDoesNotExceedGivenAmountOfWorkingHours(plan, standardAmountOfWorkingHours);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueIfEveryWorkerDoesNotHaveWorkingHoursUnderTheGivenStandard() throws Exception {
        // given
        final int standardAmountOfWorkingHours = 40;
        final int[][] plan = {
                {6,         6,          6,      6,          6,          DAY_OFF,    DAY_OFF},
                {DAY_OFF,   DAY_OFF,    14,     14,         14,         14,         14},
                {14,        14,         14,     DAY_OFF,    DAY_OFF,    6,          6}};
        // when
        final boolean result = Checker.checkIfEveryWorkerDoesNotHaveWorkingHoursUnderTheGivenStandard(plan, standardAmountOfWorkingHours);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfAnyWorkerHasWorkingHoursUnderTheGivenStandard() throws Exception {
        // given
        final int standardAmountOfWorkingHours = 40;
        final int[][] plan = {
                {6,     6,  DAY_OFF,    DAY_OFF,    DAY_OFF,    DAY_OFF,    DAY_OFF}};
        // when
        final boolean result = Checker.checkIfEveryWorkerDoesNotHaveWorkingHoursUnderTheGivenStandard(plan, standardAmountOfWorkingHours);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueIfWorkingCoverageIsFulfilled() throws Exception {
        // given
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  2,  2,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0}};
        final int[][] plan = {
                {6,  6,  6,  6,  6},
                {8,  8,  8,  8,  8},
                {14, 14, 14, 14, 14}};
        // when
        final boolean result = Checker.checkIfWorkingCoverageIsFulfilled(plan, workersCoverage);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfWorkingCoverageIsNotFulfilled() throws Exception {
        // given
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2,  0,  0}};
        final int[][] plan = {
                {6,  6,  6,  6,  6},
                {14, 14, 14, 14, 14}};
        // when
        final boolean result = Checker.checkIfWorkingCoverageIsFulfilled(plan, workersCoverage);
        // then
        assertThat(result).isFalse();
    }
}