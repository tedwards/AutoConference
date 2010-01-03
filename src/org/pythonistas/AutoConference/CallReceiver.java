package org.pythonistas.AutoConference;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
//import android.widget.Toast;
import java.lang.Math;

class CallReceiver extends BroadcastReceiver {
    private Boolean inCallState = false;
    private Context myContext = null;
    private Leveler myLevel = null;

    public CallReceiver(Context context, Leveler level){
	myContext = context;
	myLevel = level;
    }

    public void onReceive(Context context, Intent intent){
	setCallState(intent.getBooleanExtra("callState",false));
	if ( inCallState == true ){
	    myLevel.register();
	    //	    Toast.makeText(context, "Pickup the phone",Toast.LENGTH_LONG).show();
	}
	else{
	    myLevel.unregister();
	}
    }

    public Boolean getCallState(){
	return inCallState;
    }

    private void setCallState(Boolean newState){
	inCallState = newState;
    }
};
