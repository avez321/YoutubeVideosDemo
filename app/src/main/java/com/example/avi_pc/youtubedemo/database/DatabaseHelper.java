package com.example.avi_pc.youtubedemo.database;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.avi_pc.youtubedemo.model.User;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static DatabaseHelper mInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDb";

    private Dao<User, Integer> mUserDao;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            mInstance.createTablesIfNotExists();
        }
        return mInstance;
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            mUserDao = getDao(User.class);
        }
        return mUserDao;
    }

    @Override
    public void close() {
        super.close();
        mUserDao = null;

    }

    public boolean isTableExist(SQLiteDatabase db, String table_name) {
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        createTablesIfNotExists();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        try {
            oldVersion = oldVersion + 1;
            switch (oldVersion) {
                case 3:
                    getUserDao().executeRaw("ALTER TABLE `categories` ADD COLUMN bonus_options TEXT;");

            }
            Log.d("Database", "Upgrading database..");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTablesIfNotExists() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, User.class, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("UseValueOf")
    public static User getUser(Context context) {
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

}

