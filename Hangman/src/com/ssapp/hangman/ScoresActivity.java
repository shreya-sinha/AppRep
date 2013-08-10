package com.ssapp.hangman;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ScoresActivity extends FragmentActivity{
	
	private static String TAG="FragmentActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		setContentView(R.layout.activity_main);
		Log.d(TAG,"-------onCreate ------");
		
	}
}
