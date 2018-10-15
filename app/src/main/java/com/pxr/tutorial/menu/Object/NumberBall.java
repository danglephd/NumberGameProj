package com.pxr.tutorial.menu.Object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.Log;

import com.pxr.tutorial.menu.Surface.IGameSurface;
import com.pxr.tutorial.menu.dto.Fibonacci;

/**
 * Created by danglph on 12/07/2017.
 */

public class NumberBall extends GameMainObject {
    private static final String TAG = NumberBall.class.getSimpleName();

    private int rowIndex = 0;
    private int colIndex = -1;

    private int rX;
    private int rY;
    private int r;
    private int rZoom;
    private int value;
    private boolean isChose = false;
    private int ballColor = Color.GREEN;
    private Paint fillPaint = null;
    private Paint strokePaint = null;
    private TextPaint textPaint = null;

    public NumberBall(Bitmap image, int x, int y, IGameSurface gameSurface) {
        super(image, 6, 3, x, y);
        this.igameSurface = gameSurface;
        fillPaint = new Paint();
        fillPaint.setColor(ballColor);
//        fillPaint.setAlpha(100);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);
        strokePaint = new Paint();
        strokePaint.setColor(Color.WHITE);
        strokePaint.setAlpha(255);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(4.0f);
        strokePaint.setAntiAlias(true);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
    }

    private int alpha = 255;
    private Fibonacci fibonacci = new Fibonacci();
    public void update() {
//        this.colIndex++;
//
//        if (this.colIndex >= this.colCount) {
//            this.colIndex = 0;
//            this.rowIndex++;
//
//            if (this.rowIndex >= this.rowCount) {
//                rowIndex = 0;
//                colIndex = 0;
//            }
//        }
        if(isChose){
//            int i = fibonacci.next();
            if(alpha - fibonacci.getSecond() > 234) {
//                fillPaint.setColor(this.ballColor);
//                fillPaint.setShader(new RadialGradient(rX, rY,
//                        r, Color.WHITE, this.ballColor, Shader.TileMode.CLAMP));
                int i = fibonacci.next();
//                Log.i(TAG, i + "-first");
//                alpha -= fibonacci.next();
                rZoom = r+i;
                fillPaint.setShader(new RadialGradient(rX, rY,
                        r+i, Color.WHITE, this.ballColor, Shader.TileMode.CLAMP));
                fillPaint.setAlpha(alpha - i);

//                Log.i(TAG, fillPaint.getAlpha() + "-updated");
//            }else{
            }else{
                rZoom = r;
                fillPaint.setShader(new RadialGradient(rX, rY,
                        r, Color.WHITE, this.ballColor, Shader.TileMode.CLAMP));

            }
//                fillPaint.setAlpha(50);
//                fillPaint.setColor(Color.BLUE);
//                isChose = false;
        }else{
            rZoom = r;
            fillPaint.setShader(new RadialGradient(rX, rY,
                    r, Color.WHITE, this.ballColor, Shader.TileMode.CLAMP));
            alpha = 255;
//            isChose = true;
//            Log.i(TAG, fillPaint.getAlpha() + "-UpdateAlpha");
        }

    }

    public void draw(Canvas canvas) {
        //Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
//        canvas.drawBitmap(bitmap, this.x, this.y, null);

//        Rectangle rect = new Rectangle(x, y, cellWidth, cellHeight);
//        if(rX < 0 || rY < 0)
//        {
//
//        }else
//        {
//        }
//        int xPos = canvas.getWidth()/2;
//        int yPos = (int)((canvas.getHeight()/2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;

//        if(this.isChose)
//        {
//            fillPaint.setShader(null);
//            fillPaint.setColor(Color.BLACK);
//            fillPaint.setShader(new RadialGradient(rX, rY,
//                    r, Color.WHITE, Color.BLUE, Shader.TileMode.CLAMP));
//            canvas.drawCircle(rX, rY, r, fillPaint);
//            canvas.drawCircle(rX, rY, r, strokePaint);
//            fillPaint.setColor(Color.BLACK);
//            canvas.drawText(this.value + "", rX, rY + r / 3, textPaint);
//        }
//        fillPaint.setColor(this.ballColor);
        canvas.drawCircle(rX, rY, rZoom, fillPaint);
        canvas.drawCircle(rX, rY, rZoom, strokePaint);
        fillPaint.setColor(Color.BLACK);
        canvas.drawText(this.value + "", rX, rY + r / 3, textPaint);
//        fillPaint.setColor(this.ballColor);

//        e.Graphics.DrawEllipse(pen, rect);
//        brush = new SolidBrush(Color.Black);
        //e.Graphics.DrawRectangle(Pens.Black, Rectangle.Round(rect));
//        using (StringFormat format = new StringFormat())
//        {
//            canvas.drawText(this.value + "",
//                    new System.Drawing.Font("Microsoft Sans Serif", 8, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0))),
//                    brush, rect, format);
//            //graphicsObj.DrawText("Number", SystemFonts.Default, Brushes.Black, bounds, format);
//        }
    }

    public int getrX() {
        return rX;
    }

    public void setrX(int rX) {
        this.rX = rX;
    }

    public int getrY() {
        return rY;
    }

    public void setrY(int rY) {
        this.rY = rY;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getBallColor() {
        return ballColor;
    }

    public void setBallColor(int ballColor) {
        this.ballColor = ballColor;
        this.fillPaint.setColor(ballColor);
    }

    public boolean isTouched(int x, int y) {
        return (this.rX - this.r <= x && x <= this.rX + this.r
                && this.rY - this.r <= y && y <= this.rY + this.r);
    }

    public void choose(boolean isChose) {
        this.isChose = isChose;
    }
}
