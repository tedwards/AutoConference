package org.pythonistas.AutoConference;

import android.app.Activity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.hardware.SensorListener;
import android.hardware.Sensor;
import android.telephony.TelephonyManager;
import android.telephony.PhoneStateListener;
import java.lang.Math;


public class AutoConference extends Activity {
    final String tag = "AutoConference";
    private int SIMPLE_NOTFICATION_ID=1984;

    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	PhoneBroadcaster pb = new PhoneBroadcaster();
        Button start = (Button)findViewById(R.id.notifyButton);
        Button cancel = (Button)findViewById(R.id.cancelButton);
	

        start.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		    Toast.makeText(AutoConference.this, "Hello World", Toast.LENGTH_LONG).show();
		}
	    });
	
        cancel.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
		    Toast.makeText(AutoConference.this,"Goodbye World", Toast.LENGTH_LONG).show();
		}
	    });

    }

    
    @Override
	protected void onResume() {
        super.onResume();
	// register this class as a listener for the orientation and accelerometer sensors
        //mLevel.register();
	//mPhoner.register();
    }

    @Override
	protected void onStop() {
        // unregister listener
        //mLevel.unregister();
	//mPhoner.unregister();
        super.onStop();
    }    
    
}