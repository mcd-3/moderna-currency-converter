package com.matthew.carvalhodagenais.modernaconverter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.matthew.carvalhodagenais.modernaconverter.MainActivity
import com.matthew.carvalhodagenais.modernaconverter.R
import kotlinx.android.synthetic.main.fragment_converter.*

class ConvertFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        submit_button.setOnClickListener(submitButtonOnClick)
    }

    private val submitButtonOnClick = View.OnClickListener {
        submit_button.isClickable = false
        (activity as MainActivity).convertViewModel.getRates(currency_button_left.text.toString()).observe(this, Observer {
            if (it != null) {
                Log.e("THINGY", it.rates[currency_button_right.text.toString()].toString())
                submit_button.isClickable = true
                content_motion_layout.setTransition(R.id.start_to_end)
                content_motion_layout.transitionToEnd()
            }
        })
    }
}