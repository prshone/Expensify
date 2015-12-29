package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IncomeActivity extends Fragment {
	//private ProgressWheel prog_one_visitor, prog_two_visitor;
	private TextView  tv_title;
	 Button b1;
		EditText t1,t2;
		String intitle,indesc;
		boolean flag=false;
		SQLiteDatabase db;
		ImageView iv;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.activity_income, container, false);
		init(v);
		b1=(Button) v.findViewById(R.id.btncre_incate);
		
		 t1 = (EditText) v.findViewById(R.id.intitle);
		 t2 = (EditText) v.findViewById(R.id.indesc);
		 
		 v.setFocusableInTouchMode(true); 
		 v.requestFocus();
		 
		 v.setOnKeyListener(new View.OnKeyListener() {
		        @Override
		        public boolean onKey(View v, int keyCode, KeyEvent event) {
                    
		            if (keyCode == KeyEvent.KEYCODE_BACK){
		            	
		                // handle back button

		              //  return true;

		            }

		            return false;
		        }
		    });
		 
		 
		 
		   b1.setOnClickListener(new View.OnClickListener(){

	            @Override
	            public void onClick(View v) 
	            {
	            if(v==b1)
	        	{   
	               intitle = t1.getText().toString();
	       		   indesc=t2.getText().toString();
	       		    
	       		 if(intitle.equals("")||indesc.equals(""))
	     		{  
	       			Toast.makeText(getActivity().getApplicationContext(),"INVALID INPUT PROVIDED",Toast.LENGTH_LONG).show();	
	     		}
	     		else
	     		{  
	     			db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	       			db.execSQL("create table if not exists incomecate(income_id integer primary key autoincrement,incometitle varchar,description varchar,flag varchar);");
	    			db.execSQL("insert into incomecate(incometitle,description,flag) values('"+intitle+"','"+indesc+"','NO');");
	    			db.close();
	    			
	    			  startActivity(new Intent("com.sdc.android.milestone.LISTINCOME")); 
	           	   Toast.makeText(getActivity().getApplicationContext(),"INCOME CATEGORY CREATED",Toast.LENGTH_LONG).show();
	     		}
	        	}
	            }
	        });
		   
		   iv=(ImageView) v.findViewById(R.id.imageView2);
			 iv.setOnClickListener(new OnClickListener() {
	             @Override
	             public void onClick(View v) {
	            	 Intent in=new Intent(getActivity(),ListIncome.class);
	         		startActivity(in);
	             }
	         });
		return v;
	}
	
	
	
	private void init(View v) {
		// TODO Auto-generated method stub
		tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("Income Category");
         
    
	}
	
	public void onBackPressed()
    {
		Toast.makeText(getActivity().getApplicationContext(),"INCOME CATEGORY CREATED",Toast.LENGTH_LONG).show();
    }
}
