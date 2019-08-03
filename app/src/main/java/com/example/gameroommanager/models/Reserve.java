package com.example.gameroommanager.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reserve {

    public Reserve(long consoleId, String game, long started, double price){
        this.consoleId = consoleId;
        this.game = game;
        this.started = started;
        finishedTime = calculateFinishedTime(started, price);
        this.price = price;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long consoleId;

    public String game;

    public long started;

    public long finishedTime;

    public double price;

    public boolean finished = false;


    private long calculateFinishedTime(long startedTime, double payment){
        return startedTime + (long)(payment / 4 * 1000 * 60 * 60);
    }
}
