package com.ssapp.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.ssapp.hangman.HangmanFragment.DrawHangman;
import com.ssapp.hangman.LettersFragment.OnLetterSelectedListener;

/**
 * 
 * @author shreya The main play screen activity. Handles interaction between
 *         WordFragment, LettersFragment and HangmanFragment;
 * 
 */
public class PlayActivity extends FragmentActivity implements
		OnLetterSelectedListener, DrawHangman {
	private static final String TAG = "PlayActivity";
	private final String WORD_FRAGMENT = "wordFragment";
	private final String LETTERS_FRAGMENT = "lettersFragment";
	private final String HANGMAN_FRAGMENT = "hangmanFragment";
	private static int state;

	public static int getState() {
		return state;
	}

	public static void setState(int state) {
		PlayActivity.state = state;
		Log.d(TAG, "game state changed to " + state);
	}

	/**
	 * creates a linear layout and adds the two fragments- WordFragment and
	 * LettersFragment to the activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.playscreen);

		if (findViewById(R.id.playscreen) != null) {

			if (savedInstanceState != null) {
				return;
			}
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playscreen);
			linearLayout.setWeightSum(20);

			WordFragment wordFragment = new WordFragment();
			FragmentTransaction fragTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragTransaction.add(R.id.playscreen, wordFragment, WORD_FRAGMENT);
			// fragTransaction.addToBackStack(null);
			fragTransaction.commit();

			LettersFragment lettersFragment = new LettersFragment();
			FragmentTransaction fragTransaction1 = getSupportFragmentManager()
					.beginTransaction();
			fragTransaction1.add(R.id.playscreen, lettersFragment,
					LETTERS_FRAGMENT);
			// fragTransaction1.addToBackStack(null);
			fragTransaction1.commit();
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// set layout parameters to the two fragments
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 0);
		params.weight = 10;
		getSupportFragmentManager().findFragmentByTag(WORD_FRAGMENT).getView()
				.setLayoutParams(params);

		getSupportFragmentManager().findFragmentByTag(LETTERS_FRAGMENT)
				.getView().setLayoutParams(params);
	}

	/**
	 * method called when any letter in the LettersFragment is clicked
	 * 
	 * @param v
	 */
	public void letterClicked(View v) {
		Log.d("LettersFragment", "onClick called");
		// call method to check whether the other fragments need to be updated
		onLetterSelected(v.getId());
		// disable the letter that has been clicked once
		v.setClickable(false);
		v.setEnabled(false);

	}

	/**
	 * method overridden from LettersFragment method called to check whether to
	 * update WordFragment or HangmanFragment based on correct or incorrect
	 * response respectively
	 */

	@Override
	public void onLetterSelected(int id) {
		// find wordFragment
		WordFragment wordFragment = (WordFragment) getSupportFragmentManager()
				.findFragmentByTag(WORD_FRAGMENT);

		if (wordFragment != null) {
			/*
			 * If wordFragment is available, i.e., in two-pane layout... Call a
			 * method in the WordFragment to update its content
			 */

			int nextActionSwitch = wordFragment.updateWordView(id);

			Log.d(TAG, "nextActionSwitch " + nextActionSwitch);

			switch (nextActionSwitch) {
			case 0:
				break;
			case 1:
				drawHangman();
				break;
			case 2:
				setContentView(R.layout.activity_main);
				//setting up a splash screen
		        Thread splashGameOver=new Thread(){
		        	public void run(){
		        		try{
		        			int splashGameOver=0;
		        			while(splashGameOver<2000){
		        				sleep(100);
		        				splashGameOver+=100;
		        				
		        			}
		        			//starting the menu screen activity after splash timer ends 
		        			startActivity(new Intent("com.ssapp.hangman.SCORESACTIVITY"));
		        			
		        		}catch(InterruptedException ex){
		        			ex.printStackTrace();
		        		}finally{
		        			finish();
		        		}
		        	}
		        };
		        splashGameOver.start();
				break;
			case 3:
				
				break;
			default:
				Log.d(TAG, "Invalid value in nextActionSwitch");
			}

		} else {
			// if wordFragment is not available

		}

	}

	/**
	 * method overridden from HangmanFragment draws the hangman in case of
	 * incorrect response
	 */
	@Override
	public void drawHangman() {
		Log.d(TAG, "Inside drawFragment()");

		// find hangmanFragment
		HangmanFragment hangmanFragment = (HangmanFragment) getSupportFragmentManager()
				.findFragmentByTag(HANGMAN_FRAGMENT);
		if (hangmanFragment != null) {
			Log.d(TAG, "hangmanFragment =" + hangmanFragment);
		} else {

			HangmanFragment newFragment = new HangmanFragment();
			Bundle args = new Bundle();
			Log.d(TAG, "Creating new HangmanFragment");
			args.putInt(HangmanFragment.STATE, PlayActivity.getState());
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.add(R.id.playscreen, newFragment);
			// TODO create fragment
			// transaction.show(newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction

			int success = transaction.commit();
			Log.d(TAG, "Transaction success" + success);

		}
	}
}
