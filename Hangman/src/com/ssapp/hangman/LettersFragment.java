package com.ssapp.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LettersFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.letters_fragment,container, false);
	}
	
	OnLetterSelectedListener letterListener;
	public interface OnLetterSelectedListener{
		public void onLetterSelected(int id);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		try{
			letterListener=(OnLetterSelectedListener)activity;
			}catch(ClassCastException e){
				throw new ClassCastException(activity.toString()
	                    + " must implement OnLetterSelectedListener");
			}
		}
	
}
