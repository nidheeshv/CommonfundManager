package com.commonfundmanager.activities;

	import java.util.ArrayList;

import com.commonfundmanager.R;
	import com.commonfundmanager.beans.Transaction;
import com.commonfundmanager.utils.DbHandler;

	import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AllDeposits extends Activity {

		DbHandler dbHandler;
		WebView depositwebview;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.alldeposits);
		
		try{	
		dbHandler = new DbHandler(getApplicationContext());
			
		ArrayList<Transaction> transactionList=dbHandler.getAllDeposits();
		Log.d("myTransactions","transactionList size: "+transactionList.size());
		
		
		String myTable = "<table border=1>" +
				"<th>Transaction Id</th><th>Date</th><th>User</th><th>Category</th><th>Description</th><th>Amount</th>";

				
		for(Transaction t: transactionList)
		{
			myTable+="<tr><td>"+t.getTid()+
					"</td><td>"+t.gettDate()+
					"</td><td>"+t.getUser()+
					"</td><td>"+t.getCategory()+
					"</td><td>"+t.getDescription()+
					"</td><td>"+t.getAmount()+
					"</td><tr>";
		}
		
		myTable+="</table>";
		Log.d("All deposits","myTable : "+myTable);
		depositwebview = (WebView) findViewById(R.id.deposits_info_table);
		Log.d("All deposits 2","myTable : "+myTable);
		//depositwebview.loadDataWithBaseURL(null, myTable, "text/html", "utf-8", null);
		depositwebview.loadData(myTable, "text/html", "utf-8");
		// WebSettings webSettings = mwebview.getSettings();
		//  webSettings.setJavaScriptEnabled(true);
		//  webSettings.setBuiltInZoomControls(true);

		//  mwebview.setWebViewClient(new WebViewClient());
		} catch (Exception e){
			Log.d("All deposits","error : "+e.getMessage()+e.getStackTrace()+e.getCause());
		}
		}
		
		
	}

