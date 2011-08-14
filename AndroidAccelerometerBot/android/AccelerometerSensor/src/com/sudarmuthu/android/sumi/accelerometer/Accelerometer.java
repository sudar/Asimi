package com.sudarmuthu.android.sumi.accelerometer;

import net.androgames.bolg.sample.accelerometer.R;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Android accelerometer sensor tutorial
 * @author antoine vianey
 * under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 */
public class Accelerometer extends Activity implements BotListener {
	
	private static Context CONTEXT;
	
	private TextView[] botDirection = new TextView[4];

	private boolean botStarted;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CONTEXT = this;
        
        // Start in Landscape mode. TODO make sure it is set properly for Tablets
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        botDirection[0] = (TextView) findViewById(R.id.left);
        botDirection[1] = (TextView) findViewById(R.id.right);
        botDirection[2] = (TextView) findViewById(R.id.up);
        botDirection[3] = (TextView) findViewById(R.id.down);
        
        hideAllDirections();
        
        botStarted = false;
        
        findViewById(R.id.startBot).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button button = (Button) v;
				if (botStarted) {
					// bot is running, stop it
					botStarted = false;
					button.setText(R.string.start);
					changeBotDirection(BotDirection.STOP);
		    		AccelerometerManager.stopListening();		    		
				} else {
					// bot is not running, start it
					botStarted = true;
					button.setText(R.string.stop);
					changeBotDirection(BotDirection.UP);					
		    		AccelerometerManager.startListening((BotListener) CONTEXT);					
				}
			}
		});
    }

	@Override
    protected void onResume() {
    	super.onResume();
    	if (botStarted) {
        	if (AccelerometerManager.isSupported()) {
        		AccelerometerManager.startListening(this);
        	}    		
    	}
    }
    
	@Override
    protected void onDestroy() {
    	super.onDestroy();
    	if (AccelerometerManager.isListening()) {
    		AccelerometerManager.stopListening();
    	}
    	
    }

	/* (non-Javadoc)
	 * @see com.sudarmuthu.android.sumi.accelerometer.BotListener#onBotDirectionChanged(com.sudarmuthu.android.sumi.accelerometer.BotDirection)
	 */
	@Override
	public void onBotDirectionChanged(BotDirection newDirection) {
		changeBotDirection(newDirection);
	}

    public static Context getContext() {
		return CONTEXT;
	}

	/**
	 * Hide all direction textivews
	 */
	private void hideAllDirections() {
		for (int i = 0; i < botDirection.length; i++) {
			botDirection[i].setVisibility(View.INVISIBLE);			
		}
	}

	/**
	 * Change the direction of the bot
	 * @param newDirection
	 */
	private void changeBotDirection(BotDirection newDirection) {
		hideAllDirections();
		if (newDirection != BotDirection.STOP) {
			botDirection[newDirection.ordinal()].setVisibility(View.VISIBLE);			
		}

		//TODO: Send request to Bot to change direction
	}
}