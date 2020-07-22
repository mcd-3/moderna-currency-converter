package com.matthew.carvalhodagenais.modernaconverter.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.matthew.carvalhodagenais.modernaconverter.MainActivity
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.adapters.CurrencySpinnerAdapter
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency
import com.matthew.carvalhodagenais.modernaconverter.databinding.FragmentConverterBinding
import com.matthew.carvalhodagenais.modernaconverter.listeners.SpinnerListenerGenerator
import com.matthew.carvalhodagenais.modernaconverter.utils.CurrencyArrayUtil
import com.matthew.carvalhodagenais.modernaconverter.utils.ImageViewSwapperUtil
import com.matthew.carvalhodagenais.modernaconverter.utils.NetworkConnectivityUtil
import com.matthew.carvalhodagenais.modernaconverter.viewmodels.ConvertViewModel
import kotlinx.android.synthetic.main.fragment_converter.*

class ConvertFragment: Fragment() {

    private lateinit var viewModel: ConvertViewModel
    private lateinit var currencies: Array<Currency>
    private lateinit var spinnerAdapter: CurrencySpinnerAdapter

    private var fromCurrency: Currency? = null
    private var toCurrency: Currency? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).convertViewModel
        currencies = CurrencyArrayUtil().getAllSupportedCurrencies(requireContext())
        fromCurrency = currencies[CurrencyArrayUtil.USD_INDEX]
        toCurrency = currencies[CurrencyArrayUtil.EUR_INDEX]
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
        spinnerAdapter = CurrencySpinnerAdapter(requireContext(), currencies)

        // Set adapter, selection, and listeners for spinners
        val slg = SpinnerListenerGenerator()
        val listener = slg.generateCurrencyListener(
            fromCurrency, toCurrency, currency_button_left, currency_button_right
        )
        currency_button_left.adapter = spinnerAdapter
        currency_button_left.setSelection(spinnerAdapter.getPosition(fromCurrency))
        currency_button_left.onItemSelectedListener = listener
        currency_button_right.adapter = spinnerAdapter
        currency_button_right.setSelection(spinnerAdapter.getPosition(toCurrency))
        currency_button_right.onItemSelectedListener = listener
    }

    /**
     * OnClickListener for the "calculate currency" button
     * Takes the base currency and the amount and converts it to the selected "to" currency
     */
    private val submitButtonOnClick = View.OnClickListener {
        if (currency_edit_text.text.toString().trim() != "") {
            if (fromCurrency != toCurrency) {
                submit_button.isClickable = false

                ImageViewSwapperUtil(requireContext()).swapToLoading(swap_image_view)

                // Close the keyboard
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)

                // Convert the currency and set the TextViews with the proper strings
                viewModel.getRates(fromCurrency!!.code).observe(this, Observer {
                    if (it != null) {
                        base_text.text =
                            String.format(resources.getString(R.string.base_convertfragment),
                                it.base,
                                currency_edit_text.text.toString().toBigDecimal())
                        conversion_text.text =
                            String.format(resources.getString(R.string.conversion_convertfragment),
                                toCurrency!!.code,
                                (currency_edit_text.text.toString().toBigDecimal())
                                        * (it.rates[toCurrency!!.code].toString().toBigDecimal()))
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
                    ImageViewSwapperUtil(requireContext()).swapToStatic(swap_image_view)
                })
            } else {
                Snackbar.make(
                    content_motion_layout,
                    "Pick 2 different currencies",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        } else { // No amount put in the EditText
            Snackbar.make(
                content_motion_layout,
                resources.getString(R.string.no_amount_snackbar),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

}