package com.pxr.tutorial.menu.Object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.pxr.tutorial.menu.Surface.IGameSurface;
import com.pxr.tutorial.menu.Utility.CommonFeatures;
import com.pxr.tutorial.menu.Utility.SELECTION;

/**
 * Created by danglph on 10/07/2017.
 */

public class Ball extends GameMainObject {

    private int rowIndex = 0;
    private int colIndex = 0;
    private float scaleValue = 0f;
    private boolean isVisible = true;

    private int ballColor = 0;

    private Bitmap bitmap = null;
    private float deltaY = -1;

    private boolean isMoved = false;

    private Bitmap bitmapBigOne = null;
    private SELECTION bgStates = SELECTION.TYPE1;
    private Paint p = null;
    private long lastDrawNanoTime = -1;
    private static float VELOCITY = 0.1f;
    private int movingVectorX = 1;
    private int movingVectorY = 1;
    private boolean isTransform = true;

    public Ball(Bitmap image, int x, int y, IGameSurface iGameSurface, int ballColor) {
        super(image, 1, 1, x, y);
        this.igameSurface = iGameSurface;
        this.ballColor = ballColor;
        bitmap = image;
        p = new Paint();
        p.setColor(Color.GREEN);
        p.setAlpha(50);
    }

    public Ball(Bitmap image, Bitmap imageBig, int x, int y, IGameSurface iGameSurface, int ballColor) {
        super(image, 1, 1, x, y);
        this.igameSurface = iGameSurface;
        this.ballColor = ballColor;
        this.bitmap = image;
        this.bitmapBigOne = imageBig;
        p = new Paint();
        p.setColor(Color.GREEN);
        p.setAlpha(50);
    }

    public Ball(Bitmap image, int x, int y, IGameSurface iGameSurface) {
        super(image, 1, 1, x, y);
        this.igameSurface = iGameSurface;
        this.ballColor = CommonFeatures.randomIntValue(0, CommonFeatures.MAX_BALL);
        bitmap = image;
        p = new Paint();
        p.setColor(Color.GREEN);
        p.setAlpha(50);
    }

    public void setBitmapBigOne(Bitmap bitmapBigOne) {
        this.bitmapBigOne = bitmapBigOne;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
        deltaY = 0;
    }

    private void drawBackground(Canvas canvas) {
        switch (this.bgStates) {
            case TYPE1:
                break;
            case NOT_SQUARE_TYPE:
                p.setColor(Color.GRAY);
                canvas.drawRect((float) this.x, (float) this.y, (float) this.x + (float) this.getWidth(), (float) this.y + (float) this.getHeight(), p);
                break;
            case SQUARED_TYPE:
                p.setColor(Color.GREEN);
                canvas.drawRect((float) this.x, (float) this.y, (float) this.x + (float) this.getWidth(), (float) this.y + (float) this.getHeight(), p);
                break;
            default:
                p.setColor(Color.GRAY);
                canvas.drawRect((float) this.x, (float) this.y, (float) this.x + (float) this.getWidth(), (float) this.y + (float) this.getHeight(), p);
                break;
        }
//        p.setColor(Color.GREEN);


//        if (this.bgStates) {
//        }
    }


    private void drawValue(Canvas canvas) {
        switch (ballColor) {
            case 0:
                p.setColor(Color.RED);
                break;
            case 1:
                p.setColor(Color.BLUE);
                break;
            case 2:
                p.setColor(Color.GRAY);
                break;
            case 3:
                p.setColor(Color.GREEN);
                break;
            case 4:
                p.setColor(Color.MAGENTA);
                break;
            case 5:
                p.setColor(Color.YELLOW);
                break;
            default:
                p.setColor(Color.WHITE);
                break;
        }

        float x = (float) this.x + 15;
        float y = (float) this.y + 15;
        canvas.drawRect(x, y, x + (float) this.getWidth() - 30, y + (float) this.getHeight() - 30, p);
    }

