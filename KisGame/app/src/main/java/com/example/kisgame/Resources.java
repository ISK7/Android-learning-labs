package com.example.kisgame;

public class Resources {

    static private int startDps = 10;
    static private int dps;
    static private int g = 3;
    static private int score;
    static private int speed = 7;
    static private int jumpSpeed = -30;
    static private int meta = startDps;
    static private int defaultMeta = startDps;

    public static void setMeta(int meta) {
        Resources.meta = meta;
    }
    public static int getMeta() {
        return meta;
    }

    static public void setScore(int newS) {score = newS;}
    static public int getScore() {return score;}

    public static void setSpeed(int speed) {
        Resources.speed = speed;
    }
    public static int getSpeed() {
        return speed;
    }

    static public void setDps(int nDps) {
        dps = nDps;
    }
    static public int getDps() {
        return dps;
    }

    static public int getG() {
        return g;
    }

    static public int getStartDps() {return startDps;}
    static public void setStartDps(int nStartDps) {startDps = nStartDps;}
    static public void setG(int nG) {
        g = nG;
    }

    static public void setJumpSpeed(int newJumpSpeed) {jumpSpeed = newJumpSpeed; }

    public static int getJumpSpeed() {
        return jumpSpeed;
    }

    public static int getDefaultMeta() {
        return defaultMeta;
    }
}
