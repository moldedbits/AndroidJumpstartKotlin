package com.moldedbits.android.utils

import android.content.Context
import android.content.SharedPreferences

import com.moldedbits.android.BaseApplication

/**
 * Created by abhishek
 * on 18/03/15.
 */
class LocalStorage private constructor() {

    private val preferences: SharedPreferences

    init {
        preferences = BaseApplication.instance?.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE)!!
    }

    fun storeData(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun storeData(key: String, value: Float) {
        val editor = preferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun getFloat(key: String): Float {
        return preferences.getFloat(key, 0.0f)
    }

    fun getLong(key: String): Long {
        return preferences.getLong(key, 0)
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {

        val instance = LocalStorage()

        // TODO: 05/04/16 find a way to make this variable and app specific
        // TODO: 05/04/16 may be get application id somehow and append to this
        private val PREFS_NAME = "com.moldedbits.SharedPrefs"
    }
}
