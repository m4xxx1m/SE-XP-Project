package ru.hse.se.xp.auth

import android.content.Context
import android.content.SharedPreferences

object AuthStorage {
    private const val PATH_NAME = "ru.hse.xp.auth"
    private const val KEY = "ru.hse.xp.auth.token.key"

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PATH_NAME, Context.MODE_PRIVATE)
    }

    private var sharedPreferences: SharedPreferences? = null

    fun saveToken(token: String) {
        sharedPreferences?.edit()?.putString(KEY, token)?.apply()
    }

    fun getToken(): String? {
        return sharedPreferences?.getString(KEY, null)
    }

    fun clearToken() {
        sharedPreferences?.edit()?.remove(KEY)?.apply()
    }
}