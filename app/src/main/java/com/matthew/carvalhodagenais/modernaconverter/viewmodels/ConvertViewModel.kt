package com.matthew.carvalhodagenais.modernaconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

}