package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountUpdateDelete  extends Activity implements OnClickListener
{
	String sel_ac_name="";
	EditText actitle,desc,initi,curnt;
	Button btnupdate_ac,btndelete_ac;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_updel);
		
		sel_ac_name=this.getIntent().getStringExtra("account_name");
		Toast.makeText(getApplicationContext(), "IN THIS SECTION : "+sel_ac_name,   Toast.LENGTH_LONG).show();
		
		actitle=(EditText) findViewById(R.id.actitle);
		desc=(EditText) findViewById(R.id.desc);
		initi=(EditText) findViewById(R.id.initi);
		curnt=(EditText) findViewById(R.id.curnt);
		
		
		btnupdate_ac=(Button) findViewById(R.id.btnupdate_ac);
		btnupdate_ac.setOnClickListener(AccountUpdateDelete.this);
		
		btndelete_ac=(Button) findViewById(R.id.btndelete_ac);
		btndelete_ac.setOnClickListener(AccountUpdateDelete.this);
		
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
		// Cursor c=db.rawQuery("select * from account where accounttitle='sel_ac_name'", null);
		/* if(c.moveToFirst())
		 {
			 actitle.setText(c.getString(c.getColumnIndex("accounttitle")));
			 desc.setText(c.getString(c.getColumnIndex("description")));
			 initi.setText(c.getString(c.getColumnIndex("initialbal")));
			 curnt.setText(c.getString(c.getColumnIndex("currentbal")));
			 
		 }
		 else
		 {
			 Toast.makeText(getApplicationContext(), "NO ROW EXISTS",   Toast.LENGTH_LONG).show();
				
		 }*/
		 db.close();
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnupdate_ac)
		{
			db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
			/*String updateQuery = "update account set accounttitle=?,description=?,initialbal=?,currentbal=? where accounttitle=?";
			Cursor c = db.rawQuery(updateQuery, new String[] { actitle.getText().toString(),desc.getText().toString(),initi.getText().toString(),curnt.getText().toString(),sel_ac_name});
			c.close();*/
			
			

	        ContentValues values = new ContentValues();
	        values.put("accounttitle", actitle.getText().toString());
	        values.put("description", desc.getText().toString());
	        values.put("initialbal", initi.getText().toString());
	        values.put("currentbal", curnt.getText().toString());

	        // updating row
	        db.update("account", values, "accounttitle=?",new String[] { sel_ac_name });
	        
	        db.close();
			
			Intent in=new Intent(AccountUpdateDelete.this,ListAccount.class);
			in.putExtra("account_name", sel_ac_name);
			Toast.makeText(getApplicationContext(), "Record Updated Successfully",   Toast.LENGTH_LONG).show();
			startActivity(in);
			
		}
		if(v==btndelete_ac)
		{
			db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
			db.delete("account", "accounttitle= ?", new String[] { actitle.getText().toString() });
			db.close();
			Toast.makeText(getApplicationContext(), "Record Deletec Successfully",   Toast.LENGTH_LONG).show();
			Intent in=new Intent(AccountUpdateDelete.this,ListAccount.class);
			in.putExtra("account_name", sel_ac_name);
			startActivity(in);
			
		}
		
	}

}
