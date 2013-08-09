package com.ssapp.hangman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class Hangman extends View {

	Bitmap bMap;

	public Hangman(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bMap = BitmapFactory.decodeResource(getResources(), R.drawable.hangman);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.LTGRAY);

		canvas.drawBitmap(bMap, 0, 0, null);
	}

}
