package com.example.mytaskapplication.ui.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mytaskapplication.ui.models.User;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    //List<User> getAllUsers(); добавили livedata
    LiveData<List<User>> getAllUsers();

    @Insert
    void addUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);


}
