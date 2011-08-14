package com.sudarmuthu.android.sumi.accelerometer;

public interface PhoneAccelerometerListener {

	public void onPhoneDirectionChanged(BotDirection newDirection);
}

/**
 * Different directions of the bot
 * 
 * @author sudar
 *
 */
enum BotDirection {
	LEFT,
	RIGHT,
	UP,
	DOWN,
	STOP
}