package com.mis.doctor;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.mis.authentication.R;
import com.mis.patient.PatientActionBar;
import com.mis.patient.PatientManagementMenu;

public class DoctorActionBar extends SherlockFragmentActivity {
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
		menuInflater.inflate(R.menu.doctor_menu, menu);				
		return true;
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.doctor_menu_option1)
		{
			Intent i=new Intent(DoctorActionBar.this,DoctorManagementMenu.class);
			startActivity(i);
			finish();
		}
		if(item.getItemId()==R.id.doctor_menu_option2)
		{
			Intent i=new Intent(DoctorActionBar.this,DoctorManagementMenu.class);
			startActivity(i);
			finish();
		}
		if(item.getItemId()==R.id.doctor_menu_option3)
		{
			Intent i=new Intent(DoctorActionBar.this,DoctorManagementMenu.class);
			startActivity(i);
			finish();
		}
		return true;
	}

}
