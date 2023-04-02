package com.template.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val EXTRA_LONG = "extra_long"

    const val QUESTION = "?"

    const val EXTRA_LEVEL = "extra_level"
    const val ERROR_MSG = "Unknown level"
    const val LONG_ZERO = 0L
    const val SECONDS_3 = 3000L
    const val SECONDS_HALF = 500L
    const val SECONDS = 1000L

    const val PREFERENCES_NAME = "name_preferences"
    const val SETTINGS = "settings"
    const val UNKNOWN_NUMBER = "Unknown Number!"
    const val UNKNOWN_TEXT_VIEW = "Unknown TextView!"
    const val UNKNOWN_MODEL = "Unknown mode!"



    @SuppressLint("SimpleDateFormat")
    fun formattedTimer(millisUnit: Long): String {

        return SimpleDateFormat("mm:ss").format(Date(millisUnit))

    }
}