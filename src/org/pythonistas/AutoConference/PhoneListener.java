package org.pythonistas.AutoConference;

import android.telephony.PhoneStateListener; 
import android.telephony.TelephonyManager; 
import android.content.Intent;


public class PhoneListener extends PhoneStateListener {
    protected static final String CALL_STATE_CHANGE = "org.pythonistas.AutoConference.intent.action.CALL_STATE_CHANGE";
    protected static final String CALL_STATE_EXTRA = "callState";
    private Intent intent = new Intent(CALL_STATE_CHANGE);
    private Leveler myLevel = null;
    private CallReceiver myReceiver = null;
    public PhoneListener(){
	myLevel = new Leveler();
	myReceiver = new CallReceiver(myLevel);
    }
    
    public void onCallStateChanged(int state, String incomingNumber)
    {
	super.onCallStateChanged(state, incomingNumber);

	switch(state)
	    {
	    case TelephonyManager.CALL_STATE_IDLE:
		intent.putExtra(CALL_STATE_EXTRA,false);
		sendBroadcast(intent);
		break;
	    case TelephonyManager.CALL_STATE_OFFHOOK:
		intent.putExtra(CALL_STATE_EXTRA,true);
		sendBroadcast(intent);
		break;
	    }
    }

} 