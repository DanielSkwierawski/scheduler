package com.danielskwierawski.scheduler;

import org.junit.Test;

import static com.danielskwierawski.scheduler.Checker.*;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void shouldTreatMinusOneAsDayOff() throws Exception {
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

    @Test
    public void shouldReturnTrueIfInGivenDayWorkAtLeastTheSameAmountOfWorkersThatMaxCoverageInGivenDay() throws Exception {
        // given
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0}};
        final boolean[][] plan = {
                {true},
                {true},
                {true}
        };
        // when
        final boolean result = Checker.checkIfWorkingCoverageCouldBeFulfilled(plan, workersCoverage);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueIfInGivenDayWorkAtLeastTheSameAmountOfWorkersThatInGivenDay() throws Exception {
        // given
        final int[] workersCoverage = {3, 2, 1};
        final boolean[][] plan = {
                {true, true, true},
                {true, true, false},
                {true, false, false}
        };
        // when
        final boolean result = Checker.checkIfWorkingCoverageCouldBeFulfilledFromFlatWorkersCoverage(plan, workersCoverage);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfInGivenDayWorkLessWorkersThanMaxFromCoverageInGivenDay() throws Exception {
        // given
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  0,  0}};
        final boolean[][] plan = {
                {true},
                {true},
                {false}
        };
        // when
        final boolean result = Checker.checkIfWorkingCoverageCouldBeFulfilled(plan, workersCoverage);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseIfInGivenDayWorkLessWorkersThanInGivenDay() throws Exception {
        // given
        final int[] workersCoverage = {3};
        boolean[][] plan = {
                {true},
                {true},
                {false}
        };
        // when
        final boolean result = Checker.checkIfWorkingCoverageCouldBeFulfilledFromFlatWorkersCoverage(plan, workersCoverage);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueIfPlanContainsExactlyAsManyNonWorkingDaysAsGiven() throws Exception {
        // given
        final int amountOfNonWorkingDays = 2;
        final boolean[] givenPlan = {WORKING_DAY, WORKING_DAY, NON_WORKING_DAY, NON_WORKING_DAY};
        // when
        boolean result = Checker.checkAmountOfNonWorkingDays(givenPlan, amountOfNonWorkingDays);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfPlanContainsLessNonWorkingDaysThanGiven() throws Exception {
        // given
        final int amountOfNonWorkingDays = 2;
        final boolean[] givenPlan = {WORKING_DAY, WORKING_DAY, WORKING_DAY, NON_WORKING_DAY};
        // when
        boolean result = Checker.checkAmountOfNonWorkingDays(givenPlan, amountOfNonWorkingDays);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseIfPlanContainsMoreNonWorkingDaysThanGiven() throws Exception {
        // given
        final int amountOfNonWorkingDays = 2;
        final boolean[] givenPlan = {WORKING_DAY, NON_WORKING_DAY, NON_WORKING_DAY, NON_WORKING_DAY};
        // when
        boolean result = Checker.checkAmountOfNonWorkingDays(givenPlan, amountOfNonWorkingDays);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueIfWorkerDoesNotExceedSpecifiedAmountOfWorkingDaysInARow() throws Exception {
        // given
        final boolean[] givenPlan = {
                NON_WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                NON_WORKING_DAY
        };
        // when
        boolean result = Checker.checkAmountOfWorkingDaysInARow(givenPlan);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfWorkerExceedSpecifiedAmountOfWorkingDaysInARow() throws Exception {
        // given
        final boolean[] givenPlan = {
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY,
                WORKING_DAY
        };
        // when
        boolean result = Checker.checkAmountOfWorkingDaysInARow(givenPlan);
        // then
        assertThat(result).isFalse();
    }

}