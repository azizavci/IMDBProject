package com.azizavci.imdbproject.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavList.class},version=1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    public abstract FavDao getFavDao();
    private static final String databaseName = "favorilerListesi.db";
    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase =
                    Room.databaseBuilder(context, AppDatabase.class, databaseName)
                            .allowMainThreadQueries()
                            .build();
        }
        return appDatabase;
    }
    public static void destroyInstance() {
        appDatabase = null;
    }
}