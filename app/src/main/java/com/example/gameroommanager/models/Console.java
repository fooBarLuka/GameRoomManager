package com.example.gameroommanager.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Console {

    public Console(String tag, String games, boolean occupied) {
        this.tag = tag;
        this.games = games;
        this.occupied = occupied;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String tag;

    public String games;

    public boolean occupied;
}
