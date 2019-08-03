package com.example.gameroommanager.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gameroommanager.models.Reserve;

import java.util.List;

@Dao
public interface ReserveDao{

    @Query("SELECT * FROM reserve")
    List<Reserve> getAllReserves();

    @Query("SELECT * FROM reserve WHERE id = :id")
    Reserve getReserve(long id);

    @Insert
    long addReserve(Reserve reserve);

    @Update
    void updateReserve(Reserve reserve);

    @Delete
    void deleteReserve(Reserve reserve);
}
