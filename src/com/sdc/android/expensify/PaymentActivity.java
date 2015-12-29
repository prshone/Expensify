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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class PaymentActivity extends Activity 
{   
	
    private TextView textTransDate;
	private int day, month, year;
	private final int DATE_DIALOG = 1;
	Button b1;
	EditText t1,t2;
	boolean flag=false;
	SQLiteDatabase db=null;
	ImageView iv;
	
	Spinner acc_title,aa_e_cat;
	
	String sel_acc_title,sel_ex_cat;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_payment);
	
	  t1=(EditText) findViewById(R.id.paymentamt);
	  t2=(EditText) findViewById(R.id.desc);
	  b1=(Button) findViewById(R.id.btncreate_payment);
	  
	  iv=(ImageView) findViewById(R.id.imageView2);
		 iv.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
      	 Intent in=new Intent(PaymentActivity.this,ListPaymentsActivity.class);
   		startActivity(in);
       }
   });
	  
	  Calendar cal;
	  
	  acc_title=(Spinner) findViewById(R.id.spinnerexactitle);
	  
	  final ArrayList<String> list1 = new ArrayList<String>();
		 db=openOrCreateDatabase("mgmt", Context.MODE_PRIVATE, null);
		// db.execSQL("create table if not exists account(ac_id integer primary key autoincrement,accounttitle varchar,description varchar,initialbal varchar,currentbal varchar,flag varchar);");
		 Cursor c=db.rawQuery("select * from account", null);
		 while(c.moveToNext())
		 {
			 String acc_title=c.getString(c.getColumnIndex("accounttitle"));
			 list1.add(acc_title);
		 }
		 db.close();
		 ArrayAdapter<String> adapter_acc=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list1);
		 acc_title.setAdapter(adapter_acc);
		 
		 acc_title.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					sel_acc_title=(String) acc_title.getItemAtPosition(position);
					Toast.makeText(PaymentActivity.this, "SEL ACC :"+sel_acc_title, Toast.LENGTH_LONG).show();
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
			 while(c2.moveToNext())
			 {
				 String aa_e_cat=c2.getString(c2.getColumnIndex("expensestitle"));
				 list2.add(aa_e_cat);
			 }
			 db.close();
			 ArrayAdapter<String> adapter_expenses=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list2);
			 aa_e_cat.setAdapter(adapter_expenses);
			 
			 aa_e_cat.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						sel_ex_cat=(String) aa_e_cat.getItemAtPosition(position);
						Toast.makeText(PaymentActivity.this, "SEL ACC :"+sel_ex_cat, Toast.LENGTH_LONG).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
			 
	  b1.setOnClickListener(new View.OnClickListener(){
  		public void onClick(View v) 
			{
  			String payamt=t1.getText().toString();
				String desc=t2.getText().toString();
				
				if(payamt.equals("")||desc.equals(""))
				{
					Toast.makeText(PaymentActivity.this, "INVALID INPUT!!!", Toast.LENGTH_LONG).show();
					
					
				}
				else
				{
					startActivity(new Intent("com.sdc.android.milestone.PAYMENTACTIVITY")); 
					//startActivity(new Intent(ReceiptActivity.this,TransactionActivity.class));
					Toast.makeText(PaymentActivity.this, "Payments Done!!!", Toast.LENGTH_LONG).show();
				}
			}
   });
	  
	
	
	  textTransDate = (TextView) this.findViewById(R.id.textTransDate);
	 // get the current date
    cal = Calendar.getInstance();
   year = cal.get(Calendar.YEAR);
   month = cal.get(Calendar.MONTH);
   day = cal.get(Calendar.DAY_OF_MONTH);
   updateDateDisplay();
	
	//Button b1=(Button) findViewById(R.id.);
   
   b1.setOnClickListener(new View.OnClickListener(){
		public void onClick(View v) 
		{
			String payamt=t1.getText().toString();
			String desc=t2.getText().toString();
			
			if(payamt.equals("")||desc.equals(""))
			{
				Toast.makeText(PaymentActivity.this, "INVALID INPUT!!!", Toast.LENGTH_LONG).show();
				
				
			}
			else
			{
				//startActivity(new Intent("com.sdc.android.milestone.RECEIPTACTIVITY")); 
				//startActivity(new Intent(ReceiptActivity.this,TransactionActivity.class));
				db=openOrCreateDatabase("mgmt", MODE_PRIVATE, null);
				
				
				String old_amount="";	
				String selectQuery = "SELECT * from account where accounttitle=?";
					Cursor c1 = db.rawQuery(selectQuery, new String[] { sel_acc_title});
					if (c1.moveToFirst()) {
						old_amount=c1.getString(c1.getColumnIndex("currentbal"));
						
					}
				float in_num_current_bal=Float.parseFloat(old_amount);
				float in_num_pay_amt=Float.parseFloat(payamt);
				float final_amt=in_num_current_bal-in_num_pay_amt;
				
				if(final_amt<0)
				{
					Toast.makeText(PaymentActivity.this, "InSufficient Balance in the Account", Toast.LENGTH_LONG).show();
				}
				else
				{
					db.execSQL("create table if not exists payments (acctitle varchar,expenses_cat varchar,amount varchar,descrip varchar,date varchar);");
					Log.d("AMS","THE TRANC DATE IS : "+textTransDate.getText().toString());
					db.execSQL("insert into payments values('"+sel_acc_title+"','"+sel_ex_cat+"','"+payamt+"','"+desc+"','"+textTransDate.getText().toString()+"');");
					
					
					
					
					ContentValues values = new ContentValues();
			        values.put("currentbal", Float.toString(final_amt));
			        			        
			        // updating row
			        db.update("account", values, "accounttitle=?",new String[] { sel_acc_title});
					
				}
				
				
				
				db.close();
				Toast.makeText(PaymentActivity.this, "Payments Done!!!", Toast.LENGTH_LONG).show();
				Intent in=new Intent(PaymentActivity.this,ListPaymentsActivity.class);
				startActivity(in);
			}
		}
});
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
}


/*private DatePickerDialog.OnDateSetListener dateSetListener =
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
}*/