package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpensesActivity extends Fragment
{
        private TextView  tv_title;
	    Button b1;
		EditText t1,t2;
		String extitle,exdesc;
		boolean flag=false;
		SQLiteDatabase db;
		ImageView iv;

   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
   {
		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.activity_expenses, container, false);
		init(v);
		
		b1=(Button) v.findViewById(R.id.btncre_expcate);
		
		 t1 = (EditText) v.findViewById(R.id.extitle);
		 t2 = (EditText) v.findViewById(R.id.exdesc);
		 
		   b1.setOnClickListener(new View.OnClickListener(){

	            @Override
	            public void onClick(View v) 
	            {
	            if(v==b1)
	        	{   
	               extitle = t1.getText().toString();
	       		   exdesc=t2.getText().toString();
	       		    
	       		 if(extitle.equals("")||exdesc.equals(""))
	     		{  
	       			Toast.makeText(getActivity().getApplicationContext(),"INVALID INPUT PROVIDED",Toast.LENGTH_LONG).show();	
	     		}
	     		else
	     		{  
	     			db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	       			db.execSQL("create table if not exists expensescate(expenses_id integer primary key autoincrement,expensestitle varchar,description varchar,flag varchar);");
	    			db.execSQL("insert into expensescate(expensestitle,description,flag) values('"+extitle+"','"+exdesc+"','NO');");
	    			db.close();
	    			
	    			  startActivity(new Intent("com.sdc.android.milestone.LISTEXPENSES")); 
	           	   Toast.makeText(getActivity().getApplicationContext(),"EXPENSES CATEGORY CREATED",Toast.LENGTH_LONG).show();
	     		}
	        	}
	            }
	        });
		   
		   iv=(ImageView) v.findViewById(R.id.imageView2);
			 iv.setOnClickListener(new OnClickListener() {
	             @Override
	             public void onClick(View v) {
	            	 Intent in=new Intent(getActivity(),ListExpenses.class);
	         		startActivity(in);
	             }
	         });
		
		return v;
	}
	
	private void init(View v) {
		// TODO Auto-generated method stub
		tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("Expenses Category");
         
    
	}

}