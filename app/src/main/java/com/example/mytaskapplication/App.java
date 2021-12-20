package com.example.mytaskapplication;

import android.app.Application;

import com.example.mytaskapplication.utils.Prefs;

public class App extends Application {
    public static  Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        //new Prefs(this);
        prefs = new Prefs(this);


    }
}
