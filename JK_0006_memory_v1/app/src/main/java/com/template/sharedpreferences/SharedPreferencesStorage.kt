package com.template.sharedpreferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.template.utils.Constants.PREFERENCES_NAME

object SharedPreferencesStorage{

    lateinit var sharedPref: SharedPreferences

    fun with(application: Application) {
        sharedPref = application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun <T> saveGameSettings(settings: T, key: String) {
        val gsonSettings = GsonBuilder().create().toJson(settings)

        sharedPref.edit().putString(key, gsonSettings).apply()
    }

    inline fun <reified T> getGameSettings(key: String): T? {
        val json = sharedPref.getString(key, null)
        return GsonBuilder().create().fromJson(json, T::class.java)
    }
}