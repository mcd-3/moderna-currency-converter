package com.matthew.carvalhodagenais.modernaconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency
import com.matthew.carvalhodagenais.modernaconverter.data.entities.ExchangeRate
import com.matthew.carvalhodagenais.modernaconverter.data.repositories.ExchangeRateRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConvertViewModel: ViewModel() {

    private val repo = ExchangeRateRepository()

    fun getRates(base: String): LiveData<ExchangeRate> {
        return repo.fetchRates(base)
    }

    fun getTodayAsDate(): String {
        val todayUnformatted = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return todayUnformatted.format(formatter)
    }

    fun swapCurrencies(cur1: Currency, cur2: Currency) {
        val tempCurrency = Currency("", 0)
        tempCurrency.copy(cur1)
        cur1.copy(cur2)
        cur2.copy(tempCurrency)
    }

}