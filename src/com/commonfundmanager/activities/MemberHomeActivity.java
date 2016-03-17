package com.commonfundmanager.activities;

import com.commonfundmanager.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MemberHomeActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userhome_activity);
	
		
		findViewById(R.id.accountinfo).setOnClickListener(this);
		findViewById(R.id.requestclaim).setOnClickListener(this);
		findViewById(R.id.myrequests).setOnClickListener(this);
		//findViewById(R.id.about).setOnClickListener(this);
		//findViewById(R.id.sync).setOnClickListener(this);
		
//		findViewById(R.id.username).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}

	@Override
	public void onClick(View view) {
		int elementId=view.getId();
		Intent myIntent;
		switch(elementId)
		{
			case R.id.accountinfo:
				//Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
				myIntent = new Intent(MemberHomeActivity.this, AccountInfoActivity.class);
				startActivity(myIntent);
				break;
			case R.id.requestclaim:
				myIntent = new Intent(MemberHomeActivity.this, ClaimActivity.class);
				startActivity(myIntent);
				
				break;
			case R.id.myrequests:
				myIntent = new Intent(MemberHomeActivity.this, MyRequestsActivity.class);
				startActivity(myIntent);
				//Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
				break;
//			case R.id.about:
//				Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.sync:
				//Toast.makeText(getApplicationContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
			//	int localMaxId=dbHandler.getMaxId();
				//new SyncQuestns().execute(String.valueOf(localMaxId));
//				break;
		}
		
	}
	

}
