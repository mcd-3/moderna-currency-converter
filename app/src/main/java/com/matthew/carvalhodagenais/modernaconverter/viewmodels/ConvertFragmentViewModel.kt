package com.matthew.carvalhodagenais.modernaconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matthew.carvalhodagenais.modernaconverter.data.entities.ExchangeRate
import com.matthew.carvalhodagenais.modernaconverter.data.repositories.ExchangeRateRepository

class ConvertFragmentViewModel: ViewModel() {

    val repo = ExchangeRateRepository()

    fun getRates(base: String): LiveData<ExchangeRate> {
        return repo.fetchRates(base)
    }

}