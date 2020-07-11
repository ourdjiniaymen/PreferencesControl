package com.example.tdm2_td1_exo1.settingActivity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tdm2_td1_exo1.R
import com.example.tdm2_td1_exo1.activity1.MainActivity
import com.example.tdm2_td1_exo1.activity2.Activity2
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {
companion object{
    private const val SHARED_PREFS  = "sharedPrefs"
    private const val BACKGROUNDCOLOR = "color"
    private lateinit var color: String
    fun loadData(activity : Activity){
        val sharedPreferences : SharedPreferences? = activity.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        color = sharedPreferences?.getString(BACKGROUNDCOLOR,"#ffffff")!!
    }
    fun updateBackground(layout: ConstraintLayout){
        layout.setBackgroundColor(Color.parseColor(color))
    }
}
    private lateinit var layout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        layout = findViewById(R.id.settings_activity)
        loadData(this)
        updateBackground(layout)
        var intent = intent
        val extras = intent.extras
        var activity = 0
        if (extras!!.containsKey("activity")) {
            activity = intent.getIntExtra("activity",0)
        }
        when(activity){
            1 -> intent = Intent(this, MainActivity::class.java)
            2 -> intent = Intent(this, Activity2::class.java)
        }
        return_activity.setOnClickListener {
            startActivity(intent)
        }

        colorPickerView.setColorListener { colorEnvelope ->
            color = "#"+colorEnvelope.colorHtml
          
        }
        show.setOnClickListener {
            updateBackground(layout)
        }

        cancel.setOnClickListener {
            startActivity(intent)
        }
        done.setOnClickListener {
            saveData()
            updateBackground(layout)
        }
    }
    private fun saveData(){
        val sharedPreferences : SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor: SharedPreferences.Editor  = sharedPreferences.edit()
        editor.putString(BACKGROUNDCOLOR, color)
        editor.apply()
    }

}
