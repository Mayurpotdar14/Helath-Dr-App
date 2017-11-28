package com.mis.background;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;



import android.util.Log;

public class JsonParser {
	public JsonParser(){
		
	}
	public JSONObject createJsonObjectFromServerResponse(InputStream inputStream){
		
		BufferedReader bufferReader = null;
		StringBuilder stringBulider=new StringBuilder();
		String jsonString = null;
		JSONObject jsonObject = null;
		String line=null;
	
		try{
		bufferReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"),8);
		while((line=bufferReader.readLine())!=null){
			stringBulider.append(line+"\n");	
		}
		inputStream.close();
		jsonString=stringBulider.toString();		
		Log.d("server response", jsonString);
		jsonObject=new JSONObject(jsonString);
		
		}catch (Exception e) {
			
		} 
						
		return jsonObject;
	}
	
}