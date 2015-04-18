package com.karamagi.holysong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreenActivity extends Activity {
	private static int delayTime = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
	
		Thread timer = new Thread(){
			public void run(){
				try{
					
					sleep(delayTime);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent startHomeActivity = new Intent(SplashScreenActivity.this, com.karamagi.holysong.HomeActivity.class);
					startActivity(startHomeActivity);
				}
			}
		};
		
		timer.start();
	}

}
