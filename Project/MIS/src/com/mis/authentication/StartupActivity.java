package com.mis.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class StartupActivity extends Activity {
	ProgressBar startupPrgBar;
	int MPROGRESSSTATUS=0;
	int MAX=100,INCREMENTVAL=20,SLEEPTHREAD=1000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startupscreen);
		startupPrgBar=(ProgressBar) findViewById(R.id.startupprgbar);
		
		new Thread(new Runnable() {
			
			public void run() 
			{
				while(MPROGRESSSTATUS<MAX)
				{
					MPROGRESSSTATUS=MPROGRESSSTATUS+INCREMENTVAL;
					startupPrgBar.setProgress(MPROGRESSSTATUS);	
							try {
									Thread.sleep(SLEEPTHREAD);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
				}
				
					Intent startAuthenticationActivity=new Intent(StartupActivity.this,LoginActivity.class);
					startActivity(startAuthenticationActivity);
					finish();
			}
		}).start();
		
		
		
		
	}

}