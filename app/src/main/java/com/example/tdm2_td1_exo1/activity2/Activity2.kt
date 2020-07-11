package com.example.tdm2_td1_exo1.activity2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tdm2_td1_exo1.R
import com.example.tdm2_td1_exo1.activity1.MainActivity
import com.example.tdm2_td1_exo1.settingActivity.SettingsActivity
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        layout = findViewById<ConstraintLayout>(R.id.layout2)
        SettingsActivity.loadData(this)
        SettingsActivity.updateBackground(layout)
        val intent = Intent(this, MainActivity::class.java)
        undo.setOnClickListener {
            startActivity(intent)
        }
        val intent2 =  Intent(this, SettingsActivity::class.java)
        settings_icon.setOnClickListener {
            intent2.putExtra("activity",2)
            startActivity(intent2)
        }
    }
}
