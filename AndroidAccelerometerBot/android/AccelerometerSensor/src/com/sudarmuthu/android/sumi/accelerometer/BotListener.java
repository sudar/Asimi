package com.sudarmuthu.android.sumi.accelerometer;

public interface BotListener {

	public void onBotDirectionChanged(BotDirection newDirection);
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