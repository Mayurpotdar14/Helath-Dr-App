package com.mis.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.mis.authentication.R;

public class ExistingPrescription extends SherlockFragment {
	View v;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		LayoutInflater layoutInflater=getLayoutInflater(savedInstanceState);
		v=	layoutInflater.inflate(R.layout.prescriptionmodifydoctor, container, false);	
		return v;
	
	}

}
