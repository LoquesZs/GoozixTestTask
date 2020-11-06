package com.example.goozixtesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goozixtesttask.ui.trendings.TrendingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TrendingsFragment.newInstance())
                    .commitNow()
        }
    }
}