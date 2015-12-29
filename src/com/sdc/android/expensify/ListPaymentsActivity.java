package com.sdc.android.expensify;

import java.util.ArrayList;

import com.sdc.android.milestone.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListPaymentsActivity extends Activity  {
	ListView pay_list;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_payment);
		
		pay_list=(ListView) findViewById(R.id.payment_list);
		
		 final ArrayList<String> list1 = new ArrayList<String>();
		 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
		// db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
		 Cursor c=db.rawQuery("select * from payments", null);
		 while(c.moveToNext())
		 {
			 String acc_title=c.getString(c.getColumnIndex("acctitle"));
			 String acc_cat=c.getString(c.getColumnIndex("expenses_cat"));
			 String date=c.getString(c.getColumnIndex("date"));
			 String display_data=acc_title+" ("+acc_cat+") "+date;
			 list1.add(display_data);
		 }
		 db.close();
		 
		   
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listlayout,R.id.listTextView, list1);
		    pay_list.setAdapter(adapter);
		    pay_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			     

				@Override
				public void onItemClick(AdapterView<?> parent, View selectedView,
						int position, long id) {
					// TODO Auto-generated method stub
					 String selectedacc=list1.get(position);
					 
					 
					 Intent intent = new Intent(ListPaymentsActivity.this,PaymentsUpadeDel.class);
				     intent.putExtra("account_name", selectedacc);
					 startActivity(intent);
					 
					 Toast.makeText(getApplicationContext(), "SELECT Payments Account : "+selectedacc,   Toast.LENGTH_LONG).show();
				}
		    });
		
	}
	
	@Override
	public void onBackPressed()
	{
	     // code here to show dialog
	     super.onBackPressed();  // optional depending on your needs

		 Intent intent = new Intent(ListPaymentsActivity.this, MainActivity.class);
	     
		 startActivity(intent);
	}

}
