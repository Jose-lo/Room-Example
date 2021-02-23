package com.example.maroom.presentation

import androidx.lifecycle.*
import com.example.maroom.data.model.UserEntity
import com.example.maroom.repository.Repository
import com.example.maroom.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstScreenViewModel(private val repo:Repository):ViewModel() {

    fun insertUser(user: UserEntity){
        viewModelScope.launch {
            repo.insertUser(user)
        }
    }

    fun getUserInfo() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emitSource(repo.getAllUserInfo().map { Resource.Success(it) })
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteUser(user: UserEntity){
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UserEntity>>{
        return repo.searchDatabase(searchQuery)
    }
}