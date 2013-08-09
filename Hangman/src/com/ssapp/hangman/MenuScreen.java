package com.ssapp.hangman;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class MenuScreen extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.menu);
		Log.d("MenuScreen", "set content view for menu screen");
	}
	
	

}
