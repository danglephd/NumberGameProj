package com.pxr.tutorial.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TestActivity extends Activity {
//    IGameSurface game = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Loại bỏ tiêu đề.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//        setContentView(R.layout.game_layout);
        setContentView(R.layout.objectdownexmpl_layout);
        ImageView imageView = (ImageView) findViewById(R.id.your_image);
        LinearLayout layout = (LinearLayout) findViewById(R.id.info);

        float bottomOfScreen = getResources().getDisplayMetrics().heightPixels - (imageView.getHeight() * 6);
//        float bottomOfScreen =  getResources().getDisplayMetrics().heightPixels - (imageView.getHeight() * 4);
        imageView.animate().setStartDelay(1000);
        imageView.animate().translationY(bottomOfScreen * 0.6f)
                .setInterpolator(new AccelerateInterpolator())
//                .setInterpolator(new BounceInterpolator())
//                .setInterpolator(new AnticipateInterpolator())
//                .setInterpolator(new AnticipateOvershootInterpolator())
//                .setInterpolator(new CycleInterpolator(5.0f))
                .setInterpolator(new OvershootInterpolator())
                .setDuration(2000);

    }


}
