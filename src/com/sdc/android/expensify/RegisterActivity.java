package com.sdc.android.expensify;

import com.sdc.android.milestone.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends Activity {
	Intent i=null;
	EditText t1,t2,t3;
	boolean flag=false;
	SQLiteDatabase db=null;
	Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        
        t1=(EditText)findViewById(R.id.reg_fullname);
		t2=(EditText)findViewById(R.id.reg_email);
		
		t3=(EditText)findViewById(R.id.reg_password);
		
        
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
				finish();
			}
		});
        
        
        Button b1;
        b1=(Button) findViewById(R.id.btnRegister);
        b1.setOnClickListener(new View.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String fname=t1.getText().toString();
				String email=t2.getText().toString();
				String password=t3.getText().toString();
				if(fname.equals("")||email.equals("")||password.equals(""))
				{
					Toast.makeText(RegisterActivity.this, "INVALID INPUT!!!", Toast.LENGTH_LONG).show();
					
					
				}
				else
				{
					if(is_email(email))
					{
						db=openOrCreateDatabase("mgmt", MODE_PRIVATE, null);
						db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar,logged_in varchar);");
						db.execSQL("insert into login values('"+fname+"','9738805498','"+email+"','"+password+"','NO','NO');");
						db.close();
						
						
					}
					else
						Toast.makeText(RegisterActivity.this, "INVALID EMAIL PROVIDED!!!", Toast.LENGTH_LONG).show();	
				}
				if (password.length() < 5)
				{
					Toast.makeText(RegisterActivity.this, "Invalid password length!!!", Toast.LENGTH_LONG).show();
				}
				else
					startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
				
			}

    });
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
    
  