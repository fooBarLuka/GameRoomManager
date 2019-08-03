package com.example.gameroommanager.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.gameroommanager.receivers.AlarmReceiver;

public class AlarmManagerUtil {

    public static void setAlarm(Context context, long time, long consoleId, long reserveId){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.e("set alarmshi", "onCreate: " + reserveId);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("consoleId", consoleId);
        intent.putExtra("reserveId", reserveId);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(),
                intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
}
