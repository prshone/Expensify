package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends Fragment 
{
    private TextView  tv_title;
    Button b1;
	EditText t1,t2,t3,t4;
	String actitle,desc,initi,curnt;
	boolean flag=false;
	SQLiteDatabase db;
    ImageView iv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.activity_account, container, false);

		init(v);
		
		iv=(ImageView) v.findViewById(R.id.imageView2);
		 iv.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
            	 Intent in=new Intent(getActivity(),ListAccount.class);
         		startActivity(in);
             }
         });
		
		b1=(Button) v.findViewById(R.id.btncreate_ac);
		
		 t1 = (EditText) v.findViewById(R.id.actitle);
		 t2 = (EditText) v.findViewById(R.id.desc);
		 t3 = (EditText) v.findViewById(R.id.initi);
		 t4 = (EditText) v.findViewById(R.id.curnt);
		 
		 
		 
	     b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) 
            {
            if(v==b1)
        	{   
               actitle = t1.getText().toString();
       		   desc=t2.getText().toString();
       		    initi=t3.getText().toString();
       		    curnt=t4.getText().toString();
       		    
       		 if(actitle.equals("")||desc.equals("")|| initi.equals("")||curnt.equals(""))
     		{  
       			Toast.makeText(getActivity().getApplicationContext(),"INVALID INPUT PROVIDED",Toast.LENGTH_LONG).show();
       			// Toast.makeText(getActivity(), "AccountActivity.onCreateView()",Toast.LENGTH_LONG).show();
       			//Toast.makeText(getApplicationContext(), "Account Added Successfully...", Toast.LENGTH_LONG).show();		     
     			// Toast.makeText(AccountActivity.this,"INVALID INPUT PROVIDED",1000).show();
       			
       			
     		}
     		else
     		{  
     			db=getActivity().openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
       			db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
    			db.execSQL("insert into account(accounttitle,description,initialbal,currentbal,flag) values('"+actitle+"','"+desc+"','"+initi+"','"+curnt+"','NO');");
    			db.close();
           	   startActivity(new Intent("com.sdc.android.milestone.LISTACCOUNT")); 
           	   Toast.makeText(getActivity().getApplicationContext(),"ACCOUNT CREATED",Toast.LENGTH_LONG).show();
     		}
        	}
            }
        });
	     

		return v;
	}
	
	
	private void init(View v) {
    	
        tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("Accounts");
        
	}


	
	
	
	
}
