package com.example.maroom.data.local

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao:UserDao) {

    suspend fun insertUser(user: UserEntity){
        dao.insertUser(user)
    }

    fun getAllUserInfo(): LiveData<List<UserEntity>>{
       return dao.getAllUserInfo()
    }

    suspend fun deleteUser(user: UserEntity){
        dao.deleteUser(user)
    }

    suspend fun updateUser(user: UserEntity){
        dao.updateUser(user)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UserEntity>>{
        return dao.searchDatabase(searchQuery)
    }

    //Second User
    suspend fun insertSecondUser(user: UserSecondEntity){
        dao.insertSecondUser(user)
    }

    fun getAllSecondUserInfo(): LiveData<List<UserSecondEntity>>{
        return dao.getAllSecondUserInfo()
    }

    suspend fun deleteSecondUser(user: UserSecondEntity){
        dao.deleteSecondUser(user)
    }

    suspend fun updateSecondUser(user: UserSecondEntity){
        dao.updateSecondUser(user)
    }

    fun searchSecondDatabase(searchQuery: String): LiveData<List<UserSecondEntity>>{
        return dao.searchSecondDatabase(searchQuery)
    }
}