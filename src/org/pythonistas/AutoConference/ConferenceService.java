package org.pythonistas.AutoConference;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.widget.Toast;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.
//import com.example.android.apis.R;

public class ConferenceService extends Service {
    private NotificationManager mNM;
    final String svcEnabled = "AutoConference is Enabled";
    final String svcDisabled = "AutoConference is Disabled";
    private Leveler level = null;
    private PhoneBroadcaster pb = null;
    private Boolean running = false;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    @Override
		public void onReceive(Context context, Intent intent) {
		// Check action just to be on the safe side.
		if (intent.getAction() == Intent.ACTION_SCREEN_OFF) {
		    // Unregisters the listener and registers it again.
		    //mSensorManager.unregisterListener(MyService.this);
		    //mSensorManager.registerListener(MyService.this, mSensor,
		    //SensorManager.SENSOR_DELAY_NORMAL);
		    level.unregister();
		    level.register();
		    Toast.makeText(ConferenceService.this, "Screen OFF", Toast.LENGTH_LONG).show();
		}
	    }
	};



    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class ConferenceBinder extends Binder {
        ConferenceService getService() {
            return ConferenceService.this;
        }
    }

    
    @Override
    public void onCreate() {
	if ( running == false ){
	    mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	    level = new Leveler(this);
	    pb = new PhoneBroadcaster();
	    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
	    this.registerReceiver(mReceiver, filter);
	    running=true;
	}
	//pb.setLeveler(level);
    }

    @Override
    public void onDestroy() {
	running=false;
	level.unregister();
        // Tell the user we stopped.
        Toast.makeText(this, svcDisabled, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new ConferenceBinder();

    /**
     * Show a notification while this service is running.
     */
    //    private void showNotification() {
    //        Toast.makeText(this, svcEnabled, Toast.LENGTH_SHORT).show();

}