package org.pythonistas.AutoConference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import com.admob.android.ads.AdView;
//import android.widget.Toast;

public class AutoConference extends Activity {
    final String tag = "AutoConference";
    final String svcEnabled = "AutoConference is Enabled";
    final String svcDisabled = "AutoConference is Disabled";
    private int SIMPLE_NOTFICATION_ID=1984;
    private boolean running = false;
    private boolean firstrun = true;
    private ImageButton tSwitch = null;
    private Button buyButton = null;
    private TextView tStatus = null;
    private AdView mAd;
    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tSwitch = (ImageButton)findViewById(R.id.notifyButton);
        buyButton = (Button)findViewById(R.id.buyButton);
	tStatus = (TextView)findViewById(R.id.svcStatusTextView);
	
	mAd = (AdView) findViewById(R.id.ad);
	mAd.requestFreshAd();

	if (firstrun == true){
	    firstrun=false;	    
	    if (running == false) {
		running=true;
		startService(new Intent(AutoConference.this, ConferenceService.class));
		tSwitch.setImageResource(R.drawable.speaker_accept);
		tStatus.setText("AutoConference Enabled");
	    }
	    else {
		running=false;
		stopService(new Intent(AutoConference.this, ConferenceService.class));
		tSwitch.setImageResource(R.drawable.speaker_delete);
		tStatus.setText("AutoConference Disabled");
	    }
	}
        tSwitch.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		    // Start the service
		    if ( running == false ) {
			running=true;
			startService(new Intent(AutoConference.this, ConferenceService.class));
			//startService(new Intent("org.pythonistas.AutoConference.REMOTE_SERVICE"));
			tSwitch.setImageResource(R.drawable.speaker_accept);
			//			Toast.makeText(AutoConference.this, svcEnabled, Toast.LENGTH_SHORT).show();
			tStatus.setText("AutoConference Enabled");

		    }
		    else {
			running=false;
			stopService(new Intent(AutoConference.this, ConferenceService.class));
			//stopService(new Intent("org.pythonistas.AutoConference.REMOTE_SERVICE"));
			tSwitch.setImageResource(R.drawable.speaker_delete);
			//			Toast.makeText(AutoConference.this, svcDisabled, Toast.LENGTH_SHORT).show();
			tStatus.setText("AutoConference Disabled");

		    }
		}
	    });

	buyButton.setOnClickListener(new OnClickListener() {
		public void onClick(View v){

		    Intent buyIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:com.pythonistas.AutoConference")); 
		    startActivity(buyIntent);

		}
	    });
    }
    
}