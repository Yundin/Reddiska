package com.yundin.reddiska.data.storage

import android.content.SharedPreferences
import javax.inject.Inject

class AppStorage @Inject constructor(private val sharedPrefs: SharedPreferences) {

    fun getAppToken(): String? {
        return sharedPrefs.getString("app_token", null)
    }

    fun saveAppToken(token: String) {
        sharedPrefs.edit()
            .putString("app_token", token)
            .apply()
    }
}