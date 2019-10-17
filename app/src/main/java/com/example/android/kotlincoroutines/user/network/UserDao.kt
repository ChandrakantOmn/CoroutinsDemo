package com.example.android.kotlincoroutines.user.network

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.kotlincoroutines.user.User

/**
 * Created by Chandra Kant on 17/10/19.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(title: User)
    @Query("select * from User")
    fun getUsers(): LiveData<List<User>>
}