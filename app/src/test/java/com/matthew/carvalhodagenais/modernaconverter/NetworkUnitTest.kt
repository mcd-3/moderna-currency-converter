package com.matthew.carvalhodagenais.modernaconverter

import androidx.lifecycle.LiveData
import com.matthew.carvalhodagenais.modernaconverter.data.entities.ExchangeRate
import com.matthew.carvalhodagenais.modernaconverter.viewmodels.ConvertViewModel
import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests to see if network connectivity is working as intended
 */
class NetworkUnitTest {

    /**
     * Check to see if the Exchange Rate server is returning data
     */
    @Test
    fun isNetworkAPIWorking() {
        // Need to wait for LiveData
//        val viewModel: ConvertViewModel = ConvertViewModel()
//        val baseCurrency: String = "EUR"
//        val rates: LiveData<ExchangeRate> = viewModel.getRates(baseCurrency)
//        assert(rates.value != null)
//        System.out.println("Server is returning data\n${rates.value}")
    }
}