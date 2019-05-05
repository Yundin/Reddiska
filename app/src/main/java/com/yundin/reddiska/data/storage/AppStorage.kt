package com.yundin.reddiska.data.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class AppStorage(app: Application) {

    private val sharedPrefs: SharedPreferences = app.getSharedPreferences("myprefs", Context.MODE_PRIVATE)

    fun getAppToken(): String? {
        return sharedPrefs.getString("app_token", null)
    }

    fun saveAppToken(token: String) {
        sharedPrefs.edit()
            .putString("app_token", token)
            .apply()
    }
}