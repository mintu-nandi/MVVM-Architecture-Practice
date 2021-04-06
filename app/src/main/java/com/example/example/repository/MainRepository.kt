package com.example.example.repository

import com.example.example.api.datasource.MainDataSource
import javax.inject.Inject

open class MainRepository @Inject constructor(private val mainDataSource: MainDataSource) :
    BaseRepository() {
    suspend fun getRemoteData() = safeApiCall { mainDataSource.getApiData() }
}