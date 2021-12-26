package com.example.mytaskapplication.ui.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mytaskapplication.ui.models.User;

@Database(entities = {User.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao userDao();
}
