package com.corona.covid_19explorer;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("todo");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "reminder")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Reminder!")
                .setContentText(message);
        builder.setVibrate(new long[]  { 1000, 1000, 1000, 1000, 1000 });
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setLights(Color.RED, 3000, 3000);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, builder.build());
    }
}
