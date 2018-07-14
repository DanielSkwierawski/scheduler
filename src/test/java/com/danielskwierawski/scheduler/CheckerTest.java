package com.danielskwierawski.scheduler;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class CheckerTest {

    @Test
    public void shouldReturnTrueWhenEveryDayStartsAtTheSameTimeThanBeforeOrLater() throws Exception {
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
}