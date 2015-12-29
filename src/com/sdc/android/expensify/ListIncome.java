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

public class ListIncome extends Activity 
{
	ListView income_list;
	SQLiteDatabase db;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.list_incomecate);
	
	income_list=(ListView) findViewById(R.id.income_list);
	
	
	 final ArrayList<String> list1 = new ArrayList<String>();
	 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	// db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
	 Cursor c=db.rawQuery("select * from incomecate", null);
	 while(c.moveToNext())
	 {
		 String income_title=c.getString(c.getColumnIndex("incometitle"));
		 list1.add(income_title);
	 }
	 db.close();
	 
	   
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listlayout,R.id.listTextView, list1);
	    income_list.setAdapter(adapter);
	    income_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    	public void onItemClick(AdapterView<?> parent, View selectedView,
					int position, long id) {
				// TODO Auto-generated method stub
				 String selectedincme=list1.get(position);
				 
				 
				 Intent intent = new Intent(ListIncome.this,IncomeUpdateDelete.class);
			     intent.putExtra("income_name", selectedincme);
				 startActivity(intent);

				 Toast.makeText(getApplicationContext(), "SELECT Income : "+selectedincme,   Toast.LENGTH_LONG).show();
	    	}
	    });
}


@Override
public void onBackPressed()
{
     // code here to show dialog
     super.onBackPressed();  // optional depending on your needs

	 Intent intent = new Intent(ListIncome.this, MainActivity.class);
     
	 startActivity(intent);
}
}
