package com.pxr.tutorial.menu.Utility;

import java.util.Random;

/**
 * Created by danglph on 18/07/2017.
 */

public class CommonFeatures {

    public static final int MAX_BALL = 3;
    public static final int MAX_DISTANCE = 10000;//in milliseconds
    public static final int STATE_RUNNING = 0;
    public static final int STATE_STOP = 1;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_LOSE = 3;

    public static String splitCharactor_1 = "~";
    public static String splitCharactor_2 = "&";
    public static String splitCharactor_3 = ";";
    public static String splitCharactor_4 = "_";
    public static String splitCharactor_5 = ",";
    public static String splitCharactor_6 = "";

    public static final String saveConfigFile = "saveConfigFile";


    public static int randomIntValue(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }
}
