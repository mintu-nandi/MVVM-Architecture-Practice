package com.example.example.util

import android.content.SharedPreferences

object SharedPreferencesUtil {
    const val preferencesName = "AppPreferences"

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

}