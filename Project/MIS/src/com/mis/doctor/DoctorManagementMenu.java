package com.mis.doctor;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.mis.authentication.R;
import com.mis.background.TabDecider;

public class DoctorManagementMenu extends DoctorActionBar {
	ActionBar actionBar;
	ActionBar.Tab newPrescriptionTab;
	ActionBar.Tab existingPrescriptionTab;
	ActionBar.Tab attachementTab;
	

	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
	    setContentView(R.layout.doctormenu);
	    
		actionBar=getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);				
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
	
			//Tabs Created
			
			newPrescriptionTab=actionBar.newTab().setText("Prescription");
			newPrescriptionTab.setContentDescription("New Prescription Info");
			
			existingPrescriptionTab=actionBar.newTab().setText("Modify");
			existingPrescriptionTab.setContentDescription("Existing Prescription Details");
			
			attachementTab=actionBar.newTab().setText("Attachemnt");
			attachementTab.setContentDescription("View Attachment");
			 			
			//Tab Listener set i.e given class gets execute when user click on that tab
			 	TabDecider tabDecider=new TabDecider();	 
			 	newPrescriptionTab.setTabListener(tabDecider);
			 	existingPrescriptionTab.setTabListener(tabDecider);
			 	attachementTab.setTabListener(tabDecider);
			
			//Tabs are added to action bar
				actionBar.addTab(newPrescriptionTab);
				actionBar.addTab(existingPrescriptionTab);
				actionBar.addTab(attachementTab);
			
			
	}

}
