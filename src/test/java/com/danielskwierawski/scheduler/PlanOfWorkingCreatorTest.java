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
    public void shouldReturnCorrectValueOfFactorial() throws Exception {
        // given
        final int factorialInput1 = 1;
        final int expectedFactorialOutput1 = 1;
        final int factorialInput2 = 2;
        final int expectedFactorialOutput2 = 2;
        final int factorialInput3 = 3;
        final int expectedFactorialOutput3 = 6;
        final int factorialInput4 = 4;
        final int expectedFactorialOutput4 = 24;
        final int factorialInput5 = 5;
        final int expectedFactorialOutput5 = 120;
        final int factorialInput6 = 6;
        final int expectedFactorialOutput6 = 720;
        final int factorialInput7 = 7;
        final int expectedFactorialOutput7 = 5040;
        final int factorialInput8 = 8;
        final int expectedFactorialOutput8 = 40320;
        final int factorialInput9 = 9;
        final int expectedFactorialOutput9 = 362880;
        final int factorialInput10 = 10;
        final int expectedFactorialOutput10 = 3628800;
        // when
        final int result1 = PlanOfWorkingCreator.factorial(factorialInput1);
        final int result2 = PlanOfWorkingCreator.factorial(factorialInput2);
        final int result3 = PlanOfWorkingCreator.factorial(factorialInput3);
        final int result4 = PlanOfWorkingCreator.factorial(factorialInput4);
        final int result5 = PlanOfWorkingCreator.factorial(factorialInput5);
        final int result6 = PlanOfWorkingCreator.factorial(factorialInput6);
        final int result7 = PlanOfWorkingCreator.factorial(factorialInput7);
        final int result8 = PlanOfWorkingCreator.factorial(factorialInput8);
        final int result9 = PlanOfWorkingCreator.factorial(factorialInput9);
        final int result10 = PlanOfWorkingCreator.factorial(factorialInput10);
        // then
        assertThat(result1).isEqualTo(expectedFactorialOutput1);
        assertThat(result2).isEqualTo(expectedFactorialOutput2);
        assertThat(result3).isEqualTo(expectedFactorialOutput3);
        assertThat(result4).isEqualTo(expectedFactorialOutput4);
        assertThat(result5).isEqualTo(expectedFactorialOutput5);
        assertThat(result6).isEqualTo(expectedFactorialOutput6);
        assertThat(result7).isEqualTo(expectedFactorialOutput7);
        assertThat(result8).isEqualTo(expectedFactorialOutput8);
        assertThat(result9).isEqualTo(expectedFactorialOutput9);
        assertThat(result10).isEqualTo(expectedFactorialOutput10);
    }

    @Test
    public void shouldReturnCorrectAmountOfCombinations() throws Exception {
        // given
        final int amountOfDays11 = 1;
        final int amountOfNonWorkingDays11 = 1;
        final int expectedAmountOfCombinations11 = 1;
        final int amountOfDays12 = 2;
        final int amountOfNonWorkingDays12 = 1;
        final int expectedAmountOfCombinations12 = 2;
        final int amountOfDays22 = 2;
        final int amountOfNonWorkingDays22 = 2;
        final int expectedAmountOfCombinations22 = 1;
        final int amountOfDays13 = 3;
        final int amountOfNonWorkingDays13 = 1;
        final int expectedAmountOfCombinations13 = 3;
        final int amountOfDays23 = 3;
        final int amountOfNonWorkingDays23 = 2;
        final int expectedAmountOfCombinations23 = 3;
        final int amountOfDays33 = 3;
        final int amountOfNonWorkingDays33 = 3;
        final int expectedAmountOfCombinations33 = 1;
        final int amountOfDays14 = 4;
        final int amountOfNonWorkingDays14 = 1;
        final int expectedAmountOfCombinations14 = 4;
        final int amountOfDays24 = 4;
        final int amountOfNonWorkingDays24 = 2;
        final int expectedAmountOfCombinations24 = 6;
        final int amountOfDays34 = 4;
        final int amountOfNonWorkingDays34 = 3;
        final int expectedAmountOfCombinations34 = 4;
        final int amountOfDays44 = 4;
        final int amountOfNonWorkingDays44 = 4;
        final int expectedAmountOfCombinations44 = 1;
        final int amountOfDays15 = 5;
        final int amountOfNonWorkingDays15 = 1;
        final int expectedAmountOfCombinations15 = 5;
        final int amountOfDays25 = 5;
        final int amountOfNonWorkingDays25 = 2;
        final int expectedAmountOfCombinations25 = 10;
        final int amountOfDays35 = 5;
        final int amountOfNonWorkingDays35 = 3;
        final int expectedAmountOfCombinations35 = 10;
        final int amountOfDays45 = 5;
        final int amountOfNonWorkingDays45 = 4;
        final int expectedAmountOfCombinations45 = 5;
        final int amountOfDays55 = 5;
        final int amountOfNonWorkingDays55 = 5;
        final int expectedAmountOfCombinations55 = 1;
        // when
        final int result11 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays11, amountOfNonWorkingDays11);
        final int result12 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays12, amountOfNonWorkingDays12);
        final int result22 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays22, amountOfNonWorkingDays22);
        final int result13 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays13, amountOfNonWorkingDays13);
        final int result23 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays23, amountOfNonWorkingDays23);
        final int result33 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays33, amountOfNonWorkingDays33);
        final int result14 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays14, amountOfNonWorkingDays14);
        final int result24 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays24, amountOfNonWorkingDays24);
        final int result34 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays34, amountOfNonWorkingDays34);
        final int result44 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays44, amountOfNonWorkingDays44);
        final int result15 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays15, amountOfNonWorkingDays15);
        final int result25 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays25, amountOfNonWorkingDays25);
        final int result35 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays35, amountOfNonWorkingDays35);
        final int result45 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays45, amountOfNonWorkingDays45);
        final int result55 = PlanOfWorkingCreator.getAmountOfCombinations(amountOfDays55, amountOfNonWorkingDays55);
        // then
        assertThat(result11).isEqualTo(expectedAmountOfCombinations11);
        assertThat(result12).isEqualTo(expectedAmountOfCombinations12);
        assertThat(result22).isEqualTo(expectedAmountOfCombinations22);
        assertThat(result13).isEqualTo(expectedAmountOfCombinations13);
        assertThat(result23).isEqualTo(expectedAmountOfCombinations23);
        assertThat(result33).isEqualTo(expectedAmountOfCombinations33);
        assertThat(result14).isEqualTo(expectedAmountOfCombinations14);
        assertThat(result24).isEqualTo(expectedAmountOfCombinations24);
        assertThat(result34).isEqualTo(expectedAmountOfCombinations34);
        assertThat(result44).isEqualTo(expectedAmountOfCombinations44);
        assertThat(result15).isEqualTo(expectedAmountOfCombinations15);
        assertThat(result25).isEqualTo(expectedAmountOfCombinations25);
        assertThat(result35).isEqualTo(expectedAmountOfCombinations35);
        assertThat(result45).isEqualTo(expectedAmountOfCombinations45);
        assertThat(result55).isEqualTo(expectedAmountOfCombinations55);
    }
}