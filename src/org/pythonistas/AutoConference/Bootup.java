package org.pythonistas.AutoConference;

import android.content.Context; 
import android.content.Intent; 
import android.content.BroadcastReceiver; 

public class Bootup extends BroadcastReceiver { 

     @Override 
     public void onReceive(Context context, Intent intent) { 
	 /* Create intent which will finally start the Main-Activity. */ 
	 context.startService(new Intent(context, ConferenceService.class));
     } 
}