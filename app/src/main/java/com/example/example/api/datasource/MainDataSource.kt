package com.example.example.api.datasource

import com.example.example.api.ApiService
import javax.inject.Inject

class MainDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getApiData() = apiService.getTodos()
}