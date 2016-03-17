package com.commonfundmanager.utils;

import java.util.ArrayList;
import java.util.Date;

import com.commonfundmanager.beans.Login;
import com.commonfundmanager.beans.Transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {

	public static final int DbVersion=1;
	public static final String DataBaseName="CommonFundManager";
	public static final String TableName1="Transactions";
	public static final String TableName2="users";
	
	
	public static final String col1= "tid";
	public static final String col2="user";
	public static final String col3="tdate";
	public static final String col4="transactiontype";
	public static final String col5="transactioncategory";
	public static final String col6="description";
	public static final String col7="amount";
//	public static final String col8="approvedstatus";
//	public static final String col9="approver";
	
	public DbHandler(Context context) {
		super(context, DataBaseName, null, DbVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String createTableQuery="CREATE TABLE "+ TableName1 +" ( "+
		col1+" INTEGER PRIMARY KEY , "+
		col2+" TEXT, "+
		col3+" TEXT, "+
		col4+" TEXT, "+
		col5+" TEXT, "+
		col6+" TEXT, "+
		col7+" INTEGER  )";
		Log.d("dbget","create table: "+createTableQuery);
		db.execSQL(createTableQuery);
		
		String createUserTable= "CREATE TABLE " +TableName2+" ( username text, password text, role text)";
		Log.d("dbget","create table: "+createUserTable);
		db.execSQL(createUserTable);
		
		//addQuestion(new Question(12, "questionTest2", "CHOICE2"));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS"+TableName1);
		db.execSQL("DROP TABLE IF EXISTS " +TableName2);
	}

	public boolean addTransaction(Transaction transaction)
	{
	
		try{
			
		
		SQLiteDatabase db= getWritableDatabase();
		Log.d("dbget","to add Transaction: "+transaction.getDescription());
		ContentValues cn= new ContentValues();
		//cn.put(col1, 1);
		cn.put(col2,transaction.getUser() );
		cn.put(col3, transaction.gettDate());
		cn.put(col4,transaction.getTransactionType() );
		cn.put(col5, transaction.getCategory());
		cn.put(col6, transaction.getDescription());
		cn.put(col7, transaction.getAmount());
		db.insert(TableName1, null, cn);
		Log.d("dbget","transaction insertd: "+transaction.getUser()+transaction.getAmount());
		return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	

		public boolean updateTransaction(Transaction transaction)
		{
		
			try{
				
			
			SQLiteDatabase db= getWritableDatabase();
			Log.d("dbget","to add Transaction: "+transaction.getDescription());
			ContentValues cn= new ContentValues();
			//cn.put(col1, 1);
			cn.put(col2,transaction.getUser() );
			cn.put(col3, transaction.gettDate());
			cn.put(col4,transaction.getTransactionType() );
			cn.put(col5, transaction.getCategory());
			cn.put(col6, transaction.getDescription());
			cn.put(col7, transaction.getAmount());
			
			db.update(TableName1, cn,"tid= ?", new String[]{ String.valueOf(transaction.getTid())});
			Log.d("dbget","transaction updated: "+transaction.getUser()+transaction.getAmount());
			return true;
			}
			catch (Exception e)
			{
				return false;
			}
		}
		
	   
	   
	   
	public String checkLogin(Login login)
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName2;
		
		Cursor cr= db.rawQuery(selQuery, null);
		//Question q=null;
		Login dbLogin= null;
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
			do{
				dbLogin=new Login(cr.getString(0), cr.getString(1), cr.getString(2));
				Log.d("dbget","login: "+dbLogin.getUsername());
				if(login.getUsername().equals(dbLogin.getUsername())&&login.getPassword().equals(dbLogin.getPassword()))
				{
					return dbLogin.getRole();
				}
			}while(cr.moveToNext());
		}
		return null;
	}
	
	public double getTotalDeposit()
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT SUM(amount) FROM "+TableName1 +" where transactiontype='Credit'";
		
		Cursor cr= db.rawQuery(selQuery, null);
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
			do{
				Log.d("dbget","Total deposit: "+Double.valueOf(cr.getString(0)));
				return Double.valueOf(cr.getString(0));
				
			}while(cr.moveToNext());
		}
		
		return 0;
	}
	public double getTotalExpense()
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT SUM(amount) FROM "+TableName1 +" where transactiontype='Debit'";
		
		Cursor cr= db.rawQuery(selQuery, null);
		if(cr!=null)
		{
			Log.d("dbget","cursor: "+cr.getCount());
			cr.moveToFirst();
			do{
				Log.d("dbget","Total expense: "+Double.valueOf(cr.getString(0)));
				return Double.parseDouble(cr.getString(0).trim());
				//return 10.0;
			}while(cr.moveToNext());
		}
		
		return 0;
	}
	
	
	public ArrayList<Transaction> getMyTransactions(String username)
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1 +" ORDER BY tid DESC";
		
		Cursor cr= db.rawQuery(selQuery, null);
		if(cr!=null)
		{
			ArrayList<Transaction> transactionlist=new ArrayList<Transaction>();
			Log.d("dbget","cursor: "+cr.getCount());
			Transaction transaction=null;
			cr.moveToFirst();
			do{
				Log.d("dbget","Total expense: "+Double.valueOf(cr.getString(0)));
				
				int tid= Integer.parseInt(cr.getString(0));
				String user=cr.getString(1);
				String date=cr.getString(2);
				String transactionType=cr.getString(3);
				String category=cr.getString(4);
				String description=cr.getString(5);
				Double amount=Double.parseDouble(cr.getString(6).trim());
				//String approvedstatus=cr.getString(7);
				transaction = new Transaction(tid, user,date, transactionType, category, description,amount);
				transactionlist.add(transaction);
				//return Double.parseDouble(cr.getString(0).trim());
				//return 10.0;
			}while(cr.moveToNext());
			return transactionlist;
		}
		
		return null;
	}
	
	public Transaction getTransaction(int trnid )
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1 +" where tid="+trnid;
		
		Cursor cr= db.rawQuery(selQuery, null);
		if(cr!=null)
		{
			//ArrayList<Transaction> transactionlist=new ArrayList<Transaction>();
			Log.d("dbget","cursor: "+cr.getCount());
			Transaction transaction=null;
			cr.moveToFirst();
			do{
				Log.d("dbget","Total expense: "+Double.valueOf(cr.getString(0)));
				
				int tid= Integer.parseInt(cr.getString(0));
				String user=cr.getString(1);
				String date=cr.getString(2);
				String transactionType=cr.getString(3);
				String category=cr.getString(4);
				String description=cr.getString(5);
				Double amount=Double.parseDouble(cr.getString(6).trim());
				//String approvedstatus=cr.getString(7);
				transaction = new Transaction(tid, user, date, transactionType, category, description, amount);
				//transactionlist.add(transaction);
				//return Double.parseDouble(cr.getString(0).trim());
				//return 10.0;
			}while(cr.moveToNext());
			return transaction;
		}
		
		return null;
	}
	
	public ArrayList<Transaction> getAllDeposits()
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1 +" where transactiontype='Credit'";

		
		Cursor cr= db.rawQuery(selQuery, null);
		if(cr!=null)
		{
			ArrayList<Transaction> transactionlist=new ArrayList<Transaction>();
			Log.d("dbget","cursor: "+cr.getCount());
			Transaction transaction=null;
			cr.moveToFirst();
			do{
				Log.d("dbget","Total expense: "+Double.valueOf(cr.getString(0)));
				
				int tid= Integer.parseInt(cr.getString(0));
				String user=cr.getString(1);
				String date=cr.getString(2);
				String transactionType=cr.getString(3);
				String category=cr.getString(4);
				String description=cr.getString(5);
				Double amount=Double.parseDouble(cr.getString(6).trim());
				//String approvedstatus="cr.getString(7)";
				transaction = new Transaction(tid, user, date, transactionType, category, description,  amount);
				//transaction.setUser(user);
				transactionlist.add(transaction);
				//return Double.parseDouble(cr.getString(0).trim());
				//return 10.0;
			}while(cr.moveToNext());
			return transactionlist;
		}
		
		return null;
	}
	
	

	public ArrayList<Transaction> getAllRedemptions()
	{
		SQLiteDatabase db= getReadableDatabase();
		String selQuery= "SELECT * FROM "+TableName1 +" where transactiontype='Debit'";

		
		Cursor cr= db.rawQuery(selQuery, null);
		if(cr!=null)
		{
			ArrayList<Transaction> transactionlist=new ArrayList<Transaction>();
			Log.d("dbget","cursor: "+cr.getCount());
			Transaction transaction=null;
			cr.moveToFirst();
			do{
				//Log.d("dbget","Total expense: "+Double.valueOf(cr.getString(0)));
				
				int tid= Integer.parseInt(cr.getString(0));
				String user=cr.getString(1);
				String date=cr.getString(2);
				String transactionType=cr.getString(3);
				String category=cr.getString(4);
				String description=cr.getString(5);
				Double amount=Double.parseDouble(cr.getString(6).trim());
				transaction = new Transaction(tid, user, date, transactionType, category, description,  amount);
				transactionlist.add(transaction);
				//return Double.parseDouble(cr.getString(0).trim());
				//return 10.0;
			}while(cr.moveToNext());
			return transactionlist;
		}
		
		return null;
	}
	
	
	
	public void deleteMyRequest(int tid, String username) {
		//SQLiteDatabase db = this.getWritableDatabase();
		 
		 
		 SQLiteDatabase db = this.getWritableDatabase();
		    try
		    {
		    	db.delete(TableName1, " tid =?", new String[] { String.valueOf(tid    ) });
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		    finally
		    {
		        db.close();
		    }
		 
		
	}
	
	public void addLogin(Login login) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("username", login.getUsername().trim()); // Contact Name
		values.put("password", login.getPassword()); // Contact Phone
		values.put("role", login.getRole()); 
		// Inserting Row
		Log.d("dbadd","added "+login.getUsername());
		db.insert(TableName2, null, values);
		db.close(); // Closing database connection
	}
	
	
}
