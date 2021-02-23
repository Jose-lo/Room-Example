package com.example.maroom.repository

import androidx.lifecycle.LiveData
import com.example.maroom.data.local.LocalDataSource
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import kotlinx.coroutines.flow.Flow

class RepositoryImpl (private val localDataSource: LocalDataSource): Repository{

    override suspend fun insertUser(user: UserEntity) {
        localDataSource.insertUser(user)
    }

    override fun getAllUserInfo(): LiveData<List<UserEntity>> {
        return localDataSource.getAllUserInfo()
    }

    override suspend fun deleteUser(user: UserEntity) {
        localDataSource.deleteUser(user)
    }

    override suspend fun updateUser(user: UserEntity) {
        localDataSource.updateUser(user)
    }

    override fun searchDatabase(searchQuery: String): LiveData<List<UserEntity>> {
        return localDataSource.searchDatabase(searchQuery)
    }

    //Second

    override suspend fun insertSecondUser(user: UserSecondEntity) {
        localDataSource.insertSecondUser(user)
    }

    override fun getAllSecondUserInfo(): LiveData<List<UserSecondEntity>> {
        return localDataSource.getAllSecondUserInfo()
    }

    override suspend fun deleteSecondUser(user: UserSecondEntity) {
        localDataSource.deleteSecondUser(user)
    }

    override suspend fun updateSecondUser(user: UserSecondEntity) {
        localDataSource.updateSecondUser(user)
    }

    override fun searchSecondDatabase(searchQuery: String): LiveData<List<UserSecondEntity>> {
        return localDataSource.searchSecondDatabase(searchQuery)
    }
}