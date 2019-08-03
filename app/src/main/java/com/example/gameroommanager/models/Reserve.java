package com.example.gameroommanager.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reserve {

    public Reserve(int consoleId, String game, long started, double price){
        this.consoleId = consoleId;
        this.game = game;
        this.started = started;
        finished = calculateFinishedTime(started, price);
        this.price = price;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    public long started;

    public long finished;

    public double price;

    public int consoleId;

    public String game;

    private long calculateFinishedTime(long startedTime, double payment){
        return startedTime + (long)(payment / 4 * 1000 * 60 * 60);
    }
}
