package com.example.maroom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity
import com.example.maroom.repository.Repository
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repo: Repository): ViewModel() {

    fun updateUser(user: UserEntity){
        viewModelScope.launch {
            repo.updateUser(user)
        }
    }

    fun updateSecondUser(user: UserSecondEntity){
        viewModelScope.launch {
            repo.updateSecondUser(user)
        }
    }
}