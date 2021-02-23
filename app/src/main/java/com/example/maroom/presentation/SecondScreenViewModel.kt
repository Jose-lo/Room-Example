package com.example.maroom.presentation

import androidx.lifecycle.*
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import com.example.maroom.repository.Repository
import com.example.maroom.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondScreenViewModel(private val repo:Repository): ViewModel() {

    fun insertSecondGradeUser(user: UserSecondEntity){
        viewModelScope.launch {
            repo.insertSecondUser(user)
        }
    }

    fun fetchSecondGradeList()= liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emitSource(repo.getAllSecondUserInfo().map { Resource.Success(it) })
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteSecondGradeUser(user:UserSecondEntity){
        viewModelScope.launch {
            repo.deleteSecondUser(user)
        }
    }

    fun searchSeconGradeDatabase(searchQuery: String): LiveData<List<UserSecondEntity>> {
        return repo.searchSecondDatabase(searchQuery)
    }


}