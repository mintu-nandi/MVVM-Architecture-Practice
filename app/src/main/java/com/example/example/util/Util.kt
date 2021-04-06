package com.example.example.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

object Util {
    fun decimalFormatter(value: Double) = String.format("%.2f", value)
}