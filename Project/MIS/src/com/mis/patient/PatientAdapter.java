package com.mis.patient;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mis.authentication.R;


public class PatientAdapter extends ArrayAdapter<JSONObject>  {
Context context;
List<JSONObject> obj=null;

	public PatientAdapter(Context context,List<JSONObject> obj) {
		//It gives xml view(layout)which are used by list to display result 
		super(context, R.layout.patientmedicine,obj);
		this.context=context;
		this.obj=obj;
	}
	@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return super.getCount();		
		}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder;
			if(convertView==null){
				LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=layoutInflater.inflate(R.layout.patientmedicine, parent, false);				
				holder=new Holder();
				holder.prescriptionDate=(TextView) convertView.findViewById(R.id.prescriptionDate);
				holder.prescriptionDiseaseName=(TextView)convertView.findViewById(R.id.prescriptionDiseaseName);
				holder.prescriptionDiseaseDesc=(TextView)convertView.findViewById(R.id.prescriptionDiseaseDesc);				
				convertView.setTag(holder);
			}
		
			else{
				holder=(Holder) convertView.getTag();
			}
					
			try{
				holder.prescriptionDate.setText(obj.get(position).getString("MI_DATE")+"-");
				holder.prescriptionDiseaseName.setText(obj.get(position).getString("DI_NAME"));
				holder.prescriptionDiseaseDesc.setText(obj.get(position).getString("DI_DESCRIPTION"));	
				}
				catch(JSONException e){
					Toast.makeText(context, "Unable To Read Server Response", Toast.LENGTH_SHORT).show();
				}
		
			return convertView;
		
		
	}
	 class Holder{
		TextView prescriptionDate;
		TextView prescriptionDiseaseName;
		TextView prescriptionDiseaseDesc;
		
	}
}

