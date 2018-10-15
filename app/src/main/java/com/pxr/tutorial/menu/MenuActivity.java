package com.pxr.tutorial.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
	public static final String PREFS_NAME = "sampleGameSettings";		
	public static Typeface tf;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        tf = Typeface.createFromAsset(getAssets(),"data/fonts/floral.ttf");
        
        TextView tv = (TextView) findViewById(R.id.start);
        TextView tv1 = (TextView) findViewById(R.id.quick);
        TextView tv2 = (TextView) findViewById(R.id.settings);
        TextView tv3 = (TextView) findViewById(R.id.about);
        TextView tv4 = (TextView) findViewById(R.id.exit);
        
        tv.setTypeface(tf);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        tv3.setTypeface(tf);
        tv4.setTypeface(tf);
        
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
        
        Toast.makeText(this, "Press Menu to Start Game", Toast.LENGTH_LONG).show();
		   
    }
      
	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.start:
				Intent i = new Intent(this, MainActivity.class);
				startActivity(i);
				break;
			case R.id.quick:
				Intent testActivity = new Intent(this, TestActivity.class);
				startActivity(testActivity);

				break;
			case R.id.settings:
				i = new Intent(this, SettingsActivity.class);
				startActivity(i);
				break;
			case R.id.about:
				makeDialog();
				break;	
			case R.id.exit:
				finish();
				break;
		}
		
	}
	
	private void makeDialog() {		
		
	    AlertDialog.Builder dialog = new AlertDialog.Builder(this);	    
	    
	    dialog.setMessage("This is a demo");

	    dialog.setPositiveButton("Buy the full version", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {
	        	Toast.makeText(getBaseContext(), "BUY IT", Toast.LENGTH_LONG).show();
	        }
	    });
	
	    dialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface arg0, int arg1) {}
	    });
	
	    dialog.show();
	}
		
}