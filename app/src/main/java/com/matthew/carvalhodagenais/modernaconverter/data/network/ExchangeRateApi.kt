package com.matthew.carvalhodagenais.modernaconverter.data.network

import com.matthew.carvalhodagenais.modernaconverter.data.entities.ExchangeRate
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApi {
    @GET("latest")
    fun getRates(): Call<ExchangeRate>

    @GET("latest")
    fun getRates(@Query("base") baseCurrency: String): Call<ExchangeRate>

    companion object {
        operator fun invoke(): ExchangeRateApi {
            return Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExchangeRateApi::class.java)
        }
    }
}