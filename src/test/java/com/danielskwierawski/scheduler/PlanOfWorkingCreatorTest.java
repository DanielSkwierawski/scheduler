package com.danielskwierawski.scheduler;

import org.junit.Test;

import java.math.BigInteger;

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
    public void shouldReturnPlanOfIndexesForGivenAmountOfWorkers() throws Exception {
        // given
        final int amountOfWorkers = 3;
        int[] expectedPlanOfIndexes = {0, 0, 0};
        // when
        int[] result = PlanOfWorkingCreator.initializePlanOfIndexes(amountOfWorkers);
        // then
        assertThat(result).isEqualTo(expectedPlanOfIndexes);
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
    public void shouldIncreaseLeastSignificantIndex() throws Exception {
        // given
        final int amountOfCombinations = 3;
        int[] givenPlanOfIndexes =    {0, 0, 0};
        int[] expectedPlanOfIndexes = {0, 0, 1};
        // when
        final boolean result = PlanOfWorkingCreator.increaseByOnePlanOfIndexes(givenPlanOfIndexes, amountOfCombinations);
        // then
        assertThat(givenPlanOfIndexes).isEqualTo(expectedPlanOfIndexes);
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

    @Test
    public void shouldResetLeastSignificantIndexAndIncreaseByOneNextIndexIfLeastSignificantIndexHasMax() throws Exception {
        // given
        final int amountOfCombinations = 3;
        int[] givenPlanOfIndexes =    {0, 0, 2};
        int[] expectedPlanOfIndexes = {0, 1, 0};
        // when
        final boolean result = PlanOfWorkingCreator.increaseByOnePlanOfIndexes(givenPlanOfIndexes, amountOfCombinations);
        // then
        assertThat(givenPlanOfIndexes).isEqualTo(expectedPlanOfIndexes);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseAndResetAllWorkersWhenEveryWorkerHasLastSet() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        final boolean[][] givenPlan = {
                {false, true, true},
                {false, true, true},
                {false, true, true},
                {false, true, true},
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
        final boolean result = PlanOfWorkingCreator.increaseByOne(givenPlan, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(givenPlan).isEqualTo(expectedPlan);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseAndResetAllIndexesWhenEveryIndexHasMax() throws Exception {
        // given
        final int amountOfCombinations = 3;
        int[] givenPlanOfIndexes =    {2, 2, 2};
        int[] expectedPlanOfIndexes = {0, 0, 0};
        // when
        final boolean result = PlanOfWorkingCreator.increaseByOnePlanOfIndexes(givenPlanOfIndexes, amountOfCombinations);
        // then
        assertThat(givenPlanOfIndexes).isEqualTo(expectedPlanOfIndexes);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnCorrectAmountOfCombinations10From31() throws Exception {
        // given
        final int amountOfDays = 31;
        final int amountOfNonWorkingDays = 10;
        final int expectedAmountOfCombinations = 44352165;
        // when
        final int result = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays, amountOfNonWorkingDays);
        // then
        assertThat(result).isEqualTo(expectedAmountOfCombinations);
    }

    @Test
    public void shouldReturnCorrectValueOfFactorialOf12() throws Exception {
        // given
        final int factorialInput1 = 12;
        final BigInteger expectedFactorialOutput1 = new BigInteger("479001600");
        // when
        final BigInteger result1 = PlanOfWorkingCreator.factorial(factorialInput1);
        // then
        assertThat(result1).isEqualTo(expectedFactorialOutput1);
    }

    @Test
    public void shouldProducePlanWhereEveryRowContainsFirstCombinationWhenPlanOfIndexesContainsOnlyZeros() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        int[] planOfIndexes = {0, 0, 0};
        final boolean[][] expectedPlan = {
                {true, true, false},
                {true, true, false},
                {true, true, false}
        };
        // when
        final boolean[][] plan = PlanOfWorkingCreator.initializePlanForWorkersFromPlanOfIndexes(planOfIndexes, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(plan).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldProducePlanWhereEveryRowContainsLastCombinationWhenPlanOfIndexesContainsOnlyMaxValues() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        int[] planOfIndexes = {2, 2, 2};
        final boolean[][] expectedPlan = {
                {false, true, true},
                {false, true, true},
                {false, true, true}
        };
        // when
        final boolean[][] plan = PlanOfWorkingCreator.initializePlanForWorkersFromPlanOfIndexes(planOfIndexes, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(plan).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldProducePlanWithAppropriateRows() throws Exception {
        // given
        final boolean[][] everyPossibleCombinationOfWorkingAndNonWorkingDays = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        int[] planOfIndexes = {2, 1, 0};
        final boolean[][] expectedPlan = {
                {false, true, true},
                {true, false, true},
                {true, true, false}
        };
        // when
        final boolean[][] plan = PlanOfWorkingCreator.initializePlanForWorkersFromPlanOfIndexes(planOfIndexes, everyPossibleCombinationOfWorkingAndNonWorkingDays);
        // then
        assertThat(plan).isEqualTo(expectedPlan);
    }

    @Test
    public void shouldGenerateArrayWithEveryPossiblePlansOfWorkingAndNonWorkingDays() throws Exception {
        // given
        final int amountOfDays = 2;
        final int amountOfNonWorkingDays = 1;
        final int amountOfWorkers = 2;
        final int[][] workersCoverage = {
//              {0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23},
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//1
                {0,  0,  0,  0,  0,  0,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0},//2
        };
        boolean[][][] expected = {
                {{true, false}, {false, true}},
                {{false, true}, {true, false}}
        };
        // when
        boolean[][][] result = PlanOfWorkingCreator.createEveryPossiblePlansOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays, amountOfWorkers, workersCoverage);
        // then
        assertThat(result).isEqualTo(expected);
    }
}