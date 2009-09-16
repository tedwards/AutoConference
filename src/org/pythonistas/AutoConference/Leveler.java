package org.pythonistas.AutoConference;

import android.app.Activity;
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

public class Leveler extends Activity implements SensorListener {
    private Float y = null;
    private Float z = null;
    private TextView xViewO = null;
    private TextView yViewO = null;
    private TextView zViewO = null;
    private SensorManager mSensorManager = null;
    private Boolean flatState = false;
    private Boolean inCallState = false;

    public Leveler(){
	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	register();
    	xViewO = (TextView) findViewById(R.id.sensorX);
        yViewO = (TextView) findViewById(R.id.sensorY);
        zViewO = (TextView) findViewById(R.id.sensorZ);

    }

    public void setCallState(boolean newState){
	inCallState=newState;
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
	registerReceiver(callReceiver,
			 new IntentFilter(PhoneListener.CALL_STATE_CHANGE));
    }

    public void unregister(){
	mSensorManager.unregisterListener(this); 
	unregisterReceiver(callReceiver);
    }

    
    public void onSensorChanged(int sensor, float[] values) {
        synchronized (this) {
            if (sensor == SensorManager.SENSOR_ORIENTATION) {
		float y=Math.abs(values[1]);
		    float z=Math.abs(values[2]);
                xViewO.setText("Orientation X: " + values[0]);
                yViewO.setText("Orientation Y: " + values[1]);
                zViewO.setText("Orientation Z: " + values[2]);
		if ( y<=10 && z<=10  && flatState==false) {
		    flatState = true;
		    //Toast.makeText(Leveler.this, "Speaker Phone On!", Toast.LENGTH_LONG).show();
		    AudioManager audioMan = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		    audioMan.setSpeakerphoneOn(true);
		    
		}
		else if (y>=10 && z>=10  &&flatState == true){
		    flatState=false;
		    //Toast.makeText(Leveler.this, "Speaker Phone Off!", Toast.LENGTH_LONG).show();
		    AudioManager audioMan = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		    audioMan.setSpeakerphoneOn(true);

		}
            }
        }
    }

   public void onAccuracyChanged(int sensor, int accuracy) {
       
   } 

    private BroadcastReceiver callReceiver = new BroadcastReceiver(){
	    public void onReceive(Context context, Intent intent){
		setCallState(intent.getBooleanExtra("callState",false));
	    }
	    
	};   
}
