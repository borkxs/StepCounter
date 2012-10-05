package com.sensing.HW1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.hardware.SensorEvent;
import android.os.Environment;

public class FileWriter {

	// Returns OutputStreamWriter for initialized file
	public OutputStreamWriter initializeFile() {
		
		// Get external storage location
    	String root = Environment.getExternalStorageDirectory().toString();
    	File myDir = new File(root + "/accelerometer_traces");    
    	myDir.mkdirs();
    	String fname = "trace.csv";
    	File file = new File (myDir, fname);
    	
    	// Iterate file name
    	int n = 0;
    	while (file.exists ()) {
    		fname = Integer.toString(n)+fname;
    		file = new File (myDir, fname);
    		n+=1;
    	}
    	
    	// Create OutputStream
    	try {
    		FileOutputStream os = new FileOutputStream(file);
    		OutputStreamWriter out = new OutputStreamWriter(os);
    		return out;
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    OutputStreamWriter osw=null;
    	    return osw;
        }
    	
    }
	
	public void saveToFile(SensorEvent event, OutputStreamWriter out) {
		
		// Write data to file
    	try {
    		out.write(Float.toString(event.timestamp)+",");		// Date
    		out.write(Float.toString(event.values[0])+",");		// X coordinate
    		out.write(Float.toString(event.values[1])+",");		// Y
    		out.write(Float.toString(event.values[2])+"\n");	// Z
    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	
    }
    
    public void closeFile(OutputStreamWriter out) {
    	try {
			out.flush();
	    	out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}

