package com.example.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.example.repository.MainRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val mainRepository: MainRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MainRepository::class.java)
            .newInstance(mainRepository)
    }
}