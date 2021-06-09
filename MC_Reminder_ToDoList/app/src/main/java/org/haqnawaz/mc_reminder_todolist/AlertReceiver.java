package org.haqnawaz.mc_reminder_todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Task task=intent.getParcelableExtra("Task");
//        Log.d("task",task.getTime());
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("ssss");
        notificationHelper.getManager().notify(1,nb.build());
    }
}
