package com.matthew.carvalhodagenais.modernaconverter.listeners

import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency

class SpinnerListenerGenerator {

    /**
     * OnItemSelectedListener for the currency spinners
     *
     * @param fromCurrency Currency?
     * @param toCurrency Currency?
     * @param spinnerOne Spinner
     * @param spinnerTwo Spinner
     * @return AdapterView.OnItemSelectedListener
     */
    fun generateCurrencyListener(
        fromCurrency: Currency?,
        toCurrency: Currency?,
        spinnerOne: Spinner,
        spinnerTwo: Spinner
    ): AdapterView.OnItemSelectedListener {
        return object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                fromCurrency?.copy(spinnerOne.selectedItem as Currency)
                toCurrency?.copy(spinnerTwo.selectedItem as Currency)
                (p0!!.getChildAt(0) as TextView).setTextColor(Color.BLACK)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

}