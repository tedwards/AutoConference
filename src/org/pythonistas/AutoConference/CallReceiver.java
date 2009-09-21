package org.pythonistas.AutoConference;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
//import android.widget.Toast;
import java.lang.Math;


class CallReceiver extends BroadcastReceiver {
    private Boolean inCallState = false;
    public void onReceive(Context context, Intent intent){
	setCallState(intent.getBooleanExtra("callState",false));
	if ( inCallState == true ){
	    //	    Toast.makeText(context, "Pickup the phone",Toast.LENGTH_LONG).show();
	}
    }

    public Boolean getCallState(){
	return inCallState;
    }

    private void setCallState(Boolean newState){
	inCallState = newState;
    }
};
