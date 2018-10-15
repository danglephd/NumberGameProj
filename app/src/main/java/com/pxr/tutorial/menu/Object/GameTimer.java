package com.pxr.tutorial.menu.Object;

import android.widget.ProgressBar;

import com.pxr.tutorial.menu.Utility.CommonFeatures;

/**
 * Created by danglph on 28/07/2017.
 */

public class GameTimer {

    private ProgressBar pgbar = null;
    private long lastupdateNanoTime = -1;
    private static final float VELOCITY = 0.1f;

    public void initTime(ProgressBar pgbar) {
        this.pgbar = pgbar;
//        long now = System.nanoTime();
////        lastupdateNanoTime = now; //26300461937940
        if (this.pgbar != null) {
            this.pgbar.setMax(CommonFeatures.MAX_DISTANCE);//GameTimer in milliseconds
            this.pgbar.setProgress(this.pgbar.getMax() / 2);
        }
    }

    public void update(int addingTime) {
        long now = System.nanoTime();
        if (lastupdateNanoTime == -1) {
            lastupdateNanoTime = now;
        }
        // Đổi nano giây ra mili giây (1 nanosecond = 1 / 1000000 millisecond).
        int distance = (int) (((now - lastupdateNanoTime) / 1000000 - addingTime * 1000) * VELOCITY);
        if (distance > 50) {
            lastupdateNanoTime = now;
        }
        if (this.pgbar != null) {
            this.pgbar.setProgress(this.pgbar.getProgress() - distance);
        }
    }

}
