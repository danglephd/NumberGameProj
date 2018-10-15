package com.pxr.tutorial.menu.Surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pxr.tutorial.menu.Object.Ball;
import com.pxr.tutorial.menu.Object.Boy;
import com.pxr.tutorial.menu.Object.Explosion;
import com.pxr.tutorial.menu.R;
import com.pxr.tutorial.menu.Thread.GameThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import com.pro.danglph.game2dbegin.R;

/**
 * Created by danglph on 10/07/2017.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, IGameSurface {

    private GameThread gameThread;
//    List<Boy> lstChibi = null;
    private Ball ball = null;
//    private Boy chibi1 = null;
//    private Explosion expl = null;
//    Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
//    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.explo);
    Bitmap ballBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ballred);


    public GameSurface(Context context) {
        super(context);

        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);

        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);

        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if(ball.isTouched(x, y)){
                ball.setMoved(true);
            }

//            Iterator<Boy> iterator = this.lstChibi.iterator();
//            while (iterator.hasNext()) {
//                Boy chibi = iterator.next();
//                if (chibi.isTouched(x, y)) {
//                    iterator.remove();
//                    expl = new Explosion(bitmap, chibi.getX(), chibi.getY(), this);
//                } else {
//                    int movingVectorX = x - chibi.getX();
//                    int movingVectorY = y - chibi.getY();
//
//                    chibi.setMovingVector(movingVectorX, movingVectorY);
//                }
//            }
//
//            if (chibi1 != null) {
//                if (chibi1.isTouched(x, y)) {
//                    expl = new Explosion(bitmap, chibi1.getX(), chibi1.getY(), this);
//
//                    chibi1 = null;
//                } else {
//                    int movingVectorX = x - this.chibi1.getX();
//                    int movingVectorY = y - this.chibi1.getY();
//
//                    this.chibi1.setMovingVector(movingVectorX, movingVectorY);
//                }
//            }
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        if (chibi1 != null) {
//            this.chibi1.draw(canvas);
//        }
//        for (Boy chibi : lstChibi) {
//            chibi.draw(canvas);
//        }
//
//        if (expl != null) {
//            this.expl.draw(canvas);
//        }

        if (ball != null) {
            this.ball.draw(canvas);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
//        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.chibi1);
//        lstChibi = new ArrayList<>();
//        this.chibi1 = new Boy(chibiBitmap1, this.getWidth() / 2, this.getHeight() / 2, this);
//        lstChibi.add(chibi2);
//        Boy chibi2 = new Boy(chibiBitmap1, this.getWidth() / 2, this.getHeight() / 2, this);
        float scaleValue = 0.2f;
        ballBitmap =  Bitmap.createScaledBitmap(ballBitmap, (int) (ballBitmap.getWidth() * scaleValue), (int) (ballBitmap.getHeight() * scaleValue), true);
        this.ball = new Ball(ballBitmap, 0, 0, this);
        this.gameThread = new GameThread(this, surfaceHolder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                this.gameThread.setRunning(false);

                // Luồng cha, cần phải tạm dừng chờ GameThread kết thúc.
                this.gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = true;
        }
    }

    @Override
    public void update() {
        if (this.ball != null) {
            this.ball.update();
        }
//        if (this.chibi1 != null) {
//            this.chibi1.update();
//        }
//
//        for (Boy chibi : lstChibi) {
//            chibi.update();
//        }
//
//        Iterator<Boy> iterator = this.lstChibi.iterator();
//        while (iterator.hasNext()) {
//            Boy chibi = iterator.next();
//            chibi.update();
//        }
//
//
//        if (this.expl != null) {
//            this.expl.update();
//            if (this.expl.isFinish()) {
//                if (this.chibi1 == null) {
//                    this.chibi1 = new Boy(chibiBitmap1, this.getWidth() / 2, this.getHeight() / 2, this);
//                }
//
//                if (lstChibi.size() < 100) {
//                    Boy chibi2 = new Boy(chibiBitmap1, this.getWidth() / 2, this.getHeight() / 2, this);
//                    lstChibi.add(chibi2);
////                this.expl = null;
//                }
//            }
//        }
    }
}
