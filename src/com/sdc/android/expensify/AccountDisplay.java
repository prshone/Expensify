package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountDisplay extends Activity
{
	
	String sel_ac_name="";
	EditText actitle,desc,initi,curnt;
	Button btnupdate_ac,btndelete_ac;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountdisplay);
		
		sel_ac_name=this.getIntent().getStringExtra("account_name");
		Toast.makeText(getApplicationContext(), "IN THIS SECTION : "+sel_ac_name,   Toast.LENGTH_LONG).show();
		
		actitle=(EditText) findViewById(R.id.actitle);
		desc=(EditText) findViewById(R.id.desc);
		initi=(EditText) findViewById(R.id.initi);
		curnt=(EditText) findViewById(R.id.curnt);
		
		
		db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
		String selectQuery = "SELECT * from account where accounttitle=?";
		Cursor c = db.rawQuery(selectQuery, new String[] { sel_ac_name});
		if (c.moveToFirst()) {
			 actitle.setText(c.getString(c.getColumnIndex("accounttitle")));
			 desc.setText(c.getString(c.getColumnIndex("description")));
			 initi.setText(c.getString(c.getColumnIndex("initialbal")));
			 curnt.setText(c.getString(c.getColumnIndex("currentbal")));
		}
		c.close();
		 db.close();
	}

}
