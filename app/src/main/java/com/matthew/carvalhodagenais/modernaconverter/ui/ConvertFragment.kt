package com.matthew.carvalhodagenais.modernaconverter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
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
        if (currency_edit_text.text.toString().trim() != "") {

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)

            submit_button.isClickable = false

            (activity as MainActivity).convertViewModel.getRates(currency_button_left.text.toString()).observe(this, Observer {
                if (it != null) {
                    base_text.text = "${it.base} ${currency_edit_text.text}"
                    conversion_text.text =
                        "${currency_button_right.text} ${(currency_edit_text.text.toString().toDouble()) * (it.rates[currency_button_right.text].toString().toDouble())}"
                    content_motion_layout.setTransition(R.id.start_to_end)
                    content_motion_layout.transitionToEnd()
                } else {
                    // 2 things: 1) no internet 2) no response
                }
                submit_button.isClickable = true
            })
        } else {
            Snackbar.make(content_motion_layout, "Enter an amount", Snackbar.LENGTH_LONG).show()
        }
    }
}