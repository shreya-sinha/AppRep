package com.ssapp.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 
 * @author shreya 
 * The HangmanFragment displays the 
 * hangman graphic image which depicts the no. of turns played
 * 
 */
public class HangmanFragment extends Fragment {
	
	private static final String TAG="HangmanFragment"; 

	public final static String STATE = "hangman_state";

	Hangman h;
	DrawHangman drawHangmanObj;

	// Interface to plot the hangman image
	public interface DrawHangman {
		public void drawHangman();
	}

	/**
	 * Ensures that the parent activity implements the DrawHangman interface 
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "--------- onAttach ---------");

		try {
			drawHangmanObj = (DrawHangman) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnLetterSelectedListener");
		}
	}

	int imgResId;
	/**
	 * Check state and assign which image is to be displayed
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "--------- onCreate ---------");
		switch(PlayActivity.getState()){
		case 1:
			imgResId=R.layout.hangman_fragment;
			break;
		case 2:
			imgResId=R.layout.hangman_fragment;
			break;
		case 3:
			imgResId=R.layout.hangman_fragment;
			break;
		case 4:
			imgResId=R.layout.hangman_fragment;
			break;
		case 5:
			imgResId=R.layout.hangman_fragment;
			break;
		case 6:
			imgResId=R.layout.hangman_fragment;
			break;
		case 7:
			imgResId=R.layout.hangman_fragment;
			break;
		case 8:
			imgResId=R.layout.hangman_fragment;
			break;
		case 9:
			imgResId=R.layout.hangman_fragment;
			break;
		default:
			Log.d(TAG,"Invalid game state encountered. Cannot draw hangman");
		}
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d(TAG, "--------- onCreateView ---------");
		return inflater.inflate(imgResId, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.d(TAG, "--------- onViewCreated ---------");

		LinearLayout hangmanFragLayout = (LinearLayout) getView().findViewById(
				R.id.hangman_fragment);
		h = new Hangman(getActivity());
		hangmanFragLayout.addView(h);

	}

	@Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "--------- OnDestroy ---------");
    }
}
