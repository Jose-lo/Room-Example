package com.example.maroom.repository

import androidx.lifecycle.LiveData
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertUser(user: UserEntity)
    fun getAllUserInfo(): LiveData<List<UserEntity>>
    suspend fun deleteUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
    fun searchDatabase(searchQuery: String): LiveData<List<UserEntity>>

    suspend fun insertSecondUser(user: UserSecondEntity)
    fun getAllSecondUserInfo(): LiveData<List<UserSecondEntity>>
    suspend fun deleteSecondUser(user: UserSecondEntity)
    suspend fun updateSecondUser(user: UserSecondEntity)
    fun searchSecondDatabase(searchQuery: String): LiveData<List<UserSecondEntity>>
}