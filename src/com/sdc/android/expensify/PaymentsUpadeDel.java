package com.sdc.android.expensify;

import java.util.ArrayList;
import java.util.Calendar;

import com.sdc.android.milestone.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class PaymentsUpadeDel extends Activity implements OnClickListener
{
	
	String sel_ac_name="";
	 String ac_name,category;
	Spinner acc_title,aa_e_cat;
	String sel_acc_title,sel_ex_cat;
	private int day, month, year;
	private final int DATE_DIALOG = 1;
	EditText t1,t2;
	private TextView textTransDate;
	EditText actitle,desc,initi,curnt;
	Button btnupdate_payment,btndelete_payment;
	SQLiteDatabase db;
	
	String db_acc,db_cat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_updel);
		
		sel_ac_name=this.getIntent().getStringExtra("account_name");
		
		  t1=(EditText) findViewById(R.id.paymentamt);
		  t2=(EditText) findViewById(R.id.desc);
		  textTransDate = (TextView) this.findViewById(R.id.textTransDate);
		  Calendar cal;
		  
		  // get the current date
		    cal = Calendar.getInstance();
		   year = cal.get(Calendar.YEAR);
		   month = cal.get(Calendar.MONTH);
		   day = cal.get(Calendar.DAY_OF_MONTH);
		   updateDateDisplay();
		  
		  db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
		  int start=sel_ac_name.indexOf("(");
		  int end=sel_ac_name.indexOf(")");
		  ac_name=sel_ac_name.substring(0,start-1);
		  Log.d("AMS","OBT ACC NAME : "+ac_name);
		  category=sel_ac_name.substring(start+1,end);
		  String split_data[]=sel_ac_name.split(" ");
		  Log.d("AMS","OBT ACC DATE : "+split_data[2]);
		  
		  Log.d("AMS","OBT CAT NAME : "+category);
		  Toast.makeText(getApplicationContext(), "ACC NAM EIS : "+ac_name, 5000).show();
		  Toast.makeText(getApplicationContext(), "CAT  NAM EIS : "+category, 5000).show();
			String selectQuery = "SELECT * from payments where acctitle=? and expenses_cat=? and date=?";
			Cursor c1 = db.rawQuery(selectQuery, new String[] { ac_name,category,split_data[2]});
			if (c1.moveToFirst()) {
				Log.d("AMS","INSIDE DB SECTION ");
				Log.d("AMS","ACC NAM EIS : "+ac_name);
				Log.d("AMS","CAT  NAM EIS : "+category);
				db_acc=c1.getString(c1.getColumnIndex("acctitle"));
				db_cat=c1.getString(c1.getColumnIndex("expenses_cat"));
				t1.setText(c1.getString(c1.getColumnIndex("amount")));
				t2.setText(c1.getString(c1.getColumnIndex("descrip")));
				textTransDate.setText(c1.getString(c1.getColumnIndex("date")));
			}
			
			//String selectQuery = "SELECT * from reciepts where acctitle=? and income_cat=? and date=?";
			//Cursor c1 = db.rawQuery(selectQuery, new String[] { ac_name,category,split_data[2]});
			//if (c1.moveToFirst()) {
		
			c1.close();
		  
		  acc_title=(Spinner) findViewById(R.id.spinnerexactitle);
		  
		 
		  
		  final ArrayList<String> list1 = new ArrayList<String>();
			 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
			// db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
			 Cursor c=db.rawQuery("select * from account", null);
			 int i=0,pos=-1;
			 while(c.moveToNext())
			 {
				 String acc_title=c.getString(c.getColumnIndex("accounttitle"));
				 if(acc_title.equals(db_acc))
				 {
					 pos=i;
				 }
				 list1.add(acc_title);
				 i++;
			 }
			 db.close();
			 ArrayAdapter<String> adapter_acc=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list1);
			 acc_title.setAdapter(adapter_acc);
			 
		     acc_title.setSelection(pos);
			 
			 
			 acc_title.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						sel_acc_title=(String) acc_title.getItemAtPosition(position);
						Toast.makeText(PaymentsUpadeDel.this, "SEL ACC :"+sel_acc_title, Toast.LENGTH_LONG).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
			 
		  
			  aa_e_cat=(Spinner) findViewById(R.id.spinnerexpensestitle);
			  
			  
			  final ArrayList<String> list2 = new ArrayList<String>();
				 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
				// db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
				 Cursor c2=db.rawQuery("select * from expensescate", null);
				 i=0;
				 int pos1=-1;
				 while(c2.moveToNext())
				 {
					 
					 String aa_e_cat=c2.getString(c2.getColumnIndex("expensestitle"));
					 Log.d("AMS","THE DB EX PENSES CAT :"+db_cat);
					 Log.d("AMS","THE DROPDOWN EX PENSES CAT :"+aa_e_cat);
					 if(aa_e_cat.equals(db_cat))
					 {
						 pos1=i;
					 }
					 list2.add(aa_e_cat);
					 i++;
				 }
				 db.close();
				 ArrayAdapter<String> adapter_expenses=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list2);
				 aa_e_cat.setAdapter(adapter_expenses);
				 aa_e_cat.setSelection(pos1);
				 aa_e_cat.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							sel_ex_cat=(String) aa_e_cat.getItemAtPosition(position);
							Toast.makeText(PaymentsUpadeDel.this, "SEL ACC :"+sel_ex_cat, Toast.LENGTH_LONG).show();
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
					});
				 
				 
				
			   
			   btnupdate_payment=(Button) findViewById(R.id.btnupdate_payments);
			   btnupdate_payment.setOnClickListener(PaymentsUpadeDel.this);
			   
			   btndelete_payment=(Button) findViewById(R.id.btndelete_payments);
			   btndelete_payment.setOnClickListener(PaymentsUpadeDel.this);
		 
		
	}
	
	private DatePickerDialog.OnDateSetListener dateSetListener =
			new DatePickerDialog.OnDateSetListener() {

			    public void onDateSet(DatePicker view, int pYear,int pMonth, int pDay) {
			        year = pYear;
			        month = pMonth;
			        day = pDay;
			        updateDateDisplay();
			    }
			};


			public void showDateDialog(View v) {
				  showDialog(DATE_DIALOG);
			}

			@Override
			protected Dialog onCreateDialog(int id) {
				super.onCreateDialog(id);
				
			  switch (id) {
			  case DATE_DIALOG:
			      return new DatePickerDialog(this,
			                  dateSetListener, year, month, day);
			  }
			  return null;
			}


			private void updateDateDisplay() {
			    // Month is 0 based so add 1
			    textTransDate.setText( String.format("%d-%d-%d",year,month + 1,day));
			}

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v== btnupdate_payment)
				{
					db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
					/*String updateQuery = "update account set accounttitle=?,description=?,initialbal=?,currentbal=? where accounttitle=?";
					Cursor c = db.rawQuery(updateQuery, new String[] { actitle.getText().toString(),desc.getText().toString(),initi.getText().toString(),curnt.getText().toString(),sel_ac_name});
					c.close();*/
					
					

			        ContentValues values = new ContentValues();
			        values.put("acctitle", sel_acc_title);
			        values.put("expenses_cat", sel_ex_cat);
			        values.put("amount", t1.getText().toString());
			        values.put("descrip", t2.getText().toString());
			        values.put("date", textTransDate.getText().toString());
			        
			        // updating row
			        db.update("payments", values, "acctitle=?",new String[] { sel_ac_name});
			        
			        db.close();
					
					Intent in=new Intent(PaymentsUpadeDel.this,ListPaymentsActivity.class);
					
					Toast.makeText(getApplicationContext(), "Record Updated Successfully",   Toast.LENGTH_LONG).show();
					startActivity(in);
					
				}
				if(v==btndelete_payment)
				{
					db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
					db.delete("payments", "acctitle=? and expenses_cat=?", new String[] {ac_name,category });
					//db.delete("payments", "acctitle= ?", new String[] { sel_ac_name });
					db.close();
					Toast.makeText(getApplicationContext(), "Record Deleted Successfully",   Toast.LENGTH_LONG).show();
					Intent in=new Intent(PaymentsUpadeDel.this,ListPaymentsActivity.class);
					in.putExtra("account_name", sel_ac_name);
					startActivity(in);
					
				}
				
			}

}
