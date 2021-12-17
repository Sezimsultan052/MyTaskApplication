package com.example.mytaskapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ShareActionProvider;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context){

        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

    }

    public void saveBor(){
        preferences.edit().putBoolean("isShown", true).apply();

    }

    public boolean isBoardShown(){
        return  preferences.getBoolean("isShown", false);

    }
}
