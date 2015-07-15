package com.book.pong_v1;

import com.book.simplegameengine_v1.SGActivity;
import com.book.simplegameengine_v1.SGPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GameActivity extends SGActivity 
		implements GestureDetector.OnGestureListener{
	
	public static final String TAG = "PongV1";
	private GameView mView;
	
	private GestureDetector mGestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		enableFullScreen();
		enableKeepScreenOn();
		
		SGPreferences preferences = getPreferences();
		
		mGestureDetector = new GestureDetector(this, this);
		
		if(preferences.getInt("first_time", -1) == -1){
			preferences.begin()
					.putInt("first_time", 1)
					.putInt("difficulty", 0)
					.putInt("high_score", 0)
					.end();
			Log.d(TAG,  "Primeira inicialização.");
		}
		
		mView = new GameView(this);
		setContentView(mView);
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(preferences.getInt("difficulty", 0));
		Log.d(TAG, stringBuilder.toString());
		stringBuilder.setLength(0);
		stringBuilder.append("High score: ");
		stringBuilder.append(preferences.getInt("high_score", 0));
		Log.d(TAG, stringBuilder.toString());
				
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	
	@Override
	public boolean onDown(MotionEvent event) {
		Log.d(TAG, "onDown() chamado."); 
		return true;
	}
	
	@Override
	public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent2, float velocityX, float velocityY) {
		Log.d(TAG,  "onFling() chamado.");
		return true;
	}
	
	@Override
	public void onLongPress(MotionEvent evemt) {
		Log.d(TAG, "OnLongPress() chamado."); 
	}
	
	@Override
	public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX,
			float distanceY) {
		mView.movePlayer(-distanceX,  -distanceY);
		Log.d(TAG,  "onScroll() chmado.");
		return true;
	}
	
	@Override
	public void onShowPress(MotionEvent event) {
		Log.d(TAG,  "onShowPress() chamado.");
	}
	
	public boolean onSingleTapUp(MotionEvent event) {
		Log.d(TAG,  "onSingleTapUp() chamado.");
		return true;
	}
	
	
}
