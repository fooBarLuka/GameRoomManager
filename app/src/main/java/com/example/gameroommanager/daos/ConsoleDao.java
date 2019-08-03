package com.example.gameroommanager.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gameroommanager.models.Console;

import java.util.List;

@Dao
public interface ConsoleDao {

    @Query("SELECT * FROM console")
    List<Console> getAllConsoles();

    @Query("SELECT * FROM console WHERE id = :id")
    Console getConsoleByID(int id);

    @Insert
    void addConsole(Console console);

    @Update
    void updateConsole(Console console);

    @Query("UPDATE Console SET occupied = :newState WHERE id = :consoleId")
    void updateConsoleState(long consoleId, boolean newState);

    @Delete
    void deleteConsole(Console console);
}
