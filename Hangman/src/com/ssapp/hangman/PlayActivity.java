package com.ssapp.hangman;

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
	private final String WORD_FRAGMENT = "wordFragment";
	private final String LETTERS_FRAGMENT = "lettersFragment";
	private final String HANGMAN_FRAGMENT = "hangmanFragment";

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
			//fragTransaction.addToBackStack(null);
			fragTransaction.commit();

			LettersFragment lettersFragment = new LettersFragment();
			FragmentTransaction fragTransaction1 = getSupportFragmentManager()
					.beginTransaction();
			fragTransaction1.add(R.id.playscreen, lettersFragment,
					LETTERS_FRAGMENT);
			//fragTransaction1.addToBackStack(null);
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
			// If wordFragment is available, i.e., in two-pane layout...
			// Call a method in the WordFragment to update its content
			// returns true if word is updated (correct response)
			boolean wordUpdated = wordFragment.updateWordView(id);

			// call method to draw hangman in case of incorrect response
			Log.d("PlayActivity", "wordUpdated "+wordUpdated);
			if (!wordUpdated) {

				drawHangman();
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
		Log.d("PlayActivity", "Inside drawFragment()");
		
		// find hangmanFragment
		HangmanFragment hangmanFragment = (HangmanFragment) getSupportFragmentManager()
				.findFragmentByTag(HANGMAN_FRAGMENT);
		if (hangmanFragment != null) {
			Log.d("PlayActivity", "hangmanFragment ="+hangmanFragment);
		} else {

			// If hangmanFragment is not available,
			// must swap fragments

			// Create fragment and give it the previous state of hangman + 1
			HangmanFragment newFragment = new HangmanFragment();
			Bundle args = new Bundle();
			int state = newFragment.getState();
			state+=1;
			Log.d("PlayActivity", "state"+ state);
			args.putInt(HangmanFragment.STATE, state);
			newFragment.setArguments(args);
			
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			// Replace whatever is in the playscreen view with this fragment,
			// and add the transaction to the back stack so the user can navigate back
			transaction.add(R.id.playscreen, newFragment);
			// TODO create fragment
			//transaction.show(newFragment);
			transaction.addToBackStack(null);
			
			// Commit the transaction
			int success=transaction.commit();
            Log.d("PlayActivity","Transaction success" + success);
			
			
		}
	}
}
