package com.mis.doctor;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.mis.authentication.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MedicineAdapter extends ArrayAdapter<JSONObject>  {
Context context;
List<JSONObject> obj=null;

	public MedicineAdapter(Context context,List<JSONObject> obj) {
		//It gives xml view(layout)which are used by list to display result 
		super(context, R.layout.medicinelist,obj);
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
			if(convertView==null)
			{
				LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView=layoutInflater.inflate(R.layout.medicinelist, parent, false);
				
				holder=new Holder();
				holder.medicineType=(TextView) convertView.findViewById(R.id.l_medicineType);
				holder.medicineName=(TextView)convertView.findViewById(R.id.l_medicineName);
				holder.medicineQuantity=(TextView)convertView.findViewById(R.id.l_medicineQuantity);
				holder.medicineDose=(TextView)convertView.findViewById(R.id.l_medicineDose);
				convertView.setTag(holder);
			}
		
			else
			{
				holder=(Holder) convertView.getTag();
			}
			
			
			try{
				holder.medicineType.setText(obj.get(position).getString("MI_MEDICINE_TYPE")+"-");
				holder.medicineName.setText(obj.get(position).getString("MI_MEDICINE_NAME")+" ");
				holder.medicineQuantity.setText(obj.get(position).getString("MI_MEDICINE_QUANTITY"));
				holder.medicineDose.setText(obj.get(position).getString("MI_MEDICINE_DOSE"));
					
				}
				catch(JSONException e)
				{
					Toast.makeText(context, "Unable To Read Server Response", Toast.LENGTH_SHORT).show();
				}
		
		
		
			return convertView;
		
		
	}
	 class Holder
	{
		TextView medicineType;
		TextView medicineName;
		TextView medicineQuantity;
		TextView medicineDose;
	}
}
