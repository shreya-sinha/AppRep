package com.ssapp.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		
		Button bPlay= (Button) getActivity().findViewById(R.id.play);
		Button bSettings= (Button) getActivity().findViewById(R.id.settings);
		Button bScores= (Button) getActivity().findViewById(R.id.scores);
		Button bHelp= (Button) getActivity().findViewById(R.id.help);
		Button bExit= (Button) getActivity().findViewById(R.id.exit);
		OnClickListener l = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				/*Fragment newFragment=null;
				FragmentTransaction transaction=getFragmentManager().beginTransaction();*/
				switch(arg0.getId()){
				case R.id.play:
					//newFragment=new PlayFragment();
					startActivity(new Intent("com.ssapp.hangman.PLAYACTIVITY"));
					break;
				case R.id.settings:
					//newFragment=new SettingsFragment();
					startActivity(new Intent("com.ssapp.hangman.SETTINGSACTIVITY"));
					break;
				case R.id.scores:
					//newFragment=new ScoresFragment();
					startActivity(new Intent("com.ssapp.hangman.SCORESACTIVITY"));
					break;
				case R.id.help:
					//newFragment=new HelpFragment();
					startActivity(new Intent("com.ssapp.hangman.HELPACTIVITY"));
					break;
				case R.id.exit:
					//TODO How to exit an App?
					//newFragment=new HelpFragment();
					
					break;
				default:
					Log.d("Menu SwitchCase", "reached default");
				}
				/*transaction.replace(R.id.menufragment, newFragment);
				transaction.addToBackStack(null);
				transaction.commit();
				 */				
				
			}
		};
		/*bPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stubFragment newFragment=null;
				Fragment newFragment=new PlayFragment();
				FragmentTransaction transaction=getFragmentManager().beginTransaction();
				transaction.replace(R.id.menufragment, newFragment);
				transaction.addToBackStack(null);
				transaction.commit();
				
			}
		});*/
	    bPlay.setOnClickListener(l);
		bSettings.setOnClickListener(l);
		bScores.setOnClickListener(l);
		bHelp.setOnClickListener(l);
		bExit.setOnClickListener(l);
	
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("MenuFragment", "OnCreate inside MenuFragment");
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//super();
		// TODO Auto-generated method stub
		Log.d("MenuFragment", "OnCreateView inside MenuFragment");
		return inflater.inflate(R.layout.menu_fragment,container, false);
	}
	

}
