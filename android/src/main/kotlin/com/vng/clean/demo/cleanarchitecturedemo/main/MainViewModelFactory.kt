package com.vng.clean.demo.cleanarchitecturedemo.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vng.clean.demo.cleanarchitecturedemo.domain.usecase.WeatherInfoList
import com.vng.clean.demo.cleanarchitecturedemo.model.mapper.WeatherInfoListMapper

class MainViewModelFactory(private val weatherListUseCase: WeatherInfoList,
                           private val weatherInfoListMapper: WeatherInfoListMapper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(weatherListUseCase, weatherInfoListMapper) as T
    }
}