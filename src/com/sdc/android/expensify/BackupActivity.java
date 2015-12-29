package com.sdc.android.expensify;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sdc.android.milestone.R;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BackupActivity extends Fragment 
{

	Button b1;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		
		
		View v = inflater.inflate(R.layout.activity_backup, container, false);
		
		b1=(Button) v.findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
	           @Override
	           public void onClick(View v) {
	           	//CODE FOR LOGOUT
	        	   
	        	   String inFileName1 = getActivity().getDatabasePath("mgmt").getPath();
	        	   final String inFileName = inFileName1;
	        	   Log.d("AMS","FILE NAME IS : "+inFileName1);
	        	    File dbFile = new File(inFileName);
	        	    FileInputStream fis = null;
					try {
						fis = new FileInputStream(dbFile);
						Log.d("AMS","FILE NAME IS : "+dbFile.getPath());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	        	    String outFileName = Environment.getExternalStorageDirectory()+"/database_copy.db";
	        	    Log.d("AMS","EXT FILE NAME IS : "+outFileName);
	        	    // Open the empty db as the output stream
	        	    OutputStream output = null;
					try {
						output = new FileOutputStream(outFileName);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	        	    // Transfer bytes from the inputfile to the outputfile
	        	    byte[] buffer = new byte[1024];
	        	    int length;
	        	    try {
						while ((length = fis.read(buffer))>0){
						    output.write(buffer, 0, length);
						}
						Toast.makeText(getActivity(), "BACKUP FILE CREATED ",Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	        	    // Close the streams
	        	    try {
						output.flush();
						output.close();
		        	    fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	    
	    	      }
	    });
		
		return v;
		
	}
}
