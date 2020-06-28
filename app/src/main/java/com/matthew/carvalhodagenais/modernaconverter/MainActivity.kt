package com.matthew.carvalhodagenais.modernaconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matthew.carvalhodagenais.modernaconverter.ui.ConvertFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(appbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction().replace(R.id.parent_fragment, ConvertFragment()).commit()
    }
}