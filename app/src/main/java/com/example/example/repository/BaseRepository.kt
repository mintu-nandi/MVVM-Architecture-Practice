package com.example.example.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.example.api.status.Result
import com.example.example.util.ErrorHandler
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall (apiCall: suspend() -> T) : Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(apiCall.invoke())
            } catch (e: Exception) {
                if(e is HttpException) {
                    Result.Error(ErrorHandler.httpErrors(e))
                } else {
                    Result.Error(e.message ?: "Error Occurred!")
                }
            }
        }
    }
}