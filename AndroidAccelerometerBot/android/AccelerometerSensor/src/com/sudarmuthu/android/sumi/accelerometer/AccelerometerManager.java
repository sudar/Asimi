package com.sudarmuthu.android.sumi.accelerometer;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Android Accelerometer Sensor Manager Archetype
 * @author antoine vianey
 * under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 */
public class AccelerometerManager {
	
	private static Sensor sensor;
	private static SensorManager sensorManager;
	// you could use an OrientationListener array instead
	// if you plans to use more than one listener
	private static BotListener listener;
	
	/** indicates whether or not Accelerometer Sensor is supported */
	private static Boolean supported;
	/** indicates whether or not Accelerometer Sensor is running */
	private static boolean running = false;
	
	private static BotDirection currentDirection = null; 
	
	/**
	 * Returns true if the manager is listening to orientation changes
	 */
	public static boolean isListening() {
		return running;
	}
	
	/**
	 * Unregisters listeners
	 */
	public static void stopListening() {
		running = false;
		try {
			if (sensorManager != null && sensorEventListener != null) {
				sensorManager.unregisterListener(sensorEventListener);
			}
		} catch (Exception e) {}
	}
	
	/**
	 * Returns true if at least one Accelerometer sensor is available
	 */
	public static boolean isSupported() {
		if (supported == null) {
			if (Accelerometer.getContext() != null) {
				sensorManager = (SensorManager) Accelerometer.getContext().
						getSystemService(Context.SENSOR_SERVICE);
				List<Sensor> sensors = sensorManager.getSensorList(
						Sensor.TYPE_ACCELEROMETER);
				supported = new Boolean(sensors.size() > 0);
			} else {
				supported = Boolean.FALSE;
			}
		}
		return supported;
	}

	/**
	 * Registers a listener and start listening
	 * @param accelerometerListener
	 * 			callback for accelerometer events
	 */
	public static void startListening(
			BotListener accelerometerListener) {
		sensorManager = (SensorManager) Accelerometer.getContext().
				getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensors = sensorManager.getSensorList(
				Sensor.TYPE_ACCELEROMETER);
		if (sensors.size() > 0) {
			sensor = sensors.get(0);
			running = sensorManager.registerListener(
					sensorEventListener, sensor, 
					SensorManager.SENSOR_DELAY_GAME);
			listener = accelerometerListener;
		}
	}
	
	/**
	 * Configures threshold and interval
	 * And registers a listener and start listening
	 * @param accelerometerListener
	 * 			callback for accelerometer events
	 * @param threshold
	 * 			minimum acceleration variation for considering shaking
	 * @param interval
	 * 			minimum interval between to shake events
	 */
	public static void startListening(
			BotListener accelerometerListener, 
			int threshold, int interval) {
		startListening(accelerometerListener);
	}

	/**
	 * When the bot's direction is changed
	 * 
	 * @param newDirection
	 */
	private static void changeDirection(BotDirection newDirection) {
		if (currentDirection != newDirection) {
			currentDirection = newDirection;
    		// trigger change event
    		listener.onBotDirectionChanged(newDirection);			
		}
	}

	/**
	 * The listener that listen to events from the accelerometer listener
	 */
	private static SensorEventListener sensorEventListener = 
		new SensorEventListener() {

		private float x = 0;
		private float y = 0;
		private float z = 0;
		
		public void onSensorChanged(SensorEvent event) {
    		x = event.values[0];
			y = event.values[1];
			z = event.values[2];
    		
			if (z < 2) {
				changeDirection(BotDirection.DOWN);
			} else if (z > 2) {
				changeDirection(BotDirection.UP);
			}
			
			if (y < -2) {
				changeDirection(BotDirection.LEFT);
			} else if (y > 2) {
				changeDirection(BotDirection.RIGHT);			
			}
			
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			//dummy
		}
	};
}
