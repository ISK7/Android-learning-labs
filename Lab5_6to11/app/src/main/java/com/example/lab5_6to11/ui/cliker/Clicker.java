package com.example.lab5_6to11.ui.cliker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.lab5_6to11.R;

/**
 * Implementation of App Widget functionality.
 */
public class Clicker extends AppWidgetProvider {
    AppWidgetManager awm;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clicker);
        views.setTextViewText(R.id.text_view_counter, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static int counter = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        awm = appWidgetManager;

        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clicker);
            views.setTextViewText(R.id.text_view_counter, String.valueOf(counter));


            Intent intentIncrement = new Intent(context, Clicker.class);
            intentIncrement.setAction("INCREMENT_COUNTER");
            PendingIntent pendingIntentIncrement = PendingIntent.getBroadcast(context, 0, intentIncrement, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.button_increment, pendingIntentIncrement);

            Intent intentReset = new Intent(context, Clicker.class);
            intentReset.setAction("RESET_COUNTER");
            PendingIntent pendingIntentReset = PendingIntent.getBroadcast(context, 1, intentReset, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.button_reset, pendingIntentReset);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals("INCREMENT_COUNTER")) {
            counter++;
        } else if (intent.getAction().equals("RESET_COUNTER")) {
            counter = 0;
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, Clicker.class));
        onUpdate(context,appWidgetManager,appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}