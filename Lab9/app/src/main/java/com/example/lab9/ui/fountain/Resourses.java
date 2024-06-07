package com.example.lab9.ui.fountain;

public class Resourses {
    static private int fallSpeed = 5, defaultSpeed = 100;

    public static int getFallSpeed() {
        return fallSpeed;
    }

    public static int getDefaultSpeed() {
        return defaultSpeed;
    }

    public static void setDefaultSpeed(int defaultSpeed) {
        Resourses.defaultSpeed = defaultSpeed;
    }

    public static void setFallSpeed(int fallSpeed) {
        Resourses.fallSpeed = fallSpeed;
    }
}
