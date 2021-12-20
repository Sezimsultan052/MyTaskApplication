package com.example.mytaskapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ShareActionProvider;

public class Prefs {

    private SharedPreferences preferences;
//    private static Prefs Instance;
//
//    public static Prefs getInstance() {
//        return Instance;
//    }


    public Prefs(Context context){
        //Instance = this;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

    }

    public void saveBordState(){
        preferences.edit().putBoolean("isShown", true).apply();

    }

    public boolean isBoardShown(){
        return  preferences.getBoolean("isShown", false);

    }
}
