package com.example.example.util

import com.google.gson.Gson
import retrofit2.HttpException

object ErrorHandler {
    fun httpErrors(exception: HttpException): String {
        when (exception.code()) {
            400 -> {
                return Gson().fromJson(
                    exception.response()!!.errorBody()!!.charStream().readText(),
                    String::class.java
                )
            }
            401 -> {
                return Gson().fromJson(
                    exception.response()!!.errorBody()!!.charStream().readText(),
                    String::class.java
                )
            }
            else -> {
                return exception.message()
            }
        }
    }
}