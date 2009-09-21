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
	//	Leveler myLeveler = new Leveler(myContext);
    }
    
    public void onCallStateChanged(int state, String incomingNumber)
    {
	super.onCallStateChanged(state, incomingNumber);

	switch(state)
	    {
	    case TelephonyManager.CALL_STATE_IDLE:
		intent.putExtra(CALL_STATE_EXTRA,false);
		myContext.sendBroadcast(intent);
		break;
	    case TelephonyManager.CALL_STATE_OFFHOOK:
		intent.putExtra(CALL_STATE_EXTRA,true);
		myContext.sendBroadcast(intent);
		break;
	    }
    }

} 