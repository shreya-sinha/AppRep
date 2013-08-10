package com.ssapp.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author shreya
 * Displays all letters as buttons and handles click events.
 *
 */
public class LettersFragment extends Fragment {
	
	//private static final String TAG = "LettersFragment";
	
	/**
	 * Ensures that the parent activity implements 
	 * the OnLetterSelectedListener interface 
	 */
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		
		try{
			letterListener=(OnLetterSelectedListener)activity;
			}catch(ClassCastException e){
				throw new ClassCastException(activity.toString()
	                    + " must implement OnLetterSelectedListener");
			}
		}
	/**
	 * Inflates the letter fragment
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.letters_fragment,container, false);
	}
	
	OnLetterSelectedListener letterListener;
	/**
	 * Interface to handle onClick events
	 */
	public interface OnLetterSelectedListener{
		public void onLetterSelected(int id);
	}
	
}
