package com.ssapp.hangman;

import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static String KEY_ROWID = "_id";
	private static String KEY_COUNTRY_NAME = "country_name";
	private static String KEY_USED = "used";

	private static final String DATABASE_NAME = "game_resources";
	private static final String TABLE_COUNTRIES = "countries";
	private static final int DATABASE_VERSION = 1;
	//private static final String DB_PATH = "/data/data/com.ssapp.hangman/databases";

	//private static DBHelper myHelper;
	//private static Context myContext;
	private static SQLiteDatabase myDatabase;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//myContext=context;
		
	}

	public static void setDatabase(SQLiteDatabase db){
		myDatabase=db;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE " + TABLE_COUNTRIES + " (" + KEY_ROWID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COUNTRY_NAME
				+ " TEXT NOT NULL," + KEY_USED + " TEXT NOT NULL);");
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
		onCreate(db);
	}

	/*public DBHelper open(){
		myHelper=new DBHelper(myContext);
		myDatabase=myHelper.getWritableDatabase();
		return this;
	}*/
	/*public void close(){
		myHelper.close();
	}*/
	public String getData() {
		
		String[] columns=new String[]{KEY_ROWID,KEY_COUNTRY_NAME,KEY_USED};
		Cursor c=myDatabase.query(TABLE_COUNTRIES, columns, null, null,null,null,null);
		String result="";
		
		//int iRow=c.getColumnIndex(KEY_ROWID);
		int iName=c.getColumnIndex(KEY_COUNTRY_NAME);
		//int iUsed=c.getColumnIndex(KEY_USED);
		
		int n=c.getCount();
		Random r=new Random();
		int randomValue=r.nextInt(n);
		
		c.moveToPosition(randomValue);  
		
		result=c.getString(iName);
		c.close();
		
		return result;
	}
	public long createEntry(String country,String hasBeenUsed){
		
		ContentValues cv=new ContentValues();
		cv.put(KEY_COUNTRY_NAME, country);
		cv.put(KEY_USED, hasBeenUsed);
		
		return myDatabase.insert(TABLE_COUNTRIES, null,cv);
		
	}
    
}
