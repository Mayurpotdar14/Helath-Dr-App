package com.mis.doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.mis.authentication.LoginActivity;
import com.mis.authentication.R;
import com.mis.background.BackgroundProcess;

public class NewPrescription extends SherlockFragment {
	View v;
	Button addNewMedicine,addToList,cancel,saveMedicine;
	EditText userKeyDoctor,medicine_quantity,medicine_days;
	Spinner medicine_Type,medicine_name;
	CheckBox morning,afternoon,evening,night;
	ListView medicine_list;
	List<JSONObject>list=new ArrayList<JSONObject>();
	JSONArray jsonArray=new JSONArray();
	LinearLayout pre;
	Bundle bundle;
	int ACTIVE=0;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		addNewMedicine=(Button) v.findViewById(R.id.addNewMedicine);
	
		medicine_list=(ListView) v.findViewById(R.id.medicine_list);
		saveMedicine=(Button) v.findViewById(R.id.saveMedicine);
		pre=(LinearLayout) v.findViewById(R.id.pre);
		bundle=this.getArguments();
		
		addNewMedicine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				LayoutInflater layoutInflater =getActivity().getLayoutInflater();			
			    View popupView = layoutInflater.inflate(R.layout.medicinepopup, null);  
			    final PopupWindow popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			    popupWindow.setFocusable(true);			    
			    popupWindow.showAsDropDown(addNewMedicine, 50, -30);			    
			    addToList=(Button) popupView.findViewById(R.id.addToList);
			    cancel=(Button) popupView.findViewById(R.id.cancel);
			    
			    medicine_Type=(Spinner) popupView.findViewById(R.id.medicine_type);
			    medicine_name=(Spinner)popupView.findViewById(R.id.medicine_name);
			    medicine_quantity=(EditText) popupView.findViewById(R.id.medicine_quantity);
			    medicine_days=(EditText) popupView.findViewById(R.id.medicine_days);
			    morning=(CheckBox) popupView.findViewById(R.id.morning);
			    afternoon=(CheckBox) popupView.findViewById(R.id.afternoon);
			    evening=(CheckBox) popupView.findViewById(R.id.evening);
			    night=(CheckBox) popupView.findViewById(R.id.night);
			    
			    
			    Log.d("bundle result", bundle.getString("diseaseName")+"-"+bundle.getString("diseaseDescription")+"-"+bundle.getString("userKey"));
			    
			    cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						popupWindow.dismiss();						
					}
				});
			    
			    //add to list
			    
			    addToList.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						JSONObject jsObject=new JSONObject();
						
						try {
							jsObject.put("MI_MEDICINE_TYPE", medicine_Type.getSelectedItem().toString());
							jsObject.put("MI_MEDICINE_NAME", medicine_name.getSelectedItem().toString());
							jsObject.put("MI_MEDICINE_QUANTITY",medicine_quantity.getText().toString().trim());
							jsObject.put("MI_MEDICINE_DAYS",medicine_days.getText().toString().trim());
							
							jsObject.put("MI_L_PATIENT_ID", bundle.getString("userKey"));
							jsObject.put("MI_L_DOCTOR_ID", LoginActivity.USER_ID);
							jsObject.put("MI_STATUS", ACTIVE);
																		
							StringBuffer dose=new StringBuffer();
							
							if(morning.isChecked()){
								dose.append("Morning-");
							}
							if(afternoon.isChecked()){
								dose.append("Afternoon-");
							}
							if(evening.isChecked()){
								dose.append("Evening-");
							}if(night.isChecked()){
								dose.append("Night");
							}
							jsObject.put("MI_MEDICINE_DOSE", dose.toString());
							
							list.add(jsObject);
							
							jsonArray.put(jsObject);							
							MedicineAdapter medicineAdapter=new MedicineAdapter(getActivity(), list);
							medicine_list.setAdapter(medicineAdapter);							
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
			    
			    
			}
		});
		
		
		saveMedicine.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				List<NameValuePair>userInput=new ArrayList<NameValuePair>();
				List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();				
				serverAddress.add(new BasicNameValuePair("serverAddress", "mis_insertmedicine.php"));
				
				userInput.add(new BasicNameValuePair("medicinelist",jsonArray.toString()));
			
				userInput.add(new BasicNameValuePair("diseaseName", bundle.getString("diseaseName")));
				userInput.add(new BasicNameValuePair("diseaseDescription", bundle.getString("diseaseDescription")));
				
				//Log.d("TEST",jsonArray.toString());
				
				try {
					JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress,userInput).get();
					//Log.d("insert medicine result", jsonObjectResult.toString());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
				
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {		
		LayoutInflater layoutInflater=getLayoutInflater(savedInstanceState);
		v=	layoutInflater.inflate(com.mis.authentication.R.layout.prescriptiondoctor, container, false);	
		return v;
	
	}

}
