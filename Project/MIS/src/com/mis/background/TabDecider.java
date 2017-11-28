package com.mis.background;

import android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.HeterogeneousExpandableList;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mis.doctor.ExistingPrescription;
import com.mis.doctor.NewPrescription;
import com.mis.doctor.NewPrescriptionInfo;
import com.mis.doctor.ViewAttachment;
import com.mis.patient.AttachmentDetails;
import com.mis.patient.GenerateDetails;
import com.mis.patient.PatientManagementMenu;
import com.mis.patient.PrescriptionDetails;





public class TabDecider extends FragmentActivity implements TabListener {
	Object o;
	
	
	

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
	    	
		if(tab.getContentDescription().toString().equalsIgnoreCase("Prescription Details"))			
		{
			
			o=new PrescriptionDetails();
			ft.add(android.R.id.content,(Fragment) o,"");
			ft.attach((Fragment) o);	
		}		
		else if(tab.getContentDescription().toString().equalsIgnoreCase("Attachemnt Details"))		
		{
			
			o=new AttachmentDetails();
			ft.add(android.R.id.content,(Fragment) o,"");
			ft.attach((Fragment) o);
			
		}
		else if(tab.getContentDescription().toString().equalsIgnoreCase("Generate key details"))	
		{
			o=new GenerateDetails();
			ft.add(android.R.id.content, (Fragment) o,"");
			ft.attach((Fragment) o);
			
		}
		else if(tab.getContentDescription().toString().equalsIgnoreCase("New Prescription Info"))	
		{
			o=new NewPrescriptionInfo();
			ft.add(android.R.id.content, (Fragment) o,"new1");
			ft.attach((Fragment) o);
			
		}
		else if(tab.getContentDescription().toString().equalsIgnoreCase("Existing Prescription Details"))	
		{
			//Log.d("Fragment attach", "Existing prescription tab")	;
			o=new ExistingPrescription();
			ft.add(android.R.id.content, (Fragment) o,"");
			ft.attach((Fragment) o);
			
		}
		else if(tab.getContentDescription().toString().equalsIgnoreCase("View Attachment"))	
		{
			
			//Log.d("Fragment attach", "attachement tab")	;
			o=new ViewAttachment();
			ft.add(android.R.id.content, (Fragment) o,"");
			ft.attach((Fragment) o);			
		}
		

	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		Fragment f = null;
		
		if(tab.getContentDescription().toString().equalsIgnoreCase("New Prescription Info")){
			//Log.d("in if", "in if");			
			f=new ViewAttachment();			
			ft.replace(android.R.id.content, f);			
			ft.remove(f);
		}else{
			ft.detach((Fragment)o);
		}

	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		

	}
	

}
