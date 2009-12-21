package org.pythonistas.AutoConference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

public class AutoConference extends Activity {
    final String tag = "AutoConference";
    private int SIMPLE_NOTFICATION_ID=1984;
    private boolean running = false;
    private ImageButton tSwitch = null;
    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tSwitch = (ImageButton)findViewById(R.id.notifyButton);
	//	ConferenceService.setContext(this);

        tSwitch.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		    // Start the service
		    if ( running == false ) {
			running=true;
			startService(new Intent(AutoConference.this, ConferenceService.class));
			tSwitch.setImageResource(R.drawable.speaker_accept);
		    }
		    else {
			running=false;
			stopService(new Intent(AutoConference.this, ConferenceService.class));
			tSwitch.setImageResource(R.drawable.speaker_delete);
		    }
		}
	    });
	
    }
    
}