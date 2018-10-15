package com.pxr.tutorial.menu.Thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.pxr.tutorial.menu.Surface.IGameSurface;
import com.pxr.tutorial.menu.Utility.CommonFeatures;

/**
 * Created by danglph on 10/07/2017.
 */

public class GameThread extends Thread {

    private boolean running;
    private int mMode = CommonFeatures.STATE_RUNNING;
    private IGameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameThread(IGameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {

        long startTime = System.nanoTime();

        while (running) {
            Canvas canvas = null;
            try {
                // Lấy ra đối tượng Canvas và khóa nó lại.
                canvas = this.surfaceHolder.lockCanvas();

                // Đồng bộ hóa.
                synchronized (canvas) {
                    if (mMode == CommonFeatures.STATE_RUNNING) {
                        this.gameSurface.update();
                    }
                    this.gameSurface.draw(canvas);
                }
            } catch (Exception e) {
                // Không làm gì
            } finally {
                if (canvas != null) {
                    // Mở khóa cho Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime();
            // Thời gian cập nhập lại giao diện Game
            // (Đổi từ Nanosecond ra milisecond).
            long waitTime = (now - startTime) / 1000000;
            if (waitTime < 10) {
                waitTime = 10; // Millisecond.
            }

            try {
                // Ngừng chương trình một chút.
                this.sleep(waitTime);
            } catch (InterruptedException e) {

            }
            startTime = System.nanoTime();
        }
    }

    public void pause(){
        synchronized (this.surfaceHolder) {
            if (mMode == CommonFeatures.STATE_RUNNING) {
                setState(CommonFeatures.STATE_PAUSE);
            }
        }
    }

    public void unpause() {
        // Move the real time clock up to now
//        synchronized (this.surfaceHolder) {
//            mLastTime = System.currentTimeMillis() + 100;
//        }
        setState(CommonFeatures.STATE_RUNNING);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void setState(int state) {
        this.mMode = state;
    }
}
