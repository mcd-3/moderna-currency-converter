package com.matthew.carvalhodagenais.modernaconverter.data.entities

data class ExchangeRate(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
) {
    fun convert(newCur: String): Double? {
        return rates[newCur]
    }
}