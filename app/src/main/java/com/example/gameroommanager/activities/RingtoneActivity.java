package com.example.gameroommanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gameroommanager.R;
import com.example.gameroommanager.database.MyDataBase;
import com.example.gameroommanager.models.Reserve;

public class RingtoneActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private EditText extraMoneyEditText;
    private EditText extraTimeEditText;
    private Button addExtraTimeButton;
    private Button turnOffRingtone;

    private long consoleId;
    private long reserveId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringtone);


        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        mediaPlayer = MediaPlayer.create(this, alert);
        mediaPlayer.start();

        Intent intent = getIntent();
        consoleId = intent.getLongExtra("consoleId", -1);
        reserveId = intent.getLongExtra("reserveId", -1);

        initUI();
        initUIActions();

    }

    private void initUI() {
        extraMoneyEditText = findViewById(R.id.extra_time_money_edit_text_id);
        extraTimeEditText = findViewById(R.id.extra_time_time_edit_text_id);
        addExtraTimeButton = findViewById(R.id.add_extra_time_button_id);
        turnOffRingtone = findViewById(R.id.turn_off_ringtone_button_id);
    }

    private void initUIActions() {
        turnOffRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                finish();
            }
        });

        addExtraTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String extraMoney = extraMoneyEditText.getText().toString();
                String extraTime = extraTimeEditText.getText().toString();

                if (extraMoney.equals("") || extraTime.equals("")) {
                    Toast.makeText(RingtoneActivity.this, "Both fields should be full", Toast.LENGTH_LONG).show();
                } else {
                    final double money = Double.parseDouble(extraMoney);
                    double time = Double.parseDouble(extraTime);

                    final long finishedTime = (long) (time * 1000 * 60 * 60);

                    MyDataBase.buildConsoleDataBase(RingtoneActivity.this);
                    Toast.makeText(RingtoneActivity.this, "movida asyncamde " + consoleId + " " + reserveId, Toast.LENGTH_LONG).show();
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            MyDataBase.getInstance().getConsoleDao().updateConsoleState(consoleId, true);
                            Reserve reserve = MyDataBase.getInstance().getReserveDao().getReserve(reserveId);
                            reserve.finishedTime += finishedTime;
                            reserve.price += money;
                            MyDataBase.getInstance().getReserveDao().updateReserve(reserve);
                        }
                    });
                }
            }
        });
    }
}
