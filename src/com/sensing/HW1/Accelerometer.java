package com.sensing.HW1;

import java.text.DecimalFormat;
import java.util.Arrays;

//import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
//import android.widget.Toast;

public class Accelerometer implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Sensor mGyroscope;
	private Callable mainRef;
		
	private class RollingArray{
		int index = 0, capacity;
		float[] arr;
		
		RollingArray(int cap){
			capacity = cap;
			arr = new float[cap];
			Arrays.fill(arr, 0.0f);
		}
		
		public void add(float f){
			if (index == capacity)
				index = 0;
			arr[index++] = f; 
		}
		
		public float avg(){
			float sum = 0;
			for ( int i = 0; i < capacity; i++){
				sum+=arr[i];
			}
			return sum/capacity;
		}
		
	}

	public Accelerometer(Callable ref){
		
		mainRef = ref;
		mSensorManager = ref.getSensorManager();		
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

	}
	
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	RollingArray xArr = new RollingArray(100);
	RollingArray yArr = new RollingArray(100);
	RollingArray zArr = new RollingArray(100);
	public void onSensorChanged(SensorEvent event) { //http://www.touchqode.com/misc/20101025_jsug/20101025_touchqode_sensors.pdf
		
		DecimalFormat decimal = new DecimalFormat("#.####");
		
		
		if (event.sensor == mAccelerometer){
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
				
			 xArr.add(x);
			 yArr.add(y);
			 zArr .add(z);
				
			mainRef.displayAccelerometerXYZ(decimal.format(xArr.avg()), decimal.format(yArr.avg()), decimal.format(zArr.avg()));
		}
		
		if (event.sensor == mGyroscope){
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];

				
			mainRef.displayGyroXYZ(decimal.format(x), decimal.format(y), decimal.format(z));
		}
		
		
				
	}


	public void resume() {
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
		mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_FASTEST);
	}


	public void pause() {
		mSensorManager.unregisterListener(this);
	}

}