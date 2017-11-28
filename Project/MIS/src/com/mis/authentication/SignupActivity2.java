package com.mis.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.mis.background.BackgroundProcess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity2 extends Activity {
	private TextView textView8;
	private EditText firstName,lastName,address,mobileNumber,regNumber;
	private Spinner age,city;
	private RadioGroup genderGroup;
	private RadioButton selectedRadioButton;
	private Button save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity2);
		
		firstName=(EditText) findViewById(R.id.firstName);
		lastName=(EditText) findViewById(R.id.lastName);
		address=(EditText) findViewById(R.id.address);
		mobileNumber=(EditText) findViewById(R.id.mobileNumber);
		regNumber=(EditText) findViewById(R.id.regNumber);
		age=(Spinner) findViewById(R.id.age);
		city=(Spinner) findViewById(R.id.city);
		genderGroup=(RadioGroup) findViewById(R.id.genderGroup);
		
		textView8=(TextView) findViewById(R.id.textView8);
		save=(Button) findViewById(R.id.save);
		
		//registration number visible to user if his/her roll is doctor
		final Bundle userInfo=getIntent().getExtras();
		if(userInfo.getString("roll")!=null && userInfo.getString("roll").equalsIgnoreCase("doctor")){
			regNumber.setVisibility(View.VISIBLE);
			textView8.setVisibility(View.VISIBLE);			
		}
		
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
			//	Toast.makeText(SignupActivity2.this, "selected radio button"+genderGroup.getCheckedRadioButtonId(), Toast.LENGTH_LONG).show();
				int selectedId=genderGroup.getCheckedRadioButtonId();
				selectedRadioButton=(RadioButton)findViewById(selectedId);
				
				 
				
				List<NameValuePair>userInput=new ArrayList<NameValuePair>();
				List<NameValuePair>serverAddress=new ArrayList<NameValuePair>();
				
				serverAddress.add(new BasicNameValuePair("serverAddress", "mis_logininfo.php"));
				
				userInput.add(new BasicNameValuePair("username",userInfo.getString("username")));
				userInput.add(new BasicNameValuePair("password",userInfo.getString("password")));
				userInput.add(new BasicNameValuePair("roll",userInfo.getString("roll")));	
				
				userInput.add(new BasicNameValuePair("name",firstName.getText().toString().trim()+" "+lastName.getText().toString().trim()));
				userInput.add(new BasicNameValuePair("address",address.getText().toString().trim()));
				userInput.add(new BasicNameValuePair("mobileno",mobileNumber.getText().toString().trim()));
				
				userInput.add(new BasicNameValuePair("age",age.getSelectedItem().toString().trim()));
				userInput.add(new BasicNameValuePair("city",city.getSelectedItem().toString()));
				userInput.add(new BasicNameValuePair("gender", selectedRadioButton.getText().toString()));
				
				if(regNumber.isShown()){
					userInput.add(new BasicNameValuePair("regno",regNumber.getText().toString().trim()));
				}else{
					userInput.add(new BasicNameValuePair("regno",""));
				}
				
				
				try {
					JSONObject jsonObjectResult=  new BackgroundProcess().execute(serverAddress,userInput).get();

					int result= jsonObjectResult.getInt("success");
					Intent i=null;
					i=new Intent(SignupActivity2.this,LoginActivity.class);
					
					
					if(result==1){
						Toast.makeText(SignupActivity2.this, "Successfully created new user", Toast.LENGTH_LONG).show();
						startActivity(i);
						finish();
					}else{
						Toast.makeText(SignupActivity2.this, "Unable to create new user", Toast.LENGTH_LONG).show();
						startActivity(i);
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
