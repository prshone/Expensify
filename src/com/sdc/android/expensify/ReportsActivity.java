package com.sdc.android.expensify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sdc.android.milestone.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.TextView;

public class ReportsActivity extends Fragment {
	//private ProgressWheel prog_one, prog_two, prog_three;
	private TextView  tv_title;
	SQLiteDatabase db;
    String actitle,initi,curnt;
    ListView lv;
    MyCustomAdapter adapter1;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.activity_reports, container, false);
     //setupTabHost(v);
    //reLoad(v);

		init(v);
		
		
		
		
	   
		 
		return v;
	}
	
	@Override 
	public void onViewCreated (View view, Bundle savedInstanceState) {
		lv = (ListView) view.findViewById(R.id.listView1);
	    try 
	    {
	    	 List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	 		db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	 		String selectQuery = "SELECT * from account";
	 		Cursor c = db.rawQuery(selectQuery, null);
	 		while (c.moveToNext()) {
	 			 actitle=c.getString(c.getColumnIndex("accounttitle"));
	 			 initi=c.getString(c.getColumnIndex("initialbal"));
	 			 curnt=c.getString(c.getColumnIndex("currentbal"));
	 			 Log.d("AMS","INSIDE DATA");
	 			    HashMap<String, Object> hm = new HashMap<String,Object>();
	 	        	if(actitle.length()>6)
	 			        hm.put("actitle",actitle.substring(0,6));
	 	        	else
	 	        		hm.put("actitle",actitle);	
	 	        	hm.put("curnt", curnt);
	 	        	
	 	        	list.add(hm);
	 	        	
	 		}
	 		c.close();
	         
	 		adapter1 = new MyCustomAdapter(list, this.getActivity());
	 		
	 		//handle listview and assign adapter
	 		//lv=(ListView) getActivity().findViewById(R.id.listView1);
	         lv.setAdapter(adapter1);
	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	private void init(View v) {
    	
        tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("Reports");
        
	}
}
