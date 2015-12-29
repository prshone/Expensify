package com.sdc.android.expensify;

import java.util.ArrayList;

import com.sdc.android.milestone.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ExpensesReportActivity extends Activity implements OnClickListener 
{
	String acc_title;
	ListView lv;
	SQLiteDatabase db;
	TextView amt;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expensesreport);
		
		acc_title=this.getIntent().getStringExtra("account_name");
		lv=(ListView) findViewById(R.id.listView1);
		final ArrayList<String> list1 = new ArrayList<String>();
		db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
		String selectQuery = "SELECT * from payments where acctitle=?";
		Cursor c1 = db.rawQuery(selectQuery, new String[] { acc_title});
		double final_amt=0.0;
		amt=(TextView) findViewById(R.id.final_amount);
		
		while (c1.moveToNext()) {
		
			double amount=Double.parseDouble(c1.getString(c1.getColumnIndex("amount")));
			final_amt+=amount;
			 String data=c1.getString(c1.getColumnIndex("expenses_cat"))+"		"+amount+"		"+c1.getString(c1.getColumnIndex("date"));
			 list1.add(data);
		}
		
		amt.setText(Double.toString(final_amt));
		c1.close();
		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list1);
		    lv.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
