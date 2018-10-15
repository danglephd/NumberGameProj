package com.pxr.tutorial.menu.Object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.pxr.tutorial.menu.Surface.IGameSurface;

/**
 * Created by danglph on 10/07/2017.
 */

public class Explosion extends GameMainObject {

    private int rowIndex = 0;
    private int colIndex = -1;

    private boolean finish = false;

    public Explosion(Bitmap image, int x, int y, IGameSurface gameSurface) {
        super(image, 5, 5, x, y);
        this.igameSurface = gameSurface;
    }

    public void update() {
        this.colIndex++;

        if (this.colIndex >= this.colCount) {
            this.colIndex = 0;
            this.rowIndex++;

            if (this.rowIndex >= this.rowCount) {
                this.finish = true;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (!finish) {
            Bitmap bitmap = this.createSubImageAt(rowIndex, colIndex);
            canvas.drawBitmap(bitmap, this.x, this.y, null);
        }
    }

    public boolean isFinish() {
        return finish;
    }
}
