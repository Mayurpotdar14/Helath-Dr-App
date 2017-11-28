package com.mis.doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeterogeneousExpandableList;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mis.authentication.LoginActivity;
import com.mis.authentication.R;
import com.mis.background.BackgroundProcess;

public class NewPrescriptionInfo extends SherlockFragment {
	View v;
	EditText diseaseName,diseaseDescription,userKey;
	Button nextPage;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		diseaseName=(EditText) v.findViewById(R.id.diseaseName);
		diseaseDescription=(EditText) v.findViewById(R.id.diseaseDescription);
		nextPage=(Button) v.findViewById(R.id.nextPage);
		userKey=(EditText) v.findViewById(R.id.userKey);
		
		
		nextPage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(diseaseName.getText().toString().length()==0){
					Toast.makeText(getActivity(), "Please provide disease name", Toast.LENGTH_SHORT).show();
				}else{
					
					List<NameValuePair>userInput=new ArrayList<NameValuePair>();
					List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();
					serverAddress.add(new BasicNameValuePair("serverAddress", "mis_userkeyvalidate.php"));					
					userInput.add(new BasicNameValuePair("userKey",userKey.getText().toString().trim()));
					
					JSONObject jsonObjectResult;
					try {
						jsonObjectResult = new BackgroundProcess().execute(serverAddress,userInput).get();
						
						Log.d("result", jsonObjectResult.toString());
						
						int result= jsonObjectResult.getInt("success");
						
						if(result==0){
							Toast.makeText(getActivity(), "Incorrect User Key", Toast.LENGTH_LONG).show();
						}else{											
														
							Bundle bundle=new Bundle();
							bundle.putString("diseaseName",diseaseName.getText().toString());
							bundle.putString("diseaseDescription",diseaseDescription.getText().toString());
							bundle.putString("userKey", jsonObjectResult.getString("key_result"));
							
							FragmentTransaction ft = getFragmentManager().beginTransaction();													
							NewPrescription o=new NewPrescription();	
							o.setArguments(bundle);
							ft.replace(android.R.id.content, (Fragment) o,"NEW");							
							ft.commit();
	
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
	
				}
				
			}
		});
		
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		LayoutInflater layoutInflater=getLayoutInflater(savedInstanceState);
		v=	layoutInflater.inflate(com.mis.authentication.R.layout.prescriptiondoctorinfo, container, false);	
		return v;
	
	}

}
