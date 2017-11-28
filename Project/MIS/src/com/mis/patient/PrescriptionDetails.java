package com.mis.patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mis.authentication.LoginActivity;
import com.mis.authentication.R;
import com.mis.background.BackgroundProcess;

public class PrescriptionDetails extends SherlockFragment {
	View v;
	Spinner doctorNames;
	Map<Integer,List<JSONObject>>sortedData;	
	ListView patient_medicine_list;
	List<JSONObject>prescriptionList;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		List<String>doctorList=new ArrayList<String>();
		final List<Integer>doctorIdList=new ArrayList<Integer>();
		doctorNames=(Spinner) v.findViewById(R.id.doctorNames);
		patient_medicine_list=(ListView) v.findViewById(R.id.patient_medicine_list);
		
		
		List<NameValuePair>userInput=new ArrayList<NameValuePair>();
		List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();
		
		serverAddress.add(new BasicNameValuePair("serverAddress", "mis_getdoctorlist.php"));
		userInput.add(new BasicNameValuePair("userId",String.valueOf(LoginActivity.USER_ID)));
		try {
			JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress,userInput).get();
			if(jsonObjectResult!=null && jsonObjectResult.getInt("success")==1){
				JSONArray jsonArray=jsonObjectResult.getJSONArray("doctorName");				
				doctorList.add("Select Doctor");				
				for(int i=0;i<jsonArray.length();i++){
					String doctorName=jsonArray.getJSONObject(i).getString("URI_NAME");
					Integer doctorId=Integer.parseInt(jsonArray.getJSONObject(i).getString("URI_ID"));
					doctorList.add(doctorName);
					doctorIdList.add(doctorId);					
				}				
				ArrayAdapter<String>doctorNameAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,doctorList);
				doctorNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);								
				doctorNames.setAdapter(doctorNameAdapter);				
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
		
		doctorNames.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
				
				if(position!=0){
					
					prescriptionList=new ArrayList<JSONObject>();
					sortedData=new HashMap<Integer, List<JSONObject>>();
					List<NameValuePair>serverAddress1=new ArrayList<NameValuePair>();
					List<NameValuePair>userSelection=new ArrayList<NameValuePair>();
										
					serverAddress1.add(new BasicNameValuePair("serverAddress", "mis_getdoctorspecificmedicineinfo.php"));
					userSelection.add(new BasicNameValuePair("userId",String.valueOf(LoginActivity.USER_ID)));
					userSelection.add(new BasicNameValuePair("doctorId",String.valueOf(doctorIdList.get(position-1))));					
					
					try {
						JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress1,userSelection).get();
						Log.d("test responese", jsonObjectResult.toString());
						prescriptionList=sortDataAccordingToDisease(jsonObjectResult);
						if(prescriptionList!=null && prescriptionList.size()!=0){
							PatientAdapter patientAdapter=new PatientAdapter(getActivity(), prescriptionList);
							patient_medicine_list.setAdapter(patientAdapter);
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
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//on click of list
		
		patient_medicine_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getActivity(), "item selected is"+prescriptionList.get(position), Toast.LENGTH_LONG).show();
				//Log.d("on click",sortedData.get(prescriptionList.get(position).get));
				
				try {
					int id=Integer.parseInt(prescriptionList.get(position).getString("MI_DI_ID"));					
					List<JSONObject>detailList=sortedData.get(id);
					
					
					
				//	LayoutInflater layoutInflater =getActivity().getLayoutInflater();			
				 //   View popupView = layoutInflater.inflate(R.layout.medicinelist, null);  
				//    final PopupWindow popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				//    popupWindow.setFocusable(true);			    
				//    popupWindow.showAsDropDown(patient_medicine_list, 50, -30);
					
					} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		LayoutInflater layoutInflater=getLayoutInflater(savedInstanceState);
		v=	layoutInflater.inflate(R.layout.prescriptionpatient, container, false);	
		return v;
	
	}
	
	
	
	
	
	//private method area
	
	private List<JSONObject> sortDataAccordingToDisease(JSONObject jsonObjectResult) throws JSONException {
		if(jsonObjectResult!=null && jsonObjectResult.getInt("success")==1){
			JSONArray jsArray=jsonObjectResult.getJSONArray("medicineInfo");
			for(int i=0;i<jsArray.length();i++){
				JSONObject jObject=jsArray.getJSONObject(i);
				int key=Integer.parseInt(jObject.getString("MI_DI_ID"));
				
				if(sortedData.containsKey(key)){
					List<JSONObject>temp=sortedData.get(key);
					temp.add(jObject);
					sortedData.put(key, temp);										
				}else{
					List<JSONObject>value=new ArrayList<JSONObject>();
					value.add(jObject);
					prescriptionList.add(jObject);
					sortedData.put(key, value);
				}
	
			}
			
		}
		
		return prescriptionList;
		
	}
	


}
