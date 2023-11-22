package com.example.exercisethreecw2.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ContactTable")
public class ContactModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String contactUUID;
    public String contactName;
    public String contactDob;
    public String contactEmail;
    public int contactAvatar;

    public ContactModel(String contactUUID, String contactName, String contactDob, String contactEmail, int contactAvatar) {
        this.contactUUID = contactUUID;
        this.contactName = contactName;
        this.contactDob = contactDob;
        this.contactEmail = contactEmail;
        this.contactAvatar = contactAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactUUID() {
        return contactUUID;
    }

    public void setContactUUID(String contactUUID) {
        this.contactUUID = contactUUID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactDob() {
        return contactDob;
    }

    public void setContactDob(String contactDob) {
        this.contactDob = contactDob;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getContactAvatar() {
        return contactAvatar;
    }

    public void setContactAvatar(int contactAvatar) {
        this.contactAvatar = contactAvatar;
    }
}
