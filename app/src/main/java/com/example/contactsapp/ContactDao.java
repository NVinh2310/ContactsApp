package com.example.contactsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM Contact")
    public List<Contact> getAllContact();

    @Query("SELECT * FROM Contact WHERE name LIKE :name")
    public List<Contact> getContactByName(String name);

    @Insert
    public void insertAll(Contact... contact);

    @Query("DELETE FROM Contact")
    public void deleteAll();
}
