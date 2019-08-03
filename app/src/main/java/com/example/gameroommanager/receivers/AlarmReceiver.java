package com.example.gameroommanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.gameroommanager.activities.RingtoneActivity;
import com.example.gameroommanager.database.MyDataBase;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MyDataBase.buildConsoleDataBase(context);

        final long consoleId = intent.getLongExtra("consoleId", -1);
        long reserveId = intent.getLongExtra("reserveId", -1);
        Log.e("on receive", "onCreate: " + reserveId);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MyDataBase.getInstance().getConsoleDao().updateConsoleState(consoleId, false);
            }
        });

        Intent activityIntent = new Intent(context, RingtoneActivity.class);

        activityIntent.putExtra("consoleId", consoleId);
        activityIntent.putExtra("reserveId", reserveId);

        context.startActivity(activityIntent);
    }
}
