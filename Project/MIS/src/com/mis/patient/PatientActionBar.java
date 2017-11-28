package com.mis.patient;


import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import com.mis.authentication.R;

public class PatientActionBar extends SherlockFragmentActivity {
	com.actionbarsherlock.app.ActionBar actionBar;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		actionBar=getSupportActionBar();
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater menuInflater=getSupportMenuInflater();
		menuInflater.inflate(R.menu.patient_menu, menu);				
		return true;
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.patient_menu_option1)
		{
			Intent i=new Intent(PatientActionBar.this,PatientManagementMenu.class);
			startActivity(i);
			finish();
		}
		if(item.getItemId()==R.id.patient_menu_option2)
		{
			Intent i=new Intent(PatientActionBar.this,PatientManagementMenu.class);
			startActivity(i);
			finish();
		}
		if(item.getItemId()==R.id.patient_menu_option3)
		{
			Intent i=new Intent(PatientActionBar.this,PatientManagementMenu.class);
			startActivity(i);
			finish();
		}
		return true;
	}

}
