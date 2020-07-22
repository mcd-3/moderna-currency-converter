package com.matthew.carvalhodagenais.modernaconverter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.matthew.carvalhodagenais.modernaconverter.MainActivity
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.adapters.CurrencySpinnerAdapter
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency
import com.matthew.carvalhodagenais.modernaconverter.databinding.FragmentConverterBinding
import com.matthew.carvalhodagenais.modernaconverter.listeners.ButtonListenerGenerator
import com.matthew.carvalhodagenais.modernaconverter.listeners.SpinnerListenerGenerator
import com.matthew.carvalhodagenais.modernaconverter.utils.CurrencyArrayUtil
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

        // Set adapter and listener for button
        val blg = ButtonListenerGenerator()
        val buttonListener = blg.generateConvertListener(
            requireContext(),
            content_motion_layout,
            currency_edit_text,
            submit_button,
            base_text,
            conversion_text,
            swap_image_view,
            viewModel,
            this,
            fromCurrency,
            toCurrency
        )
        submit_button.setOnClickListener(buttonListener)

        // Set adapter, selection, and listeners for spinners
        spinnerAdapter = CurrencySpinnerAdapter(requireContext(), currencies)
        val slg = SpinnerListenerGenerator()
        val spinnerListener = slg.generateCurrencyListener(
            fromCurrency, toCurrency, currency_button_left, currency_button_right
        )
        currency_button_left.adapter = spinnerAdapter
        currency_button_left.setSelection(spinnerAdapter.getPosition(fromCurrency))
        currency_button_left.onItemSelectedListener = spinnerListener
        currency_button_right.adapter = spinnerAdapter
        currency_button_right.setSelection(spinnerAdapter.getPosition(toCurrency))
        currency_button_right.onItemSelectedListener = spinnerListener
    }

}