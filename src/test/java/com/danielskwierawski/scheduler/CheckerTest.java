package com.danielskwierawski.scheduler;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class CheckerTest {

    @Test
    public void shouldReturnTrueWhenEveryDayStartsAtTheSameTimeThanDayBeforeOrLater() throws Exception {
        // given
        final int[][] plan = {
                {6,  6,  8,  8,  14},
                {14, 14, 14, 14, 14},
                {0,  0,  6,  6,  8}};
        // when
        boolean result = Checker.checkStartHours(plan);
        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfAnyDayStartsEarlierThanDayBefore() throws Exception {
        // given
        final int[][] plan = {{14,  6}};
        // when
        boolean result = Checker.checkStartHours(plan);
        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldTreatZeroAsDayOff() throws Exception {
        // given
        final int[][] plan = {{14,  0}};
        boolean result = Checker.checkStartHours(plan);
        // then
        assertThat(result).isTrue();
    }
}