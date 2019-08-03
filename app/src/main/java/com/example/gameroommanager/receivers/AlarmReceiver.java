package com.example.gameroommanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.gameroommanager.activities.RingtoneActivity;
import com.example.gameroommanager.database.MyDataBase;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MyDataBase.buildConsoleDataBase(context);

        final int consoleId = intent.getIntExtra("consoleId", -1);
        int reserveId = intent.getIntExtra("reserveId", -1);

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
