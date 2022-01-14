package com.example.mytaskapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

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

    public void savePic(String uri){
        preferences.edit().putString("img",uri).apply();
    }

    public void clearPic(){
        preferences.edit().remove("img").apply();

    }

    public Uri getPic(){
        //return preferences.getString("img", null);
        return Uri.parse(preferences.getString("img", " "));
    }

    public void saveInfo(String text){
        preferences.edit().putString("text", " ").apply();

    }

    public String getInfo(){
        return  preferences.getString("text", "");
    }


    public void clearInfo() {
        preferences.edit().remove("text").apply();
    }
}
