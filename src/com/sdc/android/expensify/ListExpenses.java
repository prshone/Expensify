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

public class ListExpenses extends Activity
{
	ListView expenses_list;
	SQLiteDatabase db;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.list_expenses);
	
	expenses_list=(ListView) findViewById(R.id.expenses_list);
	
	
	 final ArrayList<String> list1 = new ArrayList<String>();
	 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	// db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
	 Cursor c=db.rawQuery("select * from expensescate", null);
	 while(c.moveToNext())
	 {
		 String expenses_title=c.getString(c.getColumnIndex("expensestitle"));
		 list1.add(expenses_title);
	 }
	 db.close();
	 
	   
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listlayout,R.id.listTextView, list1);
	    expenses_list.setAdapter(adapter);
	    expenses_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		     

			@Override
			public void onItemClick(AdapterView<?> parent, View selectedView,
					int position, long id) {
				// TODO Auto-generated method stub
				 String selectedexp=list1.get(position);
				 Intent intent = new Intent(ListExpenses.this,ExpensesUpdateDelete.class);
			     intent.putExtra("expenses_name", selectedexp);
				 startActivity(intent);
				 
				 Toast.makeText(getApplicationContext(), "SELECT Expenses : "+selectedexp,   Toast.LENGTH_LONG).show();
			}
	    });
}


@Override
public void onBackPressed()
{
     // code here to show dialog
     super.onBackPressed();  // optional depending on your needs

	 Intent intent = new Intent(ListExpenses.this, MainActivity.class);
     
	 startActivity(intent);
}
}
