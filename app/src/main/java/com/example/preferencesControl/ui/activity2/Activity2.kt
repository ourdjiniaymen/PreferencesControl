package com.example.preferencesControl.ui.activity2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.preferencesControl.R
import com.example.preferencesControl.settings.Preferences
import com.example.preferencesControl.ui.activity1.MainActivity
import kotlinx.android.synthetic.main.activity_2.*
import yuku.ambilwarna.AmbilWarnaDialog

class Activity2 : AppCompatActivity() {
    private lateinit var preferences: Preferences
    private lateinit var layout: CoordinatorLayout
    private var defaultColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        layout = findViewById(R.id.layout_2)
        defaultColor = ContextCompat.getColor(this, R.color.colorPrimary)
        preferences = Preferences(this, defaultColor)

        initialize()

        undo.setOnClickListener {
            goToMainActivity()
        }

    }

    private fun initialize() {
        preferences.loadData()
        preferences.updateBackground(layout)
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

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
