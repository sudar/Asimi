package com.sudarmuthu.android.sumi.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Code based on Android accelerometer sensor tutorial by antoine vianey 
 * @author Sudar (http://sudarmuthu.com)
 * 
 */
public class Accelerometer extends Activity implements PhoneAccelerometerListener {

	private String TAG = "Accelerometer";
	
	private TextView[] directionPointers = new TextView[4];

	private boolean botStarted = false;
	private boolean missileEngaged = false;

	private BotDirection botDirection;
	private BotDirection missileDirection;
	
	private GestureDetector gestureScanner;
	private static Context CONTEXT;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CONTEXT = this;
        
        // Start in Landscape mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// TODO make sure it is set properly for Tablets
        
        // Start the Accelerometer
		AccelerometerManager.startListening((PhoneAccelerometerListener) CONTEXT);
		
        directionPointers[0] = (TextView) findViewById(R.id.left);
        directionPointers[1] = (TextView) findViewById(R.id.right);
        directionPointers[2] = (TextView) findViewById(R.id.up);
        directionPointers[3] = (TextView) findViewById(R.id.down);
        
        hideAllDirections();
        
        // Controlling Bot
        findViewById(R.id.controlBot).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button button = (Button) v;
				if (botStarted) {
					// bot is running, stop it
					botStarted = false;
					button.setText(R.string.startBot);
					changeBotDirection(BotDirection.STOP);	    		
				} else {
					// bot is not running, start it
					botStarted = true;
					button.setText(R.string.stopBot);
					changeBotDirection(BotDirection.UP);										
				}
			}
		});

        // controlling Missile
        findViewById(R.id.controlMissile).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button button = (Button) v;
				if (missileEngaged) {
					// Missile is engaged, dismiss it
					missileEngaged = false;
					button.setText(R.string.engageMissile);
					changeMissileDirection(BotDirection.STOP);
				} else {
					// Missile is not engaged, engage it
					missileEngaged = true;
					button.setText(R.string.dismissMissile);
					changeMissileDirection(BotDirection.UP);
				}
			}
		});
        
        gestureScanner = new GestureDetector(gestureListener);
        gestureScanner.setOnDoubleTapListener((OnDoubleTapListener) gestureListener);
    }
        
    /**
     * When the activity is resumed
     */
	@Override
    protected void onResume() {
    	super.onResume();
    	if (botStarted || missileEngaged) {
        	if (AccelerometerManager.isSupported()) {
        		AccelerometerManager.startListening(this);
        	}    		
    	}
    }
    
	/**
	 * When the activity is destroyed
	 */
	@Override
    protected void onDestroy() {
    	super.onDestroy();
    	if (AccelerometerManager.isListening()) {
    		AccelerometerManager.stopListening();
    	}
    }

	public static Context getContext() {
		return CONTEXT;
	}

	/* (non-Javadoc)
	 * @see com.sudarmuthu.android.sumi.accelerometer.BotListener#onBotDirectionChanged(com.sudarmuthu.android.sumi.accelerometer.BotDirection)
	 */
	@Override
	public void onPhoneDirectionChanged(BotDirection newDirection) {
		if (missileEngaged && missileDirection != newDirection) {
			changeMissileDirection(newDirection);
		}
		
		if (botStarted && botDirection != newDirection) {
			changeBotDirection(newDirection);			
		}
	}

    /**
	 * @param newDirection
	 */
	private void changeMissileDirection(BotDirection newDirection) {
		Log.d(TAG, "Missile Direction changed to " + newDirection.toString());
		
		if (newDirection != BotDirection.STOP) {
			hideAllDirections();
			directionPointers[newDirection.ordinal()].setVisibility(View.VISIBLE);			
		} else {
			hideAllDirections(true);
		}

		//TODO: Send request to Bot to change direction
		
	}

	/**
	 * Change the direction of the bot
	 * @param newDirection
	 */
	private void changeBotDirection(BotDirection newDirection) {
		Log.d(TAG, "Bot Direction changed to " + newDirection.toString());
		
		if (newDirection != BotDirection.STOP) {
			hideAllDirections();
			directionPointers[newDirection.ordinal()].setVisibility(View.VISIBLE);			
		} else {
			hideAllDirections(true);
		}

		//TODO: Send request to Bot to change direction
	}

	/**
	 * Fire missile
	 */
	private void fireMissile() {
		Log.d(TAG, "Missile Fired");
		
		missileDirection = BotDirection.STOP;
		// TODO Send request to fire missile
	}		

	/**
	 * @param b
	 */
	private void hideAllDirections(boolean b) {
		if (!botStarted && !missileEngaged) {
			// hide the direction pointers only when both bot and missile are stopped			
			hideAllDirections();
		}	
	}

	/**
	 * Hide all direction textivews
	 */
	private void hideAllDirections() {
		for (int i = 0; i < directionPointers.length; i++) {
			directionPointers[i].setVisibility(View.INVISIBLE);			
		}
	}

	/**
	 * Handle Touch Event
	 */
	@Override  
	 public boolean onTouchEvent(MotionEvent event) {  
        return gestureScanner.onTouchEvent(event); //return the double tap events  
    }  
	
	// for handling Gestures
	private OnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
		@Override
		public boolean onDoubleTap(MotionEvent paramMotionEvent) {
			Log.d(TAG, "Fire Missile");
			fireMissile();
			return false;
		}
	};
}