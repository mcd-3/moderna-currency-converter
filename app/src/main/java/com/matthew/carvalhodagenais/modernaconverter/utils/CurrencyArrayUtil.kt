package com.matthew.carvalhodagenais.modernaconverter.utils

import android.content.Context
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency

class CurrencyArrayUtil {

    fun getAllSupportedCurrencies(context: Context): Array<Currency> {
        val codeArray: Array<String> = context.resources.getStringArray(R.array.currency_codes)
        return arrayOf<Currency>(
            Currency(codeArray[0], R.drawable.ic_aud),
            Currency(codeArray[1], R.drawable.ic_bgn),
            Currency(codeArray[2], R.drawable.ic_brl),
            Currency(codeArray[3], R.drawable.ic_cad),
            Currency(codeArray[4], R.drawable.ic_chf),
            Currency(codeArray[5], R.drawable.ic_cny),
            Currency(codeArray[6], R.drawable.ic_czk),
            Currency(codeArray[7], R.drawable.ic_dkk),
            Currency(codeArray[8], R.drawable.ic_eur),
            Currency(codeArray[9], R.drawable.ic_gbp),
            Currency(codeArray[10], R.drawable.ic_hkd),
            Currency(codeArray[11], R.drawable.ic_hrk),
            Currency(codeArray[12], R.drawable.ic_huf),
            Currency(codeArray[13], R.drawable.ic_idr),
            Currency(codeArray[14], R.drawable.ic_ils),
            Currency(codeArray[15], R.drawable.ic_inr),
            Currency(codeArray[16], R.drawable.ic_isk),
            Currency(codeArray[17], R.drawable.ic_jpy),
            Currency(codeArray[18], R.drawable.ic_krw),
            Currency(codeArray[19], R.drawable.ic_mxn),
            Currency(codeArray[20], R.drawable.ic_myr),
            Currency(codeArray[21], R.drawable.ic_nok),
            Currency(codeArray[22], R.drawable.ic_nzd),
            Currency(codeArray[23], R.drawable.ic_php),
            Currency(codeArray[24], R.drawable.ic_pln),
            Currency(codeArray[25], R.drawable.ic_ron),
            Currency(codeArray[26], R.drawable.ic_rub),
            Currency(codeArray[27], R.drawable.ic_sek),
            Currency(codeArray[28], R.drawable.ic_sgd),
            Currency(codeArray[29], R.drawable.ic_thb),
            Currency(codeArray[30], R.drawable.ic_try),
            Currency(codeArray[31], R.drawable.ic_usd),
            Currency(codeArray[32], R.drawable.ic_zar)
        )
    }

}