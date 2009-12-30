package org.pythonistas.AutoConference;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
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
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // Display a notification about us starting.  We put an icon in the status bar.
	level = new Leveler(this);
	pb = new PhoneBroadcaster();
	pb.setLeveler(level);
        //showNotification();
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        //mNM.cancel(1984);
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