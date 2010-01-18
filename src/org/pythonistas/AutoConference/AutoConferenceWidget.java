package org.pythonistas.AutoConference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.Service;
import android.os.Binder;
import android.os.IBinder;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.content.res.Resources;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.widget.RemoteViews;

public class AutoConferenceWidget extends AppWidgetProvider {
    private ImageButton tSwitch = null;
    
    public void onUpdate( Context context, 
			  AppWidgetManager appWidgetManager, 
			  int[] appWidgetIds) {
	//Start A Service so the update doesn't get interrupted
	context.startService(new Intent(context, UpdateService.class));
    }
    
    public static class UpdateService extends Service {
	@Override
	    public void onStart(Intent intent, int startId) {
            // Build the widget update for today
            RemoteViews views = buildUpdate(this);

            // Push update for this widget to the home screen
            ComponentName thisWidget = new ComponentName(this, AutoConferenceWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            manager.updateAppWidget(thisWidget, views);
        }

	public RemoteViews buildUpdate(Context context) {
            // Pick out month names from resources
            Resources res = context.getResources();
	    RemoteViews views = null;
	    views = new RemoteViews(context.getPackageName(), R.layout.toggle_widget);
	    views.setImageViewResource(R.id.notifyWidgetButton, R.drawable.widget_accept);

	    return views;
	}

	@Override
	    public IBinder onBind(Intent intent) {
	    // We don't need to bind to this service
	    return null;
	}
    }
}