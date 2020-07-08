package com.matthew.carvalhodagenais.modernaconverter.utils

import android.content.Context
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency

class CurrencyArrayUtil {

    fun getAllSupportedCurrencies(context: Context): Array<Currency> {
        val codeArray: Array<String> = context.resources.getStringArray(R.array.currency_codes)
        return arrayOf<Currency>(
            Currency(codeArray[0], context.getDrawable(R.drawable.ic_aud)!!),
            Currency(codeArray[1], context.getDrawable(R.drawable.ic_bgn)!!),
            Currency(codeArray[2], context.getDrawable(R.drawable.ic_brl)!!),
            Currency(codeArray[3], context.getDrawable(R.drawable.ic_cad)!!),
            Currency(codeArray[4], context.getDrawable(R.drawable.ic_chf)!!),
            Currency(codeArray[5], context.getDrawable(R.drawable.ic_cny)!!),
            Currency(codeArray[6], context.getDrawable(R.drawable.ic_czk)!!),
            Currency(codeArray[7], context.getDrawable(R.drawable.ic_dkk)!!),
            Currency(codeArray[8], context.getDrawable(R.drawable.ic_eur)!!),
            Currency(codeArray[9], context.getDrawable(R.drawable.ic_gbp)!!),
            Currency(codeArray[10], context.getDrawable(R.drawable.ic_hkd)!!),
            Currency(codeArray[11], context.getDrawable(R.drawable.ic_hrk)!!),
            Currency(codeArray[12], context.getDrawable(R.drawable.ic_huf)!!),
            Currency(codeArray[13], context.getDrawable(R.drawable.ic_idr)!!),
            Currency(codeArray[14], context.getDrawable(R.drawable.ic_ils)!!),
            Currency(codeArray[15], context.getDrawable(R.drawable.ic_inr)!!),
            Currency(codeArray[16], context.getDrawable(R.drawable.ic_isk)!!),
            Currency(codeArray[17], context.getDrawable(R.drawable.ic_jpy)!!),
            Currency(codeArray[18], context.getDrawable(R.drawable.ic_krw)!!),
            Currency(codeArray[18], context.getDrawable(R.drawable.ic_mxn)!!),
            Currency(codeArray[19], context.getDrawable(R.drawable.ic_myr)!!),
            Currency(codeArray[20], context.getDrawable(R.drawable.ic_nok)!!),
            Currency(codeArray[21], context.getDrawable(R.drawable.ic_nzd)!!),
            Currency(codeArray[22], context.getDrawable(R.drawable.ic_php)!!),
            Currency(codeArray[23], context.getDrawable(R.drawable.ic_pln)!!),
            Currency(codeArray[24], context.getDrawable(R.drawable.ic_ron)!!),
            Currency(codeArray[25], context.getDrawable(R.drawable.ic_rub)!!),
            Currency(codeArray[26], context.getDrawable(R.drawable.ic_sek)!!),
            Currency(codeArray[27], context.getDrawable(R.drawable.ic_sgd)!!),
            Currency(codeArray[28], context.getDrawable(R.drawable.ic_thb)!!),
            Currency(codeArray[29], context.getDrawable(R.drawable.ic_try)!!),
            Currency(codeArray[30], context.getDrawable(R.drawable.ic_usd)!!),
            Currency(codeArray[31], context.getDrawable(R.drawable.ic_zar)!!)
        )
    }

}