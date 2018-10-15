package com.pxr.tutorial.menu.Surface;

import android.graphics.Canvas;

/**
 * Created by danglph on 12/07/2017.
 */

public interface IGameSurface{

    abstract void update();
    abstract void draw(Canvas canvas);

    int getWidth();

    int getHeight();
}
