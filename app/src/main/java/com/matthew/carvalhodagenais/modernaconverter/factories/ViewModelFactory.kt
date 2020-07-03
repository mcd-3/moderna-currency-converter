package com.matthew.carvalhodagenais.modernaconverter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.matthew.carvalhodagenais.modernaconverter.viewmodels.ConvertViewModel

class ViewModelFactory(): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass){
        when {
            isAssignableFrom(ConvertViewModel::class.java) -> ConvertViewModel() as T
            else -> IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}") as T
        }
    }

}