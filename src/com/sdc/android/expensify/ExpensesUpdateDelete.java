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

public class ExpensesUpdateDelete  extends Activity
{
	String sel_ex_name="";
	EditText extitle,exdesc;
	Button btnupdate_expenses,btndelete_expenses;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expenses_updel);
		
		
		sel_ex_name=this.getIntent().getStringExtra("expenses_name");
		Toast.makeText(getApplicationContext(), "IN THIS SECTION : "+sel_ex_name,   Toast.LENGTH_LONG).show();
		
		 extitle=(EditText) findViewById(R.id.extitle);
		 exdesc=(EditText) findViewById(R.id.exdesc);
		 
			db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
			String selectQuery = "SELECT * from expensescate where expensestitle=?";
			Cursor c = db.rawQuery(selectQuery, new String[] { sel_ex_name});
			if (c.moveToFirst()) {
				 extitle.setText(c.getString(c.getColumnIndex("expensestitle")));
				 exdesc.setText(c.getString(c.getColumnIndex("description")));
				
			}
			c.close();
			 db.close();
			 
		 btnupdate_expenses=(Button) findViewById(R.id.btnupdate_expenses);
		 
		 btnupdate_expenses.setOnClickListener(new View.OnClickListener(){

	             @Override
	             public void onClick(View v) 
	             {   // startActivity(new Intent(TransactionActivity.this,ReceiptActivity.class));
	            	 
	            	 if(v==btnupdate_expenses)
	         		{
	         			db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	         			
	         	        ContentValues values = new ContentValues();
	         	        values.put("expensestitle", extitle.getText().toString());
	         	        values.put("description", exdesc.getText().toString());

	         	        // updating row
	         	        db.update("expensescate", values, "expensestitle=?",new String[] { sel_ex_name });
	         	        
	         	        db.close();
	         			
	         			Intent in=new Intent(ExpensesUpdateDelete.this,ListExpenses.class);
	         			in.putExtra("expenses_name", sel_ex_name);
	         			Toast.makeText(getApplicationContext(), "Record Updated Successfully",   Toast.LENGTH_LONG).show();
	         			startActivity(in);
	         			
	         		}
	             }
	         });
		 
		 
		 
btndelete_expenses=(Button) findViewById(R.id.btndelete_expenses);
		 
		 btndelete_expenses.setOnClickListener(new View.OnClickListener(){

	             @Override
	             public void onClick(View v) 
	             {   // startActivity(new Intent(TransactionActivity.this,ReceiptActivity.class));
	            	 
	            	 if(v==btndelete_expenses)
	         		{
	            		 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
	         			db.delete("expensescate", "expensestitle=?", new String[] { extitle.getText().toString() });
	         			db.close();
	         			Toast.makeText(getApplicationContext(), "Record Deleted Successfully",Toast.LENGTH_LONG).show();
	         			Intent in=new Intent(ExpensesUpdateDelete.this,ListExpenses.class);
	         			in.putExtra("income_name", sel_ex_name);
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

		 Intent intent = new Intent(ExpensesUpdateDelete.this, ListExpenses.class);
	     
		 startActivity(intent);
	}
}
