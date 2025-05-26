package com.example.tebakwarnareno;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserDatabase.class, "user_database")
                            .fallbackToDestructiveMigration() // Tambahkan ini supaya schema lama dihapus
                            .allowMainThreadQueries() // Simple: boleh query di main thread
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
