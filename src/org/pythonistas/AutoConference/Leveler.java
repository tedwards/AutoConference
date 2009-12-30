package org.pythonistas.AutoConference;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.widget.Toast;
import android.widget.TextView;
import android.media.AudioManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

import java.lang.Math;

public class Leveler implements SensorListener {
    private Float y = null;
    private Float z = null;
    private SensorManager mSensorManager = null;
    private Boolean flatState = false;
    private Context myContext = null;
    private CallReceiver myCallReceiver = null;
    final String speakerOn = "Speaker Phone is ON";
    final String speakerOff = "Speaker Phone is OFF";

    public Leveler(Context context){
	myContext = context;
	mSensorManager = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);
	myCallReceiver = new CallReceiver();
	register();
    }

    public boolean isFlat(){
	return flatState;
    }
    
    public float getY(){
	return y;
    }

    public float getZ(){
	return z;
    }

    public void register(){
	mSensorManager.registerListener(this, 
					Sensor.TYPE_ORIENTATION,
					SensorManager.SENSOR_DELAY_NORMAL);
	myContext.registerReceiver(myCallReceiver,
				   new IntentFilter(PhoneListener.CALL_STATE_CHANGE));
    }

    public void unregister(){
	mSensorManager.unregisterListener(this); 
	myContext.unregisterReceiver(myCallReceiver);
    }

    
    public void onSensorChanged(int sensor, float[] values) {
        synchronized (this) {
            if (sensor == SensorManager.SENSOR_ORIENTATION) {
		float y=Math.abs(values[1]);
		    float z=Math.abs(values[2]);
		    if ( y<=10 && z<=10  && flatState==false) {
		    flatState = true;
		    if ( myCallReceiver.getCallState() == true ) {
			AudioManager audioMan = (AudioManager) myContext.getSystemService(Context.AUDIO_SERVICE);
			audioMan.setSpeakerphoneOn(true);
			Toast.makeText(myContext, speakerOn, Toast.LENGTH_LONG).show();
		    }
		}
		else if (y>=10 && z>=10  &&flatState == true){
		    flatState=false;
		    if ( myCallReceiver.getCallState() == true ) {
			AudioManager audioMan = (AudioManager) myContext.getSystemService(Context.AUDIO_SERVICE);
			audioMan.setSpeakerphoneOn(false);
			Toast.makeText(myContext, speakerOff, Toast.LENGTH_LONG).show();
		    }
		}
            }
        }
    }

    // public void onCallSetup(){
    // 	if (myCallReceiver.getCallState() == true){
    // 	    float x = 0;
    // 	    float y = 0;
    // 	    float z = 0;
    // 	    float[] values = new float[3];
	    
    // 	}
    // }

    public void onAccuracyChanged( int a, int b) {

    }

}
