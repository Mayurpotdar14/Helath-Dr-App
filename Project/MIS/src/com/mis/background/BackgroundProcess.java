package com.mis.background;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.mis.authentication.LoginActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class BackgroundProcess extends AsyncTask <List<NameValuePair>,Integer,JSONObject>{
Context context;
String errorMessage=null;
Exception exception=null;
String serverPageName=null;
ProgressDialog progressDialog;



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		//Log.d("TEST", "IN POST EXECUTE");
		//progressDialog=new ProgressDialog(context);
		//progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		//progressDialog.setMessage("Loading...");
		//progressDialog.show();	
		//Log.d("TEST", "IN POST EXECUTE end");
}
	@Override
	protected JSONObject doInBackground(List<NameValuePair>... params) {
		
		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair>serverAddress=params[0];
		JSONObject jsonObject=null;
		
        HttpPost httppost = new HttpPost(LoginActivity.URL+serverAddress.get(0).getValue().toString());
        try {
			httppost.setEntity(new UrlEncodedFormEntity(params[1]));
			HttpResponse response = httpclient.execute(httppost);
		    HttpEntity entity = response.getEntity();
		    InputStream is = entity.getContent();
		    jsonObject=new JsonParser().createJsonObjectFromServerResponse(is);
		    
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return jsonObject;
	}
	
	
	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);		
		//progressDialog.dismiss();
	
}

	
}
