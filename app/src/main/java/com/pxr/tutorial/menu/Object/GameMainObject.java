package com.pxr.tutorial.menu.Object;

import android.graphics.Bitmap;

import com.pxr.tutorial.menu.Surface.IGameSurface;

/**
 * Created by danglph on 10/07/2017.
 */

public class GameMainObject {
    protected Bitmap image;

    protected final int rowCount;
    protected final int colCount;

    protected final int WIDTH;
    protected final int HEIGHT;

    protected final int width;
    protected final int height;
    protected int x;
    protected int y;
    protected IGameSurface igameSurface;


    public GameMainObject(Bitmap image, int rowCount, int colCount, int x, int y) {
        this.image = image;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.x = x;
        this.y = y;

        if(image != null) {
            this.WIDTH = image.getWidth();
            this.HEIGHT = image.getHeight();

            this.width = this.WIDTH / colCount;
            this.height = this.HEIGHT / rowCount;
        }else{
            this.WIDTH = 0;
            this.HEIGHT = 0;
            this.width = this.WIDTH / colCount;
            this.height = this.HEIGHT / rowCount;
        }
    }

    protected Bitmap createSubImageAt(int row, int col) {
        // createBitmap(bitmap, x, y, width, height).
        Bitmap subImage = Bitmap.createBitmap(image, col * width, row * height, width, height);
        return subImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
