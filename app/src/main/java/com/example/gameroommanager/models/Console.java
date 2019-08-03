package com.example.gameroommanager.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Console {

    public Console(String tag, String games, boolean occupied) {
        this.tag = tag;
        this.games = games;
        this.occupied = occupied;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String tag;

//    public List<String> games;

    public String games;

    public boolean occupied;
}
