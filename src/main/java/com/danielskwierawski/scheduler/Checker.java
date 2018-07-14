package com.danielskwierawski.scheduler;

public class Checker {

    public static boolean checkStartHours(int[][] plan) {
        for (int i = 0; i < plan.length; i++) {
            for (int j = 1; j < plan[i].length; j++) {
                if (dayIsNotDayOff(plan[i][j])) {
                    if (dayStartsBeforeThanDayBefore(plan, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean dayIsNotDayOff(int i) {
        return i != 0;
    }

    private static boolean dayStartsBeforeThanDayBefore(int[][] plan, int i, int j) {
        return plan[i][j] < plan[i][j - 1];
    }

}
