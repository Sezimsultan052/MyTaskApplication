package com.example.mytaskapplication;

import android.app.Application;

import androidx.room.Room;

import com.example.mytaskapplication.ui.data.local.AppDataBase;
import com.example.mytaskapplication.utils.Prefs;

public class App extends Application {
    public static  Prefs prefs;
    public static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        //new Prefs(this);
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
        prefs = new Prefs(this);


    }
}
