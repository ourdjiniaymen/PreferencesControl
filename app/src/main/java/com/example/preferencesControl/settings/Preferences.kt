package com.example.preferencesControl.settings

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.preferencesControl.R

class Preferences(private val activity: Activity, var color: Int) {

    companion object {
        private const val SHARED_PREFS = "sharedPrefs"
        private const val BACKGROUND_COLOR = "color"
    }


    fun loadData() {
        val sharedPreferences: SharedPreferences? =
            activity.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        color = sharedPreferences?.getInt(BACKGROUND_COLOR,R.color.defaultColor)!!
    }

    fun updateBackground(layout: CoordinatorLayout) {
        layout.setBackgroundColor(color)
    }

    fun saveData() {
        val sharedPreferences: SharedPreferences =
            activity.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(BACKGROUND_COLOR, color)
        editor.apply()
    }

}