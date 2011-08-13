package com.sudarmuthu.android.sumi.accelerometer;

import net.androgames.bolg.sample.accelerometer.R;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Android accelerometer sensor tutorial
 * @author antoine vianey
 * under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 */
public class Accelerometer extends Activity implements AccelerometerListener {
	
	private static Context CONTEXT;
	
	private TextView left;
	private TextView right;
	private TextView up;
	private TextView down;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CONTEXT = this;
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        left = (TextView) findViewById(R.id.left);
        right = (TextView) findViewById(R.id.right);
        up = (TextView) findViewById(R.id.up);
        down = (TextView) findViewById(R.id.down);
        
        hideAllDirections();
    }

    protected void onResume() {
    	super.onResume();
    	if (AccelerometerManager.isSupported()) {
    		AccelerometerManager.startListening(this);
    	}
    }
    
    protected void onDestroy() {
    	super.onDestroy();
    	if (AccelerometerManager.isListening()) {
    		AccelerometerManager.stopListening();
    	}
    	
    }
	
    public static Context getContext() {
		return CONTEXT;
	}

	/**
	 * onAccelerationChanged callback
	 */
	public void onAccelerationChanged(float x, float y, float z) {
		if (z < 2) {
			changeDirection(down);
		} else if (z > 2) {
			changeDirection(up);
		}
		
		if (y < -2) {
			changeDirection(left);
		} else if (y > 2) {
			changeDirection(right);			
		}
	}
    
	/**
	 * @param left2
	 */
	private void changeDirection(TextView direction) {
		hideAllDirections();
		direction.setVisibility(View.VISIBLE);
	}

	private void hideAllDirections() {
		left.setVisibility(View.INVISIBLE);
		right.setVisibility(View.INVISIBLE);
		up.setVisibility(View.INVISIBLE);
		down.setVisibility(View.INVISIBLE);		
	}
	
}