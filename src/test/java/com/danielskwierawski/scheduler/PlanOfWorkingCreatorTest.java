package com.danielskwierawski.scheduler;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanOfWorkingCreatorTest {

    @Test
    public void shouldGenerateArrayWithEveryPossibleCombinationsOfWorkingAndNonWorkingDays() throws Exception {
        // given
        final int amountOfDays = 3;
        final int amountOfNonWorkingDays = 1;
        boolean[][] expected = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        // when
        final boolean[][] result = PlanOfWorkingCreator.createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldReturnPlanOfWorkingAndNonWorkingDaysForGivenAmountOfWorkers() throws Exception {
        // given
        final int amountOfWorkers = 5;
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        final boolean[][] expectedPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false}
        };
        // when
        final boolean[][] result = PlanOfWorkingCreator.initializePlanForWorkersFromGivenArray(amountOfWorkers, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }

}