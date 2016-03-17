package com.commonfundmanager.activities;


import java.util.Calendar;

import com.commonfundmanager.R;
import com.commonfundmanager.utils.DbHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AccountInfoActivity extends Activity {
	
	DbHandler dbhandler=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountinfo_activity);
		
		try{
		
		dbhandler= new DbHandler(getApplicationContext());
		
		Double totalDeposit=dbhandler.getTotalDeposit();
		
		
		((TextView)findViewById(R.id.totaldeposit_value)).setText(totalDeposit.toString());
		
		Double totalExpense=dbhandler.getTotalExpense();
		Log.d("accountinfo","totalExpense: "+totalExpense);
		((TextView)findViewById(R.id.totalexpense_value)).setText(totalExpense.toString());
		
		Double balanceAmount=totalDeposit - totalExpense;
		((TextView)findViewById(R.id.account_balance_value)).setText(balanceAmount.toString());
		//int day=Calendar.DAY_OF_MONTH;
		
		int day=Integer.parseInt(android.text.format.DateFormat.format("dd", Calendar.getInstance().getTime()).toString());
	//	Toast.makeText(getApplicationContext(), "day"+day, Toast.LENGTH_SHORT).show();
		
		Double perDayExpense=totalExpense/day;
		((TextView)findViewById(R.id.dailyexpenditure_value)).setText(perDayExpense.toString());
		
		Double perHeadExpense=totalExpense/8;
		((TextView)findViewById(R.id.perhead_expenditure_value)).setText(perHeadExpense.toString());
		
		} catch (Exception e)
		
		{
			Log.d("accountinfoException","error: "+e.getMessage());
		}
	
	}
	
	
	public void ShowAllDeposits(View view) {
		// TODO Auto-generated method stub

		Intent myIntent = new Intent(AccountInfoActivity.this, AllDeposits.class);
		startActivity(myIntent);
		
	}
	public void ShowAllRedemptions(View view) {
		// TODO Auto-generated method stub

		Intent myIntent = new Intent(AccountInfoActivity.this, AllRedemptions.class);
		startActivity(myIntent);
		
	}
}
