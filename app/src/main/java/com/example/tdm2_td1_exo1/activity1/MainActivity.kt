package com.example.tdm2_td1_exo1.activity1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tdm2_td1_exo1.R
import com.example.tdm2_td1_exo1.activity2.Activity2
import com.example.tdm2_td1_exo1.settingActivity.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layout = findViewById(R.id.main_layout)
        SettingsActivity.loadData(this)
        SettingsActivity.updateBackground(layout)
        val intent = Intent(this, Activity2::class.java)
        redo.setOnClickListener {
            startActivity(intent)
        }
        val intent2 =  Intent(this, SettingsActivity::class.java)
        settings_icon.setOnClickListener {
            intent2.putExtra("activity",1)
            startActivity(intent2)
        }
    }
}
