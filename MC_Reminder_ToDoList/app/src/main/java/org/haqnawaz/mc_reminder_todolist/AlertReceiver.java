package org.haqnawaz.mc_reminder_todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    String task;
    int id;
    @Override
    public void onReceive(Context context, Intent intent) {
        task=intent.getStringExtra("title");
        id=intent.getIntExtra("id", 1);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(task);
        notificationHelper.getManager().notify(id, nb.build());
    }
}
