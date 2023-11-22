package com.example.exercisethreecw2.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exercisethreecw2.database.model.ContactModel;

import java.util.List;


@Dao
public interface ContactDao {
    @Insert
    void insert(ContactModel contactModel);

    @Query("SELECT * FROM ContactTable")
    List<ContactModel> getAllListContact();
}
