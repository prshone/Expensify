package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TransactionActivity extends Fragment {
	//private ProgressWheel prog_one_visitor, prog_two_visitor;
	private TextView  tv_title;
	//private TabHost mTabHost;
    Button bt1,bt2;
    SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.activity_transaction, container, false);
		//reLoad(v);
		init(v);
	
		bt1 = (Button) v.findViewById(R.id.btnreceipt);
	     bt1.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View arg0) 
             {   // startActivity(new Intent(TransactionActivity.this,ReceiptActivity.class));
            	 //if()
            	 startActivity(new Intent("com.sdc.android.milestone.RECEIPTACTIVITY")); 
             }
         });
	     
			bt2 = (Button) v.findViewById(R.id.btnpayment);
		     bt2.setOnClickListener(new View.OnClickListener(){

	             @Override
	             public void onClick(View arg0) 
	             {   // startActivity(new Intent(TransactionActivity.this,ReceiptActivity.class));
	            	 startActivity(new Intent("com.sdc.android.milestone.PAYMENTACTIVITY")); 
	             }
	         });
		     
		     db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
    		 db.execSQL("create table if not exists incomecate(income_id integer primary key autoincrement,incometitle varchar,description varchar,flag varchar);");   
    		 Cursor c=db.rawQuery("select * from incomecate", null);
    		 if(c.moveToFirst())
    		 {
    			 
    		 }
    		 else
    		 {
    			 bt1.setEnabled(false);
    		 }
    		 
    		 
    		 db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
    		 db.execSQL("create table if not exists expensescate(expenses_id integer primary key autoincrement,expensestitle varchar,description varchar,flag varchar);");
    		 c=db.rawQuery("select * from expensescate", null);
    		 if(c.moveToFirst())
    		 {
    			 
    		 }
    		 else
    		 {
    			 bt2.setEnabled(false);
    		 }
    		 db.close();
	        return v;
    		
    }

	private void init(View v) {
		// TODO Auto-generated method stub
		tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("Transaction");
         
    
	}
	}
