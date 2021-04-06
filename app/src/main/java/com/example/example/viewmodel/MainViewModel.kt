package com.example.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.api.status.Result
import com.example.example.model.Todo
import com.example.example.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository) :
    ViewModel() {
    private val _user: MutableLiveData<Result<List<Todo>>> = MutableLiveData()

    fun getTodoList(): LiveData<Result<List<Todo>>> = _user
    fun getTodoData() = viewModelScope.launch {
        _user.value = Result.Loading
        _user.value = mainRepository.getRemoteData()
    }
}