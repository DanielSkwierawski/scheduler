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

    @Test
    public void shouldAssignSecondSetToLeastSignificantWorkerIfHeHasFirstSet() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        final boolean[][] givenPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false}
        };
        final boolean[][] expectedPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, false, true}
        };
        // when
        final boolean result = PlanOfWorkingCreator.increaseByOne(givenPlan, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldAssignThirdSetToLeastSignificantWorkerIfHeHasSecondSet() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        final boolean[][] givenPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, false, true}
        };
        final boolean[][] expectedPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {false, true, true}
        };
        // when
        final boolean result = PlanOfWorkingCreator.increaseByOne(givenPlan, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldResetLeastSignificantWorkerAndIncreaseByOneNextWorkerIfLeastSignificantWorkerHasLastSetFromGivenArray() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        final boolean[][] givenPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {false, true, true}
        };
        final boolean[][] expectedPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false},
                {true, false, true},
                {true, true, false}
        };
        // when
        final boolean result = PlanOfWorkingCreator.increaseByOne(givenPlan, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isTrue();
    }

}