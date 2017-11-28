package com.mis.authentication;

import java.util.ArrayList;
import java.util.List;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity1 extends Activity {
	EditText signupUserName,signupPassword;
	Spinner signupRoll;
	Button signupNext,signupExit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity1);
		
		signupUserName=(EditText) findViewById(R.id.signupUserName);
		signupPassword=(EditText) findViewById(R.id.signupPassword);
		signupRoll=(Spinner) findViewById(R.id.signupRoll);
		signupNext=(Button) findViewById(R.id.signupNext);
		signupExit=(Button) findViewById(R.id.signupExit);
		
		signupExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		
		signupNext.setOnClickListener(new OnClickListener() {			

			
			@Override
			public void onClick(View v) {
				List<NameValuePair>userInput=new ArrayList<NameValuePair>();
				List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();
				
				serverAddress.add(new BasicNameValuePair("serverAddress", "mis_login.php"));
				
				userInput.add(new BasicNameValuePair("username",signupUserName.getText().toString().trim()));
				userInput.add(new BasicNameValuePair("password",signupPassword.getText().toString().trim()));
				userInput.add(new BasicNameValuePair("roll",signupRoll.getSelectedItem().toString().trim()));	
				
				try {
					JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress,userInput).get();

					int result= jsonObjectResult.getInt("success");
					
					if(result==1){
						Toast.makeText(SignupActivity1.this, "User name is already exists Please choose another user name", Toast.LENGTH_LONG).show();
					}else{
						 Intent nextActivity=null;
						 nextActivity=new Intent(SignupActivity1.this, SignupActivity2.class);
						 Bundle newUserInfo=new Bundle();
						 newUserInfo.putString("username",signupUserName.getText().toString().trim());
						 newUserInfo.putString("password",signupPassword.getText().toString().trim());
						 newUserInfo.putString("roll",signupRoll.getSelectedItem().toString().trim());
						 nextActivity.putExtras(newUserInfo);
						 startActivity(nextActivity);
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
