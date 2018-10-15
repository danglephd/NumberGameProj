package com.pxr.tutorial.menu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SettingsActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	
	private TextView tv, tv1, tv2, tv3, tv4;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);        
             
        
        tv = (TextView) findViewById(R.id.sound);
        tv1 = (TextView) findViewById(R.id.music);
        tv2 = (TextView) findViewById(R.id.vibrate);
        tv3 = (TextView) findViewById(R.id.graphics);
        tv4 = (TextView) findViewById(R.id.back);
        
        tv.setTypeface(MenuActivity.tf);
        tv1.setTypeface(MenuActivity.tf);
        tv2.setTypeface(MenuActivity.tf);
        tv3.setTypeface(MenuActivity.tf);
        tv4.setTypeface(MenuActivity.tf);
        
        tv.setOnTouchListener(new CustomTouchListener());
        tv1.setOnTouchListener(new CustomTouchListener());
        tv2.setOnTouchListener(new CustomTouchListener());
        tv3.setOnTouchListener(new CustomTouchListener());
        tv4.setOnTouchListener(new CustomTouchListener());
        
        tv.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        
        getSettings();
        
        setText();        
    }
        
    
	private void setText() {
		tv.setText(	settingsHolder[0] ? "Sound off" 		: "Sound on");
        tv1.setText(settingsHolder[1] ? "Music off" 		: "Music on");
        tv2.setText(settingsHolder[2] ? "Vibrate off" 	: "Vibrate on");
        tv3.setText(settingsHolder[3] ? "Graphics low" 	: "Graphics high");
	}

	private boolean[] settingsHolder = new boolean[4];
	
	private void getSettings() {
		SharedPreferences settings = getSharedPreferences(MenuActivity.PREFS_NAME, 0);
		
		settingsHolder[0] = settings.getBoolean("sound", true);
		settingsHolder[1] = settings.getBoolean("music", true);
		settingsHolder[2] = settings.getBoolean("vibrate", true);
		settingsHolder[3] = settings.getBoolean("graphics", true);			
	}

	
	public void onClick(View v) {
		SharedPreferences settings = getSharedPreferences(MenuActivity.PREFS_NAME, 0);
	    SharedPreferences.Editor editor = settings.edit();
	      
		switch(v.getId()){
			case R.id.sound:
				editor.putBoolean("sound", !settingsHolder[0]);
				break;
			case R.id.music:
				editor.putBoolean("music", !settingsHolder[1]);
				break;
			case R.id.vibrate:
				editor.putBoolean("vibrate", !settingsHolder[2]);
				break;
			case R.id.graphics:
				editor.putBoolean("graphics", !settingsHolder[3]);
				break;
			case R.id.back:
				finish();
				break;
		}
		
		editor.commit();
		
		getSettings();
		setText();
	}
}