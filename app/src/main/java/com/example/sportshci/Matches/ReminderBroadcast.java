package com.example.sportshci.Matches;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.sportshci.R;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String teamsString = intent.getStringExtra("teamString");
        String dateString = intent.getStringExtra("dateString");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyChannel")
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle(teamsString)
                .setContentText(dateString)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());
    }
}
