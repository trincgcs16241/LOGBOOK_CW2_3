package com.example.exercisethreecw2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.exercisethreecw2.database.dao.ContactDao;
import com.example.exercisethreecw2.database.model.ContactModel;

@Database(entities = {ContactModel.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract ContactDao getContactDao();
}
