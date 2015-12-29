package com.sdc.android.expensify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sdc.android.milestone.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomAdapter1 extends BaseAdapter implements ListAdapter { 
	private List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	private Context context; 
	private MyCustomAdapter1 adapter;


	public MyCustomAdapter1(List<HashMap<String, Object>> list2, Context context) { 
	    this.list = list2; 
	    this.context = context; 
	    this.adapter=this;
	   
	} 

	@Override
	public int getCount() { 
	    return list.size(); 
	} 

	@Override
	public Object getItem(int pos) { 
	    return list.get(pos); 
	} 

	/*
	@Override
	public long getItemId(int pos) { 
	    return list.get(pos).
	    //just return 0 if your list items do not have an Id variable.
	} */

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
	    View view = convertView;
	    if (view == null) {
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	        view = inflater.inflate(R.layout.activity_baisc_layout1, null);
	    } 

	    //Handle TextView and display string from your list
	    TextView Acc_name = (TextView)view.findViewById(R.id.account_name); 
	    Acc_name.setText(list.get(position).get("actitle").toString());
	    
	   
	    TextView Current_amt = (TextView)view.findViewById(R.id.current); 
	    Current_amt.setText(list.get(position).get("curnt").toString()); 
	    //Handle buttons and add onClickListeners
 return view; 
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}





	}
