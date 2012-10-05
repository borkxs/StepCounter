package com.sensing.HW1;


import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
//import android.widget.Toast;

public class MainActivity extends Activity implements Callable{

	Accelerometer accelerometer;
	SensorManager sensorManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = new Accelerometer(this);        
        
    }
    
    public void startButtonPushed(View buttonView){
    	
    	Button button = ((Button)buttonView);   	
    	
    	//Toast.makeText(getApplicationContext(), button.getText() + ":" + getString(R.string.start_button) , Toast.LENGTH_SHORT).show();
    	    	
    	if (button.getText().equals( getString(R.string.start_button) )) {
    		accelerometer.resume();
    		button.setText(R.string.pause_button);
    	}
    	else{
    		accelerometer.pause();
    		button.setText(R.string.start_button);
    	}
    	
    }
    
    //used by accelerometer
    public void displayAccelerometerXYZ(String x, String y, String z){
		((TextView)findViewById(R.id.x_axis)).setText(x);		
		((TextView)findViewById(R.id.y_axis)).setText(y);
		((TextView)findViewById(R.id.z_axis)).setText(z);
		   	
    }
    
    public void displayGyroXYZ(String x, String y, String z){
		((TextView)findViewById(R.id.gyro_x_axis)).setText(x);		
		((TextView)findViewById(R.id.gyro_y_axis)).setText(y);
		((TextView)findViewById(R.id.gyro_z_axis)).setText(z);
		   	
    }
    

    public SensorManager getSensorManager(){
    	return sensorManager;
    }

    protected void onResume(){
    	super.onResume();
    	accelerometer.resume();
    }
    
    protected void onPause() {
    	super.onPause();
    	accelerometer.pause();
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

interface Callable{
	public void displayAccelerometerXYZ(String x, String y, String z);
	public void displayGyroXYZ(String x, String y, String z);
	public SensorManager getSensorManager();
}