package com.example.preferencesControl.ui.activity1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.preferencesControl.R
import com.example.preferencesControl.settings.Preferences
import com.example.preferencesControl.ui.activity2.Activity2
import kotlinx.android.synthetic.main.activity_main.*
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {
    private lateinit var preferences: Preferences
    private lateinit var layout: CoordinatorLayout
    private var defaultColor = R.color.defaultColor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById(R.id.main_layout)
        preferences = Preferences(this, defaultColor)

        initialize()

        redo.setOnClickListener {
            goToActivity2()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                openColorPicker()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initialize() {
        preferences.loadData()
        preferences.updateBackground(layout)
    }

    private fun openColorPicker() {
        val colorPicker =
            AmbilWarnaDialog(this, preferences.color, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}
                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    preferences.color = color
                    preferences.saveData()
                    preferences.updateBackground(layout)
                }
            })
        colorPicker.show()
    }

    private fun goToActivity2() {
        val intent = Intent(this, Activity2::class.java)
        startActivity(intent)
    }
}
