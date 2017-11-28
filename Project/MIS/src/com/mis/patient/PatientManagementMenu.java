package com.mis.patient;

import com.actionbarsherlock.app.ActionBar;

import com.mis.authentication.R;
import com.mis.background.TabDecider;

import android.os.Bundle;

public class PatientManagementMenu extends PatientActionBar {
	ActionBar actionBar;
	ActionBar.Tab prescriptionTab;
	ActionBar.Tab attachementTab;
	ActionBar.Tab generateTab;

	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
	    setContentView(R.layout.patientmenu);
		actionBar=getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);				
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			//Tabs Created
			
			    prescriptionTab=actionBar.newTab().setText("Prescription");
			    prescriptionTab.setContentDescription("Prescription Details");
			    
			    attachementTab=actionBar.newTab().setText("Attachemnt");
			    attachementTab.setContentDescription("Attachemnt Details");
			    
			    generateTab=actionBar.newTab().setText("Generate Key");
			    generateTab.setContentDescription("Generate key details");
			 			
			//Tab Listener set i.e given class gets execute when user click on that tab
			 	TabDecider tabDecider=new TabDecider();	 
			 	prescriptionTab.setTabListener(tabDecider);
			 	attachementTab.setTabListener(tabDecider);
			 	generateTab.setTabListener(tabDecider);
			
			//Tabs are added to action bar
				actionBar.addTab(prescriptionTab);
				actionBar.addTab(attachementTab);
				actionBar.addTab(generateTab);
			
			
	}

}
