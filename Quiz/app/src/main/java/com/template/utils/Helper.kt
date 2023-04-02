package com.template.utils

import android.content.Context
import android.widget.Toast
import com.template.domain.model.Level
import java.lang.RuntimeException

fun numberOfQuestions(level: String) : Int = when (level) {
    Level.EASY.toString() -> 5
    Level.MEDIUM.toString() -> 10
    Level.HARD.toString() -> 15
    else -> throw RuntimeException("Unknown level $level")
}

fun makeToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun formattedTimer(millisUnitFinished: Long): String {

    val second = millisUnitFinished / Constants.SECONDS
    val minute = second / Constants.MINUTES
    val leftSeconds = second - (minute * Constants.SECONDS)

    return String.format(Constants.TIME_FORMAT, minute, leftSeconds)

}