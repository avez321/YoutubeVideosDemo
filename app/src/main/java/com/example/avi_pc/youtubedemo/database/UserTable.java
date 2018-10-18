package com.example.avi_pc.youtubedemo.database;


import android.annotation.SuppressLint;
import android.content.Context;

import com.example.avi_pc.youtubedemo.injection.ActivityContext;
import com.example.avi_pc.youtubedemo.injection.ApplicationContext;
import com.example.avi_pc.youtubedemo.model.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class UserTable {
    private Context context;

    @Inject
    UserTable(@ApplicationContext Context context){
        this.context = context;
    }

    public int createOrUpdateUser(User user) {
        CreateOrUpdateStatus status = null;
        int count = -1;
        try {
            status = DatabaseHelper.getInstance(context).getUserDao().createOrUpdate(user);
        } catch (SQLException e) {
        }
        if (status != null) count = status.getNumLinesChanged();
        return count;
    }

    @SuppressLint("UseValueOf")
    public  User getUser() {
        List<User> users = null;
        try {
            users = DatabaseHelper.getInstance(context).getUserDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users != null) {
            if (users.size() > 0) {
                return users.get(0);
            } else {
                return null;
            }
        } else
            return null;
    }

    public int deleteSavedUser(Context context, int id) {
        DeleteBuilder<User, Integer> deleteBuilder = null;
        int count = 0;
        try {
            deleteBuilder = DatabaseHelper.getInstance(context).getUserDao().deleteBuilder();
            deleteBuilder.where().eq("id", id);
            count = deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}

