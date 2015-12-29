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
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity  {
	Intent i=null;
	EditText t1,t2;
	boolean flag=false;
	SQLiteDatabase db=null;
	 Button b1;
	
	 //TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       
        
        t1=(EditText) findViewById(R.id.email);
		t2=(EditText) findViewById(R.id.pass);
		 b1=(Button) findViewById(R.id.btnLogin);
	
		 
       final TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
       
        
          // Listening to register new account link
        
         db=openOrCreateDatabase("mgmt", MODE_PRIVATE, null);
         db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar,logged_in varchar);");
		 Cursor c=db.rawQuery("select * from login ",null);	
		 String login_status="";
			if(c.moveToFirst())
			{
			  
			   login_status=c.getString(c.getColumnIndex("logged_in"));
			   if(login_status.equals("YES"))
			   {
				   startActivity(new Intent(LoginActivity.this,MainActivity.class));
			   }
			   else
				   registerScreen.setEnabled(false);
			} 
			 db.close();
      
       registerScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				
			
			Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
			startActivity(i);

				
				
					
			}
		});
       
       
        
         b1=(Button) findViewById(R.id.btnLogin);
        b1.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) 
			{
					
				String email=t1.getText().toString();
				String password=t2.getText().toString(); 
				
				if(email.equals("")||password.equals(""))
				{
					Toast.makeText(LoginActivity.this, "INVALID INPUT PROVIDED!!!", Toast.LENGTH_LONG).show();
				}
				else	
				{     //db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar);");
					if(is_email(email))
					{
						
						db=openOrCreateDatabase("mgmt", MODE_PRIVATE, null);
						 Cursor c=db.rawQuery("select * from login where email_id='"+email+"' and password='"+password+"'",null);	
							if(c.moveToFirst())
							{
								
			         			//CODE FOR LOGOUT
			         	        ContentValues values = new ContentValues();
			         	        values.put("logged_in", "YES");
			         	        	        // updating row
			         	        db.update("login", values, "email_id=?",new String[] { email });
			         	        
			         	        db.close();
							  
							  
							  i=new Intent(LoginActivity.this,MainActivity.class);
							   startActivity(i);
							   //db.close();
								//finish();
							   
							
							} 
							else
								Toast.makeText(LoginActivity.this, "INVALID EMAIL/PASSWORD PROVIDED!!!", Toast.LENGTH_LONG).show();
						// TODO Auto-generated method stub
						//startActivity(new Intent(LoginActivity.this,DashBoard.class));
						
						
					}
					else
						Toast.makeText(LoginActivity.this, "INVALID EMAIL PROVIDED!!!", Toast.LENGTH_LONG).show();
				}
				
				
				
			}

    });
        
       /* b1.setOnClickListener(new View.OnClickListener() {
			
            public void onClick(View v) {
                b1.setText("");
            }
        });*/
        
}
   
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	db=openOrCreateDatabase("mgmt", MODE_PRIVATE, null);
        db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar,logged_in varchar);");
		 Cursor c=db.rawQuery("select * from login ",null);	
		 String login_status="";
			if(c.moveToFirst())
			{
			  
			   login_status=c.getString(c.getColumnIndex("logged_in"));
			   if(login_status.equals("YES"))
			   {
				   startActivity(new Intent(LoginActivity.this,MainActivity.class));
			   }
			   
			} 
			 db.close();
    }
    public boolean is_email(String in_email)
    {
          String email_parts[]=in_email.split("@");
 		 //apex@gmail.com
 		 //email_parts[0]=apex,
 		 //email_parts[1]=gmail.com
 		 if(email_parts.length==2)
 		  {
 		     String futher_parts[]=email_parts[1].split("\\.");
 			 //futher_parts[0]=gmail,futher_parts[1]=com
 			  if(futher_parts.length==2)
 			  {
 			        if(futher_parts[1].equals("com")||futher_parts[1].equals("in"))
 					 return true;
 					else
                       return false;					
 			  }
 			  else
 			    return false;
 		  }
 		 else
 		    return false;
    }
    
    public boolean is_number(String in_number)
    {
          try 
 		  {
 		    double data=Double.parseDouble(in_number);
 			return true;
 		  }
 		 catch(Exception e) 
          {
 		    return false;
 		 }
    }		
		
}
   

		
		
			
		

		
    	
