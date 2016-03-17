package com.commonfundmanager.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.commonfundmanager.R;
import com.commonfundmanager.beans.Transaction;
import com.commonfundmanager.utils.DbHandler;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ClaimActivity extends Activity {
public	int tDate=0;
public	int tMonth=0;
public int tYear=0;
	
SharedPreferences pref;
Editor editor;
DbHandler dbHandler=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestclaim_activity);
		
		pref= getApplicationContext().getSharedPreferences("My Pref", 0);
		editor=pref.edit();
		dbHandler = new DbHandler(getApplicationContext());
		
	}
	Calendar myCalendar = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	        // TODO Auto-generated method stub
	        myCalendar.set(Calendar.YEAR, year);
	        myCalendar.set(Calendar.MONTH, monthOfYear);
	        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	        updateLabel();
	    }

	};
	
	public void showDatePickerDialog(View v) {
	new DatePickerDialog(ClaimActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
	}
	public void setClaimDate(int day, int month, int year)
	{
		((EditText)findViewById(R.id.t_date)).setText(day+"/"+month+"/"+year);
	}
	
	 private void updateLabel() {

		    String myFormat = "MM/dd/yy"; //In which you need put here
		    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
		    ((EditText)findViewById(R.id.t_date)).setText(sdf.format(myCalendar.getTime()));
	}
	 public void submitClaim(View view)
	 {
		String user = String.valueOf(((Spinner)findViewById(R.id.member_name)).getSelectedItem());
		String claimDate= ((EditText)findViewById(R.id.t_date)).getText().toString();
		String requestType=String.valueOf(((Spinner)findViewById(R.id.request_type)).getSelectedItem());
		String category=String.valueOf(((Spinner)findViewById(R.id.claim_category)).getSelectedItem());
		String claimItems= ((EditText)findViewById(R.id.claim_items)).getText().toString();
		double claimAmount= Double.valueOf(((EditText)findViewById(R.id.claim_amount)).getText().toString().trim());
		
		Transaction tn= new Transaction(user, claimDate, requestType, category, claimItems, claimAmount);
		Intent myIntent=null;
		if(dbHandler.addTransaction(tn))
		{
			Toast.makeText(getApplicationContext(),"Transaction recorded successfully.", Toast.LENGTH_SHORT).show();
			String userrole=pref.getString("userrole", null);
			if(userrole.equals("member"))
			{
				myIntent = new Intent(ClaimActivity.this, MemberHomeActivity.class);
				startActivity(myIntent);
				
			}
		}
	 }

}

