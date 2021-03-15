package com.android.hogwartsenciclopedia.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context?) {
    var sharedPreferences: SharedPreferences = context!!.getSharedPreferences(SharedPreferenceLiveData.NAME, Context.MODE_PRIVATE)
    var currentTheme: String = SharedPreferenceLiveData.DEFAULT

    companion object : SingletonHolder<PreferenceManager, Context>(::PreferenceManager)

    fun editPreferences(theme: String) {
        sharedPreferences.edit().putString(SharedPreferenceLiveData.CURRENT_THEME, theme).apply()
    }
}