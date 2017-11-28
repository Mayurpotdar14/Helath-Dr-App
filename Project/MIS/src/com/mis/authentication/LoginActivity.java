package com.mis.authentication;

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

import com.mis.background.BackgroundProcess;

import com.mis.doctor.DoctorManagementMenu;
import com.mis.patient.PatientManagementMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public static final String URL="http://192.168.43.25/nakul/";
	public static int USER_ID=0;
	EditText userName,password;
	Spinner roll;
	Button signIn,signUp,exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {			
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		userName=(EditText) findViewById(R.id.userName);
		password=(EditText) findViewById(R.id.password);
		roll=(Spinner) findViewById(R.id.roll);
		signIn=(Button) findViewById(R.id.signIn);
		signUp=(Button) findViewById(R.id.signUp);
		exit=(Button) findViewById(R.id.exit);
		
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
		signUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent nextActivity=new Intent(LoginActivity.this, SignupActivity1.class);
				startActivity(nextActivity);
				//Log.d("TEST", String.valueOf(86867680));
				
			}
		});
		
		signIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<NameValuePair>userInput=new ArrayList<NameValuePair>();
				List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();
				
				serverAddress.add(new BasicNameValuePair("serverAddress", "mis_loginvalidate.php"));
				
				userInput.add(new BasicNameValuePair("username",userName.getText().toString().trim()));
				userInput.add(new BasicNameValuePair("password",password.getText().toString().trim()));
				userInput.add(new BasicNameValuePair("roll",roll.getSelectedItem().toString().trim()));	
				
				try {
					JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress,userInput).get();
					
					Log.d("login result", jsonObjectResult.toString());
					
					int result= jsonObjectResult.getInt("success");
					
					if(result==0){
						Toast.makeText(LoginActivity.this, "Incorrect user name or password", Toast.LENGTH_LONG).show();
					}else{
						
						JSONArray jsonArray=jsonObjectResult.getJSONArray("login_result");
						JSONObject jsObject= jsonArray.getJSONObject(0);
						LoginActivity.USER_ID=Integer.parseInt(jsObject.getString("L_ID"));
						
						Intent nextActivity=null;
						
						if(jsObject.getString("L_ROLL").equalsIgnoreCase("Patient")){
							 nextActivity=new Intent(LoginActivity.this, PatientManagementMenu.class);
							 startActivity(nextActivity);
						}else if(jsObject.getString("L_ROLL").equalsIgnoreCase("Doctor")){
							nextActivity=new Intent(LoginActivity.this, DoctorManagementMenu.class);
							startActivity(nextActivity);
						}
						finish();
						
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
		});
	
		
		
		
	}

}
