/**
 * Part of the Sumi project - http://sudarmuthu.com/arduino/sumi
 * 
 * Copyright 2011  Sudar Muthu  (email : sudar@sudarmuthu.com)
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <sudar@sudarmuthu.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer or coffee in return - Sudar
 * ----------------------------------------------------------------------------
 */

package com.sudarmuthu.android.sumi.androidbotcontroller;

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
import at.abraxas.amarino.Amarino;

/**
 * The main Activity class
 * 
 * Code based on Android accelerometer sensor tutorial by Antoine Vianey
 *  
 * @author Sudar (http://sudarmuthu.com)
 * 
 */
public class Accelerometer extends Activity implements PhoneAccelerometerListener {
	
	//	TODO: Add voice based control
	private static final String TAG = "Accelerometer";
	
	// Amarino related
	private static final String deviceAddress = "00:06:66:02:CC:FA"; // TODO: Make it configurable
	private static final char amarinoBotFlag = 'b';
	private static final char amarinoMissileFlag = 'm';	
	
	private TextView[] directionPointers = new TextView[4];

	private boolean botStarted = false;
	private boolean missileEngaged = false;

	private PhoneDirection botDirection;
	private PhoneDirection missileDirection;
	
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
					changeBotDirection(PhoneDirection.STOP);	    		
				} else {
					// bot is not running, start it
					botStarted = true;
					button.setText(R.string.stopBot);
					changeBotDirection(PhoneDirection.UP);										
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
					changeMissileDirection(PhoneDirection.STOP);
				} else {
					// Missile is not engaged, engage it
					missileEngaged = true;
					button.setText(R.string.dismissMissile);
					changeMissileDirection(PhoneDirection.UP);
				}
			}
		});
        
        // Handle Gestures
        gestureScanner = new GestureDetector(gestureListener);
        gestureScanner.setOnDoubleTapListener((OnDoubleTapListener) gestureListener);
    }

    /**
     * When the activity is started for the first time
     */
    @Override
	protected void onStart() {
		super.onStart();
		Amarino.connect(this, deviceAddress);
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
		Amarino.disconnect(this, deviceAddress);    	
    }

	/**
	 * Return the context
	 * 
	 * @return
	 */
	public static Context getContext() {
		return CONTEXT;
	}

	/* (non-Javadoc)
	 * @see com.sudarmuthu.android.sumi.accelerometer.BotListener#onBotDirectionChanged(com.sudarmuthu.android.sumi.accelerometer.BotDirection)
	 */
	@Override
	public void onPhoneDirectionChanged(PhoneDirection newDirection) {
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
	private void changeMissileDirection(PhoneDirection newDirection) {
		Log.d(TAG, "Missile Direction changed to " + newDirection.toString());
		
		if (newDirection != PhoneDirection.STOP) {
			hideAllDirections();
			directionPointers[newDirection.ordinal()].setVisibility(View.VISIBLE);			
		} else {
			hideAllDirections(true);
		}

		//Send request to missile to change direction
		Amarino.sendDataToArduino(this, deviceAddress, amarinoMissileFlag, new int[]{newDirection.ordinal()});
		
	}

	/**
	 * Change the direction of the bot
	 * @param newDirection
	 */
	private void changeBotDirection(PhoneDirection newDirection) {
		Log.d(TAG, "Bot Direction changed to " + newDirection.toString());
		
		if (newDirection != PhoneDirection.STOP) {
			hideAllDirections();
			directionPointers[newDirection.ordinal()].setVisibility(View.VISIBLE);			
		} else {
			hideAllDirections(true);
		}

		//Send request to Bot to change direction
		Amarino.sendDataToArduino(this, deviceAddress, amarinoBotFlag, new int[]{newDirection.ordinal()});		
	}

	/**
	 * Fire missile
	 */
	private void fireMissile() {
		Log.d(TAG, "Missile Fired");
		
		missileDirection = PhoneDirection.STOP;
		// Send request to fire missile
		Amarino.sendDataToArduino(this, deviceAddress, amarinoMissileFlag, new int[]{5});
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