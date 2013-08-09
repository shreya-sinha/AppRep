package com.ssapp.hangman;

import java.util.ArrayList;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //setting up a splash screen
        Thread splashTimer=new Thread(){
        	public void run(){
        		try{
        			int splashTimer=0;
        			while(splashTimer<2000){
        				sleep(100);
        				splashTimer+=100;
        				
        			}
        			//starting the menu screen activity after splash timer ends 
        			startActivity(new Intent("com.ssapp.hangman.MAINMENU"));
        			
        		}catch(InterruptedException ex){
        			ex.printStackTrace();
        		}finally{
        			finish();
        		}
        	}
        };
        
        DBHelper dbHelper=new DBHelper(this);
        try{
        
        DBHelper.setDatabase(dbHelper.getWritableDatabase());
		
		 GameResources gameResources=new GameResources();
	       ArrayList<String> countries=(ArrayList<String>) gameResources.getCountriesArray();
	       for(String country:countries){
	    	   //add each entry in the list to the database with the hasBeenUsed parameter set to false as default
	    	   
	    	   dbHelper.createEntry(country, "false");
	       }
	       //dbHelper.close();
        }catch(SQLiteException sqe){
        	
        }finally{
        	dbHelper.close();
        }
        
        splashTimer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
