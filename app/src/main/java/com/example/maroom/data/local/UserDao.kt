package com.example.maroom.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM userEntity")
    fun getAllUserInfo(): LiveData<List<UserEntity>>

    @Insert()
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM userEntity WHERE name COLLATE SQL_Latin1_General_Cp1_CI_AI LIKE :searchQuery")
    fun searchDatabase(searchQuery: String):LiveData<List<UserEntity>>

    @Query("SELECT * FROM userSecondEntity")
    fun getAllSecondUserInfo(): LiveData<List<UserSecondEntity>>

    @Insert()
    suspend fun insertSecondUser(user: UserSecondEntity)

    @Delete
    suspend fun deleteSecondUser(user: UserSecondEntity)

    @Update
    suspend fun updateSecondUser(user: UserSecondEntity)

    @Query("SELECT * FROM userSecondEntity WHERE name COLLATE SQL_Latin1_General_Cp1_CI_AI LIKE :searchQuery")
    fun searchSecondDatabase(searchQuery: String):LiveData<List<UserSecondEntity>>

}