package com.danielskwierawski.scheduler;

public class Checker {

    public static boolean checkStartHours(int[][] plan) {
        for (int i = 0; i < plan.length; i++) {
            for (int j = 1; j < plan[i].length; j++) {
                if (plan[i][j] < plan[i][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
