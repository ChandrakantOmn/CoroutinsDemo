package com.example.android.kotlincoroutines.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.kotlincoroutines.user.network.UserDao

/**
 * Created by Chandra Kant on 17/10/19.
 */

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TitleDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}

private lateinit var INSTANCE: TitleDatabase

/**
 * Instantiate a database from a context.
 */
fun getDatabase(context: Context): TitleDatabase {
    synchronized(TitleDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                    .databaseBuilder(
                            context.applicationContext,
                            TitleDatabase::class.java,
                            "usersDb"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
    return INSTANCE
}