package org.pythonistas.AutoConference;

import android.telephony.PhoneStateListener; 
import android.telephony.TelephonyManager; 
import android.content.Intent;
import android.content.Context;
//import android.widget.Toast;

public class PhoneListener extends PhoneStateListener {
    protected static final String CALL_STATE_CHANGE = "org.pythonistas.AutoConference.intent.action.CALL_STATE_CHANGE";
    protected static final String CALL_STATE_EXTRA = "callState";
    private Intent intent = new Intent(CALL_STATE_CHANGE);
    private Context myContext = null;
    //    private Leveler myLeveler = null;

    public PhoneListener(Context context){
	myContext = context;
    }
    
    // public void setLeveler(Leveler level){
    // 	myLeveler = level;
    // }
    
    public void onCallStateChanged(int state, String incomingNumber)
    {
	super.onCallStateChanged(state, incomingNumber);

	switch(state)
	    {
		//User hung up the phone
	    case TelephonyManager.CALL_STATE_IDLE:
		intent.putExtra(CALL_STATE_EXTRA,false);
		myContext.sendBroadcast(intent);
		// if ( myLeveler != null ) {
		//     myLeveler.unregister();
		// }
		break;
		//User picked up the phone
	    case TelephonyManager.CALL_STATE_OFFHOOK:
		// if ( myLeveler != null ) {
		//     myLeveler.register();
		// }
		intent.putExtra(CALL_STATE_EXTRA,true);
		myContext.sendBroadcast(intent);
		break;
	    }
    }

} 