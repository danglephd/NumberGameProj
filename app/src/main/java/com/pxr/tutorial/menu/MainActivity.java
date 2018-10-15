package com.pxr.tutorial.menu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.pxr.tutorial.menu.Surface.GameSurface2;
import com.pxr.tutorial.menu.Surface.IGameSurface;

public class MainActivity extends Activity {
    IGameSurface game = null;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (game != null && (game instanceof GameSurface2)) {
            ((GameSurface2) game).saveGameData();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (game != null && (game instanceof GameSurface2)) {
            ((GameSurface2) game).onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (game != null && (game instanceof GameSurface2)) {
            ((GameSurface2) game).saveGameData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Loại bỏ tiêu đề.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.game_layout);

        game = findViewById(R.id.gameSurface4);

    }


}
