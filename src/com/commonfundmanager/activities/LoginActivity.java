package com.commonfundmanager.activities;




import com.commonfundmanager.R;
import com.commonfundmanager.beans.Login;
import com.commonfundmanager.utils.DbHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class LoginActivity extends Activity implements OnClickListener {
	
	
	DbHandler dbHandler = null;
	SharedPreferences pref;
	Editor editor;
	String usernameValue;
	String passwordValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		Log.d("nnnqlist","qlist size"); //"login","loginactivity entrance");
		
		dbHandler = new DbHandler(getApplicationContext());
		
		dbHandler.addLogin(new Login("683258", "password","admin"));
//		dbHandler.addLogin(new Login("683229", "password","minister"));
		dbHandler.addLogin(new Login("683268", "password","member"));
//		dbHandler.addLogin(new Login("683258", "password","admin"));
//		dbHandler.addLogin(new Login("683258", "password","admin"));
//		dbHandler.addLogin(new Login("683258", "password","admin"));
//		dbHandler.addLogin(new Login("683258", "password","admin"));
		
		pref= getApplicationContext().getSharedPreferences("My Pref", 0);
		editor=pref.edit();
		
		
		
		
		findViewById(R.id.login).setOnClickListener(new OnClickListener() {
			EditText username= (EditText) findViewById(R.id.username);
			EditText password= (EditText) findViewById(R.id.password);
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				editor.remove("username");
				editor.remove("userrole");
				editor.commit();
				
				if ( (username.getText().toString().trim().length() > 0)
						&&(password.getText().toString().trim().length() > 0)) {
					
					// new category name
					usernameValue = username.getText().toString();
					passwordValue = password.getText().toString();
					Login login= new Login(usernameValue, passwordValue);
					Intent myIntent;
					String dbRole= dbHandler.checkLogin(login);
					Log.d("login","role: "+dbRole);
					if(dbRole==null)
				//if(dbRole.equals(null))
					{
						Toast.makeText(getApplicationContext(),
								"User not found in db.", Toast.LENGTH_SHORT)
								.show();
					}
					else 
					{
						
					if(dbRole.equals("member"))
					{
						editor.putString("username", usernameValue);
						editor.putString("userrole", "member");
						editor.commit();
						myIntent = new Intent(LoginActivity.this, MemberHomeActivity.class);
						startActivity(myIntent);
						
					}
					
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Invalid username/password", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
