package com.matthew.carvalhodagenais.modernaconverter.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.matthew.carvalhodagenais.modernaconverter.MainActivity
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.databinding.FragmentConverterBinding
import com.matthew.carvalhodagenais.modernaconverter.utils.NetworkConnectivityUtil
import com.matthew.carvalhodagenais.modernaconverter.viewmodels.ConvertViewModel
import kotlinx.android.synthetic.main.fragment_converter.*

class ConvertFragment: Fragment() {

    private lateinit var viewModel: ConvertViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).convertViewModel
        val binding = DataBindingUtil.inflate<FragmentConverterBinding>(
            inflater, R.layout.fragment_converter, container, false
        ).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        submit_button.setOnClickListener(submitButtonOnClick)
    }

    /**
     * OnClickListener for the "calculate currency" button
     * Takes the base currency and the amount and converts it to the selected "to" currency
     */
    private val submitButtonOnClick = View.OnClickListener {
        if (currency_edit_text.text.toString().trim() != "") {
            submit_button.isClickable = false


            // Close the keyboard
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)

            // Convert the currency and set the TextViews with the proper strings
            viewModel.getRates(currency_button_left.text.toString()).observe(this, Observer {
                if (it != null) {
                    base_text.text =
                        String.format(resources.getString(R.string.base_convertfragment),
                            it.base,
                            currency_edit_text.text.toString().toBigDecimal())
                    conversion_text.text =
                        String.format(resources.getString(R.string.conversion_convertfragment),
                            currency_button_right.text.toString(),
                            (currency_edit_text.text.toString().toBigDecimal())
                                    * (it.rates[currency_button_right.text].toString().toBigDecimal()))
                    content_motion_layout.setTransition(R.id.start_to_end)
                    content_motion_layout.transitionToEnd()
                } else { // 1) no internet 2) no response
                    if (NetworkConnectivityUtil().hasNetworkConnection(requireContext())) {
                        Snackbar.make(
                            content_motion_layout,
                            resources.getString(R.string.no_connection_snackbar),
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            content_motion_layout,
                            resources.getString(R.string.no_api_snackbar),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                submit_button.isClickable = true
            })
        } else { // No amount put in the EditText
            Snackbar.make(
                content_motion_layout,
                resources.getString(R.string.no_amount_snackbar),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}