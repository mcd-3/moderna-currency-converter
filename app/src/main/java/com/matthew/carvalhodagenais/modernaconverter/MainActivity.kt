package com.matthew.carvalhodagenais.modernaconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.matthew.carvalhodagenais.modernaconverter.factories.ViewModelFactory
import com.matthew.carvalhodagenais.modernaconverter.ui.ConvertFragment
import com.matthew.carvalhodagenais.modernaconverter.viewmodels.ConvertViewModel
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : AppCompatActivity() {

    lateinit var convertViewModel: ConvertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(ConvertViewModel::class.java)

        setSupportActionBar(appbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction().replace(R.id.parent_fragment, ConvertFragment()).commit()
    }


}