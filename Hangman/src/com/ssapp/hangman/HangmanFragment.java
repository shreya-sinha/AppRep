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
 * 
 */
public class HangmanFragment extends Fragment {

	private int state;
	public final static String STATE = "hangman_state";

	Hangman h;
	DrawHangman drawHangmanObj;

	public interface DrawHangman {
		public void drawHangman();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("HangmanFragment", "---------> onAttach");

		try {
			drawHangmanObj = (DrawHangman) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnLetterSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("HangmanFragment", "---------> onCreate");

		if (savedInstanceState == null) {
            Log.d("HangmanFragment", "savedInstanceState == null");

            this.setState(1);
		} else {
            Log.d("HangmanFragment", "savedInstanceState != null");

            this.setState(savedInstanceState.getInt(STATE));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("HangmanFragment", "---------> onCreateView");
		return inflater.inflate(R.layout.hangman_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.d("HangmanFragment", "---------> onViewCreated");

		LinearLayout hangmanFragLayout = (LinearLayout) getView().findViewById(
				R.id.hangman_fragment);
		h = new Hangman(getActivity());
		hangmanFragLayout.addView(h);

	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d("HangmanFragment", "---------> onStart");

		Log.d("HangmanFragment", "State = " + this.state);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("HangmanFragment", "---------> onSaveInstanceState");

		outState.putInt(STATE, this.getState());
	}

	public void setState(int state) {
		Log.d("HangmanFragment", "setState");
		this.state = state;

	}

	public int getState() {
		Log.d("HangmanFragment", "getState");
		
		return this.state;
	}
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("HangmanFragment", "OnDestroy");
    }
}
