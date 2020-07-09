package com.matthew.carvalhodagenais.modernaconverter.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matthew.carvalhodagenais.modernaconverter.data.entities.ExchangeRate
import com.matthew.carvalhodagenais.modernaconverter.data.network.ExchangeRateApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeRateRepository {

    fun fetchRates(base: String): LiveData<ExchangeRate> {
        val responseData = MutableLiveData<ExchangeRate>()
        ExchangeRateApi()
            .getRates(base)
            .enqueue(object: Callback<ExchangeRate> {
                override fun onResponse(
                    call: Call<ExchangeRate>?,
                    response: Response<ExchangeRate>?
                ) {
                    if (response!!.isSuccessful) {
                        responseData.value = ExchangeRate(
                            response.body().base,
                            response.body().date,
                            response.body().rates
                        )
                    } else {
                        responseData.value = null
                    }
                }

                override fun onFailure(call: Call<ExchangeRate>?, t: Throwable?) {
                    responseData.value = null
                }
            })
        return responseData
    }
}