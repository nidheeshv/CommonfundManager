package com.commonfundmanager.activities;

import java.util.ArrayList;

import com.commonfundmanager.R;
import com.commonfundmanager.beans.Transaction;
import com.commonfundmanager.utils.DbHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyRequestsActivity extends Activity implements OnClickListener {
	DbHandler dbHandler;
	String username;
	SharedPreferences pref;
	WebView mwebview;
	TableLayout myRequestTable;
	Button deleteButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myrequests);
		dbHandler = new DbHandler(getApplicationContext());
	try{	
	
	pref= getApplicationContext().getSharedPreferences("My Pref", 0);
	username=pref.getString("username", null);
	myRequestTable = (TableLayout) findViewById(R.id.myrequest_table);
	
	ArrayList<Transaction> transactionList=dbHandler.getMyTransactions(username);
	Log.d("myTransactions","transactionList size: "+transactionList.size());
	
	
	
	
	for(Transaction t: transactionList)
	{
		Log.d("myTransactions","loop size: "+t.getDescription());
		
		  TableRow row= new TableRow(this);
	        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
	        row.setLayoutParams(lp);
	        
	        TextView tidText=new TextView(this);
	        tidText.setText(""+t.getTid());
	        tidText.setPadding(20,20,20,20);
	        tidText.setBackgroundResource(R.drawable.request_table_border);
	        
	       
	        TextView tUserText=new TextView(this);
	        tUserText.setText(t.getUser());
	        tUserText.setPadding(20,20,20,20);
	        tUserText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tDateText=new TextView(this);
	        tDateText.setText(t.gettDate());
	        tDateText.setPadding(20,20,20,20);
	        tDateText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tTypeText=new TextView(this);
	        tTypeText.setText(t.getTransactionType());
	        tTypeText.setPadding(20,20,20,20);
	        tTypeText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tCategoryText=new TextView(this);
	        tCategoryText.setText(t.getCategory());
	        tCategoryText.setPadding(20,20,20,20);
	        tCategoryText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tDescriptionText=new TextView(this);
	        tDescriptionText.setText(t.getDescription());
	        tDescriptionText.setPadding(20,20,20,20);
	        tDescriptionText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tAmountText=new TextView(this);
	        tAmountText.setText(Double.toString(t.getAmount()));
	        tAmountText.setPadding(20,20,20,20);
	        tAmountText.setBackgroundResource(R.drawable.request_table_border);
	        
	        
	        Button editButton=new Button(this);
	        editButton.setText("Edit");
	        editButton.setPadding(10,10,10,10);
	        editButton.setId(t.getTid());
	        editButton.setBackgroundResource(R.drawable.orange_button);
	        editButton.setOnClickListener(this);
	        
	        
	        deleteButton=new Button(this);
	        deleteButton.setText("Delete");
	        deleteButton.setId(t.getTid());
	        deleteButton.setPadding(10,10,10,10);
	        deleteButton.setBackgroundResource(R.drawable.orange_button);
	        deleteButton.setOnClickListener(this);
	        
	        row.addView(tidText);
	        row.addView(tUserText);
	        row.addView(tDateText);
	        row.addView(tTypeText);
	        row.addView(tCategoryText);
	        row.addView(tDescriptionText);
	        row.addView(tAmountText);
	        row.addView(editButton);
		    row.addView(deleteButton);
	        myRequestTable.addView(row,0);
	    
	}
	init();
//	
	
//	String myTable = "<table border=1>" +
//			"<th>Transaction Id</th><th>Date</th><th>Type</th><th>Category</th><th>Description</th><th>Amount</th><th>Current Status</th>";
//
//			
//	for(Transaction t: transactionList)
//	{
//		myTable+="<tr><td>"+t.getTid()+
//				"</td><td>"+t.gettDate()+
//				"</td><td>"+t.getTransactionType()+
//				"</td><td>"+t.getCategory()+
//				"</td><td>"+t.getDescription()+
//				"</td><td>"+t.getAmount()+
//				"</td><td>"+t.getApprovedStatus()+
//				"</td><tr>";
//	}
//	
//	myTable+="</table>";
//	Log.d("myTransactions","myTable : "+myTable);
//	mwebview = (WebView) findViewById(R.id.myrequesttable);
//	
//	//mwebview.loadDataWithBaseURL(null, myTable, "text/html", "utf-8", null);
//	mwebview.loadData(myTable, "text/html", "utf-8");
//	 WebSettings webSettings = mwebview.getSettings();
//	  webSettings.setJavaScriptEnabled(true);
//	  webSettings.setBuiltInZoomControls(true);
//
//	  mwebview.setWebViewClient(new WebViewClient());
	} catch (Exception e){
		Log.d("myTransactions","error : "+e.getMessage());
	}
	
	
	
	
	}
	
	
	public void init(){
//	    TableLayout ll = (TableLayout) findViewById(R.id.myrequest_table);
//	    ll.setBackgroundResource(R.drawable.row_border2);

	        TableRow row= new TableRow(this);
	        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
	        row.setLayoutParams(lp);
	        
	        TextView tidText=new TextView(this);
	        tidText.setText("Transaction Id");
	        tidText.setPadding(20,20,20,20);
	        tidText.setBackgroundResource(R.drawable.request_table_border);
	        tidText.setId(R.id.tidtext);
	        
	        TextView tUserText=new TextView(this);
	        tUserText.setText("Member");
	        tUserText.setPadding(20,20,20,20);
	        tUserText.setBackgroundResource(R.drawable.request_table_border);
	        tUserText.setId(R.id.tidtext);
	       
	        TextView tDateText=new TextView(this);
	        tDateText.setText("Date");
	        tDateText.setPadding(20,20,20,20);
	        tDateText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tTypeText=new TextView(this);
	        tTypeText.setText("Type");
	        tTypeText.setPadding(20,20,20,20);
	        tTypeText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tCategoryText=new TextView(this);
	        tCategoryText.setText("Category");
	        tCategoryText.setPadding(20,20,20,20);
	        tCategoryText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tDescriptionText=new TextView(this);
	        tDescriptionText.setText("Description");
	        tDescriptionText.setPadding(20,20,20,20);
	        tDescriptionText.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView tAmountText=new TextView(this);
	        tAmountText.setText("Amount");
	        tAmountText.setPadding(20,20,20,20);
	        tAmountText.setBackgroundResource(R.drawable.request_table_border);
	        
	        
	        TextView editButton=new TextView(this);
	        editButton.setText("Edit");
	        editButton.setPadding(20,20,20,20);
	        editButton.setBackgroundResource(R.drawable.request_table_border);
	        
	        TextView deleteButton=new TextView(this);
	        deleteButton.setText("Delete");
	        deleteButton.setPadding(20,20,20,20);
	        deleteButton.setBackgroundResource(R.drawable.request_table_border);
	        
	        
	        row.addView(tidText);
	        row.addView(tUserText);
	        row.addView(tDateText);
	        row.addView(tTypeText);
	        row.addView(tCategoryText);
	        row.addView(tDescriptionText);
	        row.addView(tAmountText);
	        row.addView(editButton);
	        row.addView(deleteButton);
	        myRequestTable.addView(row,0);
	        
	}
	


	@Override
	public void onClick(View view) {
		try{
			
		
		if(((Button)view).getText().equals("Delete"))
		{
			final int tid=((Button)view).getId();
			
			new AlertDialog.Builder(this)
	           .setMessage("Are you sure you want to delete?")
	           .setCancelable(false)
	           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   
	            	   
	            	   dbHandler.deleteMyRequest(tid,username);
	            	   
	                    MyRequestsActivity.this.finish();
	               }
	           })
	           .setNegativeButton("No", null)
	           .show();
		}
		if(((Button)view).getText().equals("Edit"))
		{
			final int tid=((Button)view).getId();
			Intent myIntent = new Intent(MyRequestsActivity.this, UpdateClaimActivity.class);
			myIntent.putExtra("tid", tid);
			startActivity(myIntent);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			Log.d("click listener","error : "+ e.getMessage() );
		}
		// TODO Auto-generated method stub
		TableRow tr = (TableRow) view.getParent();
        //TextView tid = (TextView) tr.findViewById(R.id.tidtext);
          //              String tidText = tid.getText().toString();   
        
      Log.d("click listener","tid : "+ ((Button)view).getId());
	}
}
