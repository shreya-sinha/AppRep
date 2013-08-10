package com.ssapp.hangman;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author shreya Displays the blanks for the word that the user needs to guess
 *         and handles all modifications to it.
 * 
 */
public class WordFragment extends Fragment {

	private static final String TAG = "WordFragment";

	private String word;

	private void setWord(String word) {
		this.word = word;

	}

	private String getWord() {
		return this.word;
	}

	/**
	 * Initialize a game turn by fetching a word from the database
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			return;
		} else {
			DBHelper info = new DBHelper(getActivity());

			DBHelper.setDatabase(info.getWritableDatabase());
			setWord(info.getData());
			PlayActivity.setState(0);
			info.close();
			Log.d(TAG, "Initialised word from database");

			// TODO temporary dialog - only for dubugging , to be removed
			Dialog d = new Dialog(getActivity());
			d.setTitle("Display Word");
			TextView tv = new TextView(getActivity());
			tv.setText(getWord());
			d.setContentView(tv);
			d.show();
		}
	}

	/**
	 * inflates the word fragment
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.word_fragment, container, false);
	}

	/**
	 * Creates the linear layout in word fragment. Displays buttons for each
	 * letter in the word.
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		LinearLayout linearLayout = (LinearLayout) getActivity().findViewById(
				R.id.word_fragment_layout);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);

		for (int x = 0; x < getWord().length(); x++) {
			Button b = new Button(getActivity());
			b.setBackgroundColor(Color.WHITE);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					30, 30);
			params.setMargins(10, 10, 0, 0);
			b.setLayoutParams(params);

			linearLayout.addView(b);
		}

	}

	/**
	 * invoked when a letter is clicked in the letters fragment Checks if the
	 * letter clicked is present in the word. If yes the letter is shown at its
	 * position in the word.
	 * 
	 * @param id
	 *            - ID for the button that was clicked
	 * @return 0 if the view was updated
	 * 		   1 if the view was not updated
	 * 		   2 if 10 chances are consumed and game ends
	 */
	public int updateWordView(int id) {
		final int WORD_UPDATED=0;
		final int WORD_NOT_UPDATED=1;
		final int GAME_END=2;
		
		String letter = getLetter(id);
		
		if (getWord().contains(letter)) {
			LinearLayout linearLayout = (LinearLayout) getActivity()
					.findViewById(R.id.word_fragment_layout);
			Log.d(TAG, "child count is " + linearLayout.getChildCount());
	
			for (int counter = 0; counter < linearLayout.getChildCount(); counter++) {
				int position = getWord().indexOf(letter, counter);
				if (position != -1) {
					Button b = (Button) linearLayout.getChildAt(position);
					if (b == null) {
						Log.d(TAG, "button null position " + position);
					} else {
						b.setText(letter);
						Log.d(TAG, "button text set at  " + position);
					}
					counter = position;
				}
			}
		} else {
			// TODO temporary dialog - for debugging, to be removed
			Dialog letterAbsent = new Dialog(getActivity());
			letterAbsent.setTitle("Letter not found");
			TextView tv = new TextView(getActivity());
			tv.setText(letter + " absent");
			letterAbsent.setContentView(tv);
			letterAbsent.show();

			PlayActivity.setState(PlayActivity.getState()+1);
			if(PlayActivity.getState()==10){
				//TODO end game/Show score
				return GAME_END;
				
			}
			return WORD_NOT_UPDATED;

		}
		
		return WORD_UPDATED;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "OnDestroy");
	}

	/**
	 * Returns the String letter for an integer id
	 * @param id
	 * @return letter
	 */
	private String getLetter(int id) {
		String letter = "XXX"; // initialized to a value so that by default no letter is shown
		switch (id) {
		case R.id.a:
			letter = "A";
			break;
		case R.id.b:
			letter = "B";
			break;
		case R.id.c:
			letter = "C";
			break;
		case R.id.d:
			letter = "D";
			break;
		case R.id.e:
			letter = "E";
			break;
		case R.id.f:
			letter = "F";
			break;
		case R.id.g:
			letter = "G";
			break;
		case R.id.h:
			letter = "H";
			break;
		case R.id.i:
			letter = "I";
			break;
		case R.id.j:
			letter = "J";
			break;
		case R.id.k:
			letter = "K";
			break;
		case R.id.l:
			letter = "L";
			break;
		case R.id.m:
			letter = "M";
			break;
		case R.id.n:
			letter = "N";
			break;
		case R.id.o:
			letter = "O";
			break;
		case R.id.p:
			letter = "P";
			break;
		case R.id.q:
			letter = "Q";
			break;
		case R.id.r:
			letter = "R";
			break;
		case R.id.s:
			letter = "S";
			break;
		case R.id.t:
			letter = "T";
			break;
		case R.id.u:
			letter = "U";
			break;
		case R.id.v:
			letter = "V";
			break;
		case R.id.w:
			letter = "W";
			break;
		case R.id.x:
			letter = "X";
			break;
		case R.id.y:
			letter = "Y";
			break;
		case R.id.z:
			letter = "Z";
			break;

		}

		return letter;
	}
}
