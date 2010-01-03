package org.pythonistas.AutoConference;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;


public class PhoneBroadcaster extends BroadcastReceiver {
    // private Leveler myLeveler;

    // public void setLeveler(Leveler level){
    // 	myLeveler = level;
    // }

    public void onReceive(Context context, Intent intent)
    {
	Intent phoneIntent = new Intent(context,
					PhoneListener.class);
	phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	context.startService(phoneIntent);
	TelephonyManager tManager = (TelephonyManager)
	    context.getSystemService(Context.TELEPHONY_SERVICE);
	PhoneListener pListener = new PhoneListener(context);
	//	pListener.setLeveler(myLeveler);
	tManager.listen(pListener,
			PhoneStateListener.LISTEN_CALL_STATE);
    }

}

