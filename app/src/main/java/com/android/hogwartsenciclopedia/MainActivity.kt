package com.android.hogwartsenciclopedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.hogwartsenciclopedia.utils.PreferenceManager
import com.android.hogwartsenciclopedia.utils.SharedPreferenceLiveData
import com.android.hogwartsenciclopedia.utils.stringLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    private lateinit var sharedPrefLiveData: SharedPreferenceLiveData<String>

    private lateinit var preferenceManager: PreferenceManager

    private fun initialize() {
        setSharedPreferences()
        setTheme()
        setThemeObserver()
        setContentView(R.layout.activity_main)
    }

    private fun setSharedPreferences() {
        preferenceManager = PreferenceManager.getInstance(this)
        val sharedPref = preferenceManager.sharedPreferences
        sharedPrefLiveData = sharedPref.stringLiveData(SharedPreferenceLiveData.CURRENT_THEME, preferenceManager.currentTheme)
    }

    private fun setThemeObserver() {
        sharedPrefLiveData.observe(this, Observer {
            val currentTheme = preferenceManager.currentTheme
            if (it != currentTheme) {
                preferenceManager.currentTheme = it
                recreate()
            }
        })
    }

    private fun setTheme() {
        when (preferenceManager.currentTheme) {
            SharedPreferenceLiveData.GRYFFINDOR -> setTheme(R.style.Gryffindor)
            SharedPreferenceLiveData.SLYTHERIN -> setTheme(R.style.Slytherin)
            SharedPreferenceLiveData.RAVENCLAW -> setTheme(R.style.Ravenclaw)
            SharedPreferenceLiveData.HUFFLEPUFF -> setTheme(R.style.Hufflepuff)
        }
    }
}