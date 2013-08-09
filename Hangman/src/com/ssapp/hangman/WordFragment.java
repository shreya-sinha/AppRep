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
 * @author shreya
 *
 */
public class WordFragment extends Fragment {
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

	private String word;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.word_fragment, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(savedInstanceState!=null){
			return;
		}else{
			DBHelper info = new DBHelper(getActivity());

			DBHelper.setDatabase(info.getWritableDatabase());
			setWord(info.getData());
			info.close();

			Dialog d = new Dialog(getActivity());
			d.setTitle("Display Word");
			TextView tv = new TextView(getActivity());
			tv.setText(getWord());
			d.setContentView(tv);
			d.show();
		}
	}

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

	public boolean updateWordView(int id) {
		// TODO Auto-generated method stub
		String letter = "XXX"; // initialized to a value so that by default no
								// letter is shown
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
		if (getWord().contains(letter)) {
			LinearLayout linearLayout = (LinearLayout) getActivity()
					.findViewById(R.id.word_fragment_layout);
			Log.d("WordFragment",
					"child count is " + linearLayout.getChildCount());
			// int wordLength = getWord().length();

			for (int counter = 0; counter < linearLayout.getChildCount(); counter++) {
				int position = getWord().indexOf(letter, counter);
				if (position != -1) {
					Button b = (Button) linearLayout.getChildAt(position);
					if (b == null) {
						Log.d("WordFragment", "button null position "
								+ position);
					} else {
						b.setText(letter);
						Log.d("WordFragment", "button tesxt set at  "
								+ position);
					}
					counter = position ;
				} 
			}
		}else {
			Dialog letterAbsent = new Dialog(getActivity());
			letterAbsent.setTitle("Letter not found");
			TextView tv = new TextView(getActivity());
			tv.setText(letter + " absent");
			letterAbsent.setContentView(tv);
			letterAbsent.show();
			return false;

		}
		/*
		 * int counter = 0; while (counter < wordLength) { int position =
		 * getWord().indexOf(letter, counter); counter = position; Button
		 * b=(Button)linearLayout.getChildAt(position); if(b==null){
		 * Log.d("WordFragment", "button null position "+position); }else{
		 * b.setText(letter); } counter++; }
		 */
		return true;
	}

	private void setWord(String word) {
		this.word = word;

	}

	private String getWord() {
		return this.word;
	}
	

}
