package com.sdc.android.expensify;



import com.sdc.android.milestone.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SectionActivity extends Activity implements OnClickListener

{
   Button b1,b2;
   String acc_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_section);
		
		acc_title=this.getIntent().getStringExtra("account_name");
		
		b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(SectionActivity.this);
		b2=(Button) findViewById(R.id.button2);
		b2.setOnClickListener(SectionActivity.this);
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==b1)
		{
			    Intent in=new Intent(SectionActivity.this,IncomeReportActivity.class);
	            in.putExtra("account_name", acc_title);
	            startActivity(in);
		}
		if(v==b2)
		{
			Intent in=new Intent(SectionActivity.this,ExpensesReportActivity.class);
            in.putExtra("account_name", acc_title);
            startActivity(in);
			
		}
	}

}
