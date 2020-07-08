package com.matthew.carvalhodagenais.modernaconverter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency
import kotlinx.android.synthetic.main.spinner_currency.view.*

class CurrencySpinnerAdapter(context: Context, currencies: Array<Currency>): ArrayAdapter<Currency>(context, 0, currencies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val newView = LayoutInflater.from(context).inflate(
            R.layout.spinner_currency, parent, false
        )

        val currency = getItem(position)
        if (currency != null) {
            newView.spinner_layout.text = currency.code
            newView.spinner_layout.setCompoundDrawablesRelative(currency.image, null, null, null)
        }

        return newView
    }



}