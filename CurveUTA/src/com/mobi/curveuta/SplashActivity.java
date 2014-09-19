package com.mobi.curveuta;
import com.parse.Parse;
import com.parse.ParseAnalytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, YOUR_PARSE_APP_ID, YOUR_PARSE_CLIENT_KEY);
        
        setContentView(R.layout.activity_splash);
        
        Thread t = new Thread() {
			public void run() {
				try {
					Intent i = new Intent(SplashActivity.this, DepartmentActivity.class);
					sleep(3000);
			        startActivity(i);
			        finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}
		};	
		t.start();
        
        
        
    }
}
