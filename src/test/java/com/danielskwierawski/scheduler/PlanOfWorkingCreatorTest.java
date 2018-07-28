package com.danielskwierawski.scheduler;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class PlanOfWorkingCreatorTest {

    @Test
    public void shouldGenerateArrayWithEveryPossibleCombinationsOfWorkingAndNonWorkingDays() throws Exception {
        // given
        final int amountOfDays = 3;
        final int amountOfNonWorkingDays = 1;
        boolean[][] expectedPlan = {
                {true, true, false},
                {true, false, true},
                {false, true, true}
        };
        // when
        boolean[][] result = PlanOfWorkingCreator.createEveryPossibleCombinationsOfWorkingAndNonWorkingDays(amountOfDays, amountOfNonWorkingDays);
        // then
        assertThat(result).isEqualTo(expectedPlan);
    }
}