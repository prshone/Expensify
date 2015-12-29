package com.sdc.android.expensify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sdc.android.milestone.R;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//java file
public class DashboardActivity extends Fragment{

	private TextView tv_title;
	SQLiteDatabase db;
    String actitle,initi,curnt;
    ListView lv;
    MyCustomAdapter1 adapter1;
    ImageView iv;
	OnKeyListener pressed;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View v = inflater.inflate(R.layout.activity_dashboard, container, false);
		
		

		tv_title =(TextView)v.findViewById(R.id.tv_title);
		tv_title.setText("Dashboard");
		v.setOnKeyListener(pressed);
		pressed = new OnKeyListener() { 
			@Override 
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{ 
				if( keyCode == KeyEvent.KEYCODE_BACK )
				{  Toast.makeText(getActivity(),"LLLLLLLLLLLLL", 13000).show(); return true;} 
				return false; 
			} 
		};
		
		
		iv=(ImageView) v.findViewById(R.id.image3);
		 iv.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
           	//CODE FOR LOGOUT
           	db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
          //CODE FOR LOGOUT
 	        ContentValues values = new ContentValues();
 	        values.put("logged_in", "NO");
 	        	        // updating row
 	        db.update("login", values, null,null);
 	        
 	        db.close();
    	       
    	        	        // updating row
    	       // db.update("login", values, "email_id=?",new String[] { email });
    	       Intent in=new Intent(getActivity(),LoginActivity.class);
      		startActivity(in);
    	      }
    });
		
		
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
	 	        	hm.put("actitle",actitle);
	 	        	hm.put("curnt", curnt);
	 	        	
	 	        	list.add(hm);
	 	        	
	 		}
	 		c.close();
	         
	 		adapter1 = new MyCustomAdapter1(list, this.getActivity());
	 		
	 		//handle listview and assign adapter
	 		//lv=(ListView) getActivity().findViewById(R.id.listView1);
	         lv.setAdapter(adapter1);
	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	

}
	    
