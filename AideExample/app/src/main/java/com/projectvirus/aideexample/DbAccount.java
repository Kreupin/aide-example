package com.projectvirus.aideexample;
import android.database.sqlite.*;
import android.content.*;
import android.database.*;

public class DbAccount extends SQLiteOpenHelper
{
	public static final String DB_NAME="account.db";
	
	public DbAccount(Context ct)
	{
		super(ct,DB_NAME,null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE login(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int p2, int p3)
	{
		db.execSQL("DROP TABLE IF EXISTS login");
		onCreate(db);
	}
	
	public boolean Insert(String username,String password)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put("username",username);
		val.put("password",password);
		long result = db.insert("login",null,val);
		if(result == -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean CheckUsername(String username)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cs = db.rawQuery("SELECT * FROM login",null);
		if(cs.getCount() > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean CheckLogin(String username,String password)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cs = db.rawQuery("SELECT * FROM login",null);
		if(cs.getCount() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