    public void draw(Canvas canvas) {
        drawBackground(canvas);
        if (this.isTransform) {
            drawtransform(canvas);
        } else {
            if (this.isVisible) {
                canvas.drawBitmap(bitmap, this.x, this.y, null);
            } else {
                canvas.drawBitmap(bitmap, this.x - 1, this.y - 1, null);
            }
        }
//        isVisible = !isVisible;
        drawBigOne(canvas);
        this.lastDrawNanoTime = System.nanoTime();

//        drawValue(canvas);
    }

    private void drawtransform(Canvas canvas) {
        if (scaleValue >= 0) {
            Bitmap transformImg = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * scaleValue), (int) (bitmap.getHeight() * scaleValue), true);
            int x = this.x + (this.width - transformImg.getWidth()) / 2;
            int y = this.y + (this.height - transformImg.getHeight()) / 2;
            canvas.drawBitmap(transformImg, x, y, null);
        }
    }

    private void drawBigOne(Canvas canvas) {
        if (bitmapBigOne != null) {
            if (this.bgStates == SELECTION.NOT_SQUARE_TYPE
                    || this.bgStates == SELECTION.SQUARED_TYPE) {
                int x = this.x + (this.width - bitmapBigOne.getWidth()) / 2;
                int y = this.y + (this.height - bitmapBigOne.getHeight()) / 2;
                canvas.drawBitmap(bitmapBigOne, x, y, null);
            }
        }
    }

    public void decreaseScaleVal() {
        if (scaleValue > 0) {
            scaleValue -= 0.1;
        }
    }

    public boolean isTouched(int x, int y) {
        return (this.x <= x && x <= this.x + width
                && this.y <= y && y <= this.y + this.height);
    }

    public void update() {
        if (isTransform) {
//            transformtosmall();
            transformtobig();
        }
        if (isMoved) {
//            move();
        }
    }

    private void transformtosmall() {
        if (scaleValue > 0) {
            scaleValue -= 0.1f;
        } else if (scaleValue <= 0) {
            scaleValue = 1.0f;
            isTransform = false;
        }
    }

    private void transformtobig() {
        if (scaleValue < 1.0f) {
            scaleValue += 0.06f;
        } else if (scaleValue >= 1.0f) {
            scaleValue = 0f;
            isTransform = false;
        }
    }

    private void move() {
        // Thời điểm hiện tại theo nano giây.
        long now = System.nanoTime();

        // Chưa vẽ lần nào.
        if (lastDrawNanoTime == -1) {
            lastDrawNanoTime = now;
        }

        // Đổi nano giây ra mili giây (1 nanosecond = 1 / 1000000 millisecond).
        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        // Quãng đường mà banh đi được (pixel).
        float distance = VELOCITY * deltaTime;
//        deltaY += VELOCITY * deltaTime;

//        //áp dụng gia tốc
//        distance = deltaTime * deltaTime * 0.5f * VELOCITY;

        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

        // Tính toán vị trí mới của nhân vật.
        this.x += (int) (distance * movingVectorX / movingVectorLength);
        this.y += (int) (distance * movingVectorY / movingVectorLength);
//        this.y += deltaY * deltaTime;

        // Khi nhân vật của game chạm vào cạnh của màn hình thì đổi hướng.
        if (this.x < 0) {
            this.x = 0;
            this.movingVectorX = -this.movingVectorX;
        } else if (this.x > this.igameSurface.getWidth() - width) {
            this.x = this.igameSurface.getWidth() - width;
            this.movingVectorX = -this.movingVectorX;
        }

        if (this.y < 0) {
            this.y = 0;
            this.movingVectorY = -this.movingVectorY;
        } else if (this.y > this.igameSurface.getHeight() - height) {
            this.y = this.igameSurface.getHeight() - height;
            this.movingVectorY = -this.movingVectorY;
            VELOCITY = 0;
        }
    }

    public void setScaleValue(float scaleValue) {
        this.scaleValue = scaleValue;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setBgStates(SELECTION bgStates) {
        this.bgStates = bgStates;
    }

    public int getBallColor() {
        return ballColor;
    }

    public void setBallColor(int ballColor) {
        this.ballColor = ballColor;
    }

    public boolean isSquared() {
        return bgStates == SELECTION.SQUARED_TYPE;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
