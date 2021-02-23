package com.example.maroom.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maroom.data.model.UserSecondEntity
import com.example.maroom.repository.Repository
import kotlinx.coroutines.launch

class DetailSecondScreenViewModel(private val repo: Repository): ViewModel() {

    fun updateSeconGradeUSer(user: UserSecondEntity){
        viewModelScope.launch {
            repo.updateSecondUser(user)
        }
    }
}