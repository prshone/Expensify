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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IncomeUpdateDelete  extends Activity
{
	String sel_in_name="";
	EditText intitle,indesc;
	Button btnupdate_income,btndelete_income;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.income_updel);
		
		
		sel_in_name=this.getIntent().getStringExtra("income_name");
		Toast.makeText(getApplicationContext(), "IN THIS SECTION : "+sel_in_name,   Toast.LENGTH_LONG).show();
		
		 intitle=(EditText) findViewById(R.id.intitle);
		 indesc=(EditText) findViewById(R.id.indesc);
		 
			db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
			String selectQuery = "SELECT * from incomecate where incometitle=?";
			Cursor c = db.rawQuery(selectQuery, new String[] { sel_in_name});
			if (c.moveToFirst()) {
				 intitle.setText(c.getString(c.getColumnIndex("incometitle")));
				 indesc.setText(c.getString(c.getColumnIndex("description")));
				
			}
			c.close();
			 db.close();
			 
		 btnupdate_income=(Button) findViewById(R.id.btnupdate_income);
		 
		 btnupdate_income.setOnClickListener(new View.OnClickListener(){

	             @Override
	             public void onClick(View v) 
	             {   // startActivity(new Intent(TransactionActivity.this,ReceiptActivity.class));
	            	 
	            	 if(v==btnupdate_income)
	         		{
	         			db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	         			
	         	        ContentValues values = new ContentValues();
	         	        values.put("incometitle", intitle.getText().toString());
	         	        values.put("description", indesc.getText().toString());

	         	        // updating row
	         	        db.update("incomecate", values, "incometitle=?",new String[] { sel_in_name });
	         	        
	         	        db.close();
	         			
	         			Intent in=new Intent(IncomeUpdateDelete.this,ListIncome.class);
	         			in.putExtra("income_name", sel_in_name);
	         			Toast.makeText(getApplicationContext(), "Record Updated Successfully",   Toast.LENGTH_LONG).show();
	         			startActivity(in);
	         			
	         		}
	             }
	         });
		 
		 btndelete_income=(Button) findViewById(R.id.btndelete_income);
		 
		 btndelete_income.setOnClickListener(new View.OnClickListener(){

	             @Override
	             public void onClick(View v) 
	             {   // startActivity(new Intent(TransactionActivity.this,ReceiptActivity.class));
	            	 
	            	 if(v==btndelete_income)
	         		{
	            		 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	         			db.delete("incomecate", "incometitle=?", new String[] { intitle.getText().toString() });
	         			db.close();
	         			Toast.makeText(getApplicationContext(), "Record Deleted Successfully",Toast.LENGTH_LONG).show();
	         			Intent in=new Intent(IncomeUpdateDelete.this,ListIncome.class);
	         			in.putExtra("income_name", sel_in_name);
	         			startActivity(in);
	            		 
	            		 
	         			/*db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	         			
	         	        ContentValues values = new ContentValues();
	         	        values.put("incometitle", intitle.getText().toString());
	         	        values.put("description", indesc.getText().toString());

	         	        // updating row
	         	        db.update("incomecate", values, "incometitle=?",new String[] { sel_in_name });
	         	        
	         	        db.close();
	         			
	         			Intent in=new Intent(IncomeUpdateDelete.this,ListIncome.class);
	         			in.putExtra("income_name", sel_in_name);
	         			Toast.makeText(getApplicationContext(), "Record Updated Successfully",   Toast.LENGTH_LONG).show();
	         			startActivity(in);*/
	         			
	         		}
	             }
	         });
		 
		 
		 
		 
	}
	
	
	@Override
	public void onBackPressed()
	{
	     // code here to show dialog
	     super.onBackPressed();  // optional depending on your needs

		 Intent intent = new Intent(IncomeUpdateDelete.this, ListIncome.class);
	     
		 startActivity(intent);
	}
}
