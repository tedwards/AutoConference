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
    private Boolean leveling = false;
    private Boolean listening = false;
    private Context myContext = null;
    private CallReceiver myCallReceiver = null;

    final String speakerOn = "Speaker Phone is ON";
    final String speakerOff = "Speaker Phone is OFF";
    final String levelerOn = "Leveler is ON";
    final String levelerOff = "Leveler is OFF";
    final String listenerOn = "Leveler is listening";
    final String listenerOff = "Leveler is listening";

    public Leveler(Context context){
	myContext = context;
	mSensorManager = (SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);
	myCallReceiver = new CallReceiver(myContext, this);
	registerReceiver();
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

    public void registerReceiver(){
	if ( listening == false ){
	    myContext.registerReceiver(myCallReceiver,
				       new IntentFilter(PhoneListener.CALL_STATE_CHANGE));
	    //	    Toast.makeText(myContext, listenerOn, Toast.LENGTH_LONG).show();
	}
	listening = true;
    }

    public void unregisterReceiver(){
	if ( listening == true ){
	    myContext.unregisterReceiver(myCallReceiver);
	    //	    Toast.makeText(myContext, listenerOff, Toast.LENGTH_LONG).show();
	}
	listening = false;
    }

    public void register(){
	if ( leveling == false ){
	    mSensorManager.registerListener(this, 
					    Sensor.TYPE_ORIENTATION,
					    SensorManager.SENSOR_DELAY_NORMAL);
	    //	    Toast.makeText(myContext, levelerOn, Toast.LENGTH_LONG).show();
	}
	//registerReceiver();
	leveling=true;
    }

    public void unregister(){
	//unregisterReceiver();
	if ( leveling == true ){
	    mSensorManager.unregisterListener(this); 
	    //	    Toast.makeText(myContext, levelerOff, Toast.LENGTH_LONG).show();
	}
	leveling=false;
    }

    
    public void onSensorChanged(int sensor, float[] values) {
        synchronized (this) {
            if (sensor == SensorManager.SENSOR_ORIENTATION) {
		float y=Math.abs(values[1]);
		    float z=Math.abs(values[2]);
		    if ( (y<=10 || y>=170) && (z<=10)  && flatState==false) {
		    flatState = true;
		    if ( myCallReceiver.getCallState() == true ) {
			AudioManager audioMan = (AudioManager) myContext.getSystemService(Context.AUDIO_SERVICE);
			audioMan.setSpeakerphoneOn(true);
			Toast.makeText(myContext, speakerOn, Toast.LENGTH_SHORT).show();
		    }
		}
		else if (y>=10 && z>=10  &&flatState == true){
		    flatState=false;
		    if ( myCallReceiver.getCallState() == true ) {
			AudioManager audioMan = (AudioManager) myContext.getSystemService(Context.AUDIO_SERVICE);
			audioMan.setSpeakerphoneOn(false);
			Toast.makeText(myContext, speakerOff, Toast.LENGTH_SHORT).show();
		    }
		}
            }
        }
    }

    

    public void onAccuracyChanged( int a, int b) {

    }

}
