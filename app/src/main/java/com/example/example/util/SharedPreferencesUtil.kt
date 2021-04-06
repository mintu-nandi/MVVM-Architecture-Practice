package com.example.example.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtil {
    const val preferencesName = "AppPreferences"
    private const val AVERAGE_PRICE = "AVERAGE_PRICE"

    fun sharedPreference(context: Context): SharedPreferences = context.getSharedPreferences(
        preferencesName, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.averagePrice
        get() = getFloat(AVERAGE_PRICE, 0.0f)
        set(value) {
            editMe {
                it.putFloat(AVERAGE_PRICE, value)
            }
        }
}