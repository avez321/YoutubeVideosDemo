package com.example.avi_pc.youtubedemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class User implements Parcelable {
    @DatabaseField(generatedId=true)
    private int id;
    @DatabaseField
    private String username;
    @DatabaseField
    private String imageUrl;
    @DatabaseField
    private String email;

    public User(){ }

    public User(String username, String imageUrl, String email) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.email = email;
    }

    protected User(Parcel in) {
        username = in.readString();
        imageUrl = in.readString();
        email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsename(String usename) {
        this.username = usename;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(imageUrl);
        dest.writeString(email);
    }
}
