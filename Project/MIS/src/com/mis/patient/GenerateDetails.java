package com.mis.patient;

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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mis.authentication.LoginActivity;
import com.mis.authentication.R;
import com.mis.background.BackgroundProcess;


public class GenerateDetails extends SherlockFragment {	
	View v;
	LinearLayout activeKeyLayout,newKeyLayout;
	TextView activeUserKey,newKey;
	Button disableKey,generateKey;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);		
		activeKeyLayout=(LinearLayout) v.findViewById(R.id.activeKeyLayout);
		newKeyLayout=(LinearLayout) v.findViewById(R.id.newKeyLayout);
		activeUserKey=(TextView) v.findViewById(R.id.activeUserKey);
		newKey=(TextView) v.findViewById(R.id.newKey);
		disableKey=(Button) v.findViewById(R.id.disableKey);
		generateKey=(Button) v.findViewById(R.id.generateKey);
		
		List<NameValuePair>userInput=new ArrayList<NameValuePair>();
		List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();
		
		serverAddress.add(new BasicNameValuePair("serverAddress", "mis_checkuserkey.php"));
		userInput.add(new BasicNameValuePair("userId",String.valueOf(LoginActivity.USER_ID)));
		userInput.add(new BasicNameValuePair("userStatus",String.valueOf(0)));
		
		try {
			JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress,userInput).get();
			int result= jsonObjectResult.getInt("success");
			if(result==1){
				JSONArray jsonArray=jsonObjectResult.getJSONArray("userkey_result");
				JSONObject jsObject= jsonArray.getJSONObject(0);
				activeKeyLayout.setVisibility(View.VISIBLE);
				activeUserKey.setText(jsObject.getString("UK_KEY"));
				
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
		
		generateKey.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String value=null;
				// TODO Auto-generated method stub
				if(activeUserKey.isShown()){
					Toast.makeText(getActivity(), "Please disable previous key", Toast.LENGTH_LONG).show();
				}else{
					value=generateUserKey();									
					List<NameValuePair>userInput=new ArrayList<NameValuePair>();
					List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();				
					serverAddress.add(new BasicNameValuePair("serverAddress", "mis_generateuserkey.php"));	
					
					userInput.add(new BasicNameValuePair("userId",String.valueOf(LoginActivity.USER_ID)));
					userInput.add(new BasicNameValuePair("status",String.valueOf(0)));
					userInput.add(new BasicNameValuePair("key",value));				
					try {
						JSONObject jsonObjectResult=new BackgroundProcess().execute(serverAddress,userInput).get();
						int result= jsonObjectResult.getInt("success");
						if(result==1){
							newKey.setText(value);
							
							activeKeyLayout.setVisibility(View.INVISIBLE);		
							generateKey.setVisibility(View.INVISIBLE);
						}
					}catch (InterruptedException e) {					
						e.printStackTrace();
					} catch (ExecutionException e) {					
						e.printStackTrace();
					} catch (JSONException e) {					
						e.printStackTrace();
					}
				}
			}
		});
		
		
		disableKey.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				List<NameValuePair>userInput=new ArrayList<NameValuePair>();
				List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();				
				serverAddress.add(new BasicNameValuePair("serverAddress", "mis_disableuserkey.php"));					
				userInput.add(new BasicNameValuePair("userId",String.valueOf(LoginActivity.USER_ID)));
				userInput.add(new BasicNameValuePair("status",String.valueOf(1)));
				JSONObject jsonObjectResult;
				try {
					jsonObjectResult = new BackgroundProcess().execute(serverAddress,userInput).get();
					int result= jsonObjectResult.getInt("success");
					if(result==1){
						activeKeyLayout.setVisibility(View.INVISIBLE);
					}
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} catch (ExecutionException e) {
					
					e.printStackTrace();
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
				
				
				
			}
		});
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {		
		LayoutInflater layoutInflater=getLayoutInflater(savedInstanceState);
		v=	layoutInflater.inflate(com.mis.authentication.R.layout.generatepatient, container, false);	
		return v;	
	}
	
private String generateUserKey(){
	String genUserKey=null;
	long userKey = 0;
	int min=1,max=100000;	
	userKey=min + (int)(Math.random() * ((max - min) + 1));	
	userKey=Math.round(userKey);	
	genUserKey=String.valueOf(userKey).concat(String.valueOf(LoginActivity.USER_ID));	
	return genUserKey;	
}

}
