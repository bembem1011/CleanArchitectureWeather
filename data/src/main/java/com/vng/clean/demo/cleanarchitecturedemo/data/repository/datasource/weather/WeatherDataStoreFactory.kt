package com.vng.clean.demo.cleanarchitecturedemo.data.repository.datasource.weather

import com.vng.clean.demo.cleanarchitecturedemo.data.net.service.ForecastService

class WeatherDataStoreFactory(private val forecastService: ForecastService) {
    fun createCloudWeatherDataStore(): WeatherDataStore {
        return CloudWeatherDataStore(forecastService)
    }
}