package org.pythonistas.AutoConference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

public class AutoConference extends Activity {
    final String tag = "AutoConference";
    private int SIMPLE_NOTFICATION_ID=1984;

    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button start = (Button)findViewById(R.id.notifyButton);
        Button cancel = (Button)findViewById(R.id.cancelButton);
	//	ConferenceService.setContext(this);

        start.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		    // Start the service
		    startService(new Intent(AutoConference.this, ConferenceService.class));
		}
	    });
	
        cancel.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		    // Stop the service
		    stopService(new Intent(AutoConference.this, ConferenceService.class));
		}
	    });
    }
    
}