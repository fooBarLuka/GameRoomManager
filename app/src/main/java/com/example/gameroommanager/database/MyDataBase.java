package com.example.gameroommanager.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gameroommanager.daos.ConsoleDao;
import com.example.gameroommanager.daos.ReserveDao;
import com.example.gameroommanager.models.Console;
import com.example.gameroommanager.models.Reserve;

@Database(entities = {Console.class, Reserve.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    private static final String MY_DATABASE_NAME = "myDatabaseName";

    public static MyDataBase myDataBase;

    public static MyDataBase getInstance() {
        return myDataBase;
    }

    public static void buildConsoleDataBase(Context context){
        if(myDataBase == null){
            myDataBase = Room.databaseBuilder(context, MyDataBase.class, MY_DATABASE_NAME).build();
        }
    }

    public static void addConsoleAsynchronous(final Console console){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                myDataBase.getConsoleDao().addConsole(console);
            }
        });
    }

    public static void addReserveAsynchronous(final Reserve reserve){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                myDataBase.getReserveDao().addReserve(reserve);
            }
        });
    }

    public static void updateConsoleAsynchronous(final Console console){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                myDataBase.getConsoleDao().updateConsole(console);
            }
        });
    }

    public abstract ConsoleDao getConsoleDao();

    public abstract ReserveDao getReserveDao();
}
