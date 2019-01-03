package com.vng.clean.demo.cleanarchitecturedemo.data.repository

import com.vng.clean.demo.cleanarchitecturedemo.data.model.mapper.CloudWeatherDataListMapper
import com.vng.clean.demo.cleanarchitecturedemo.data.repository.datasource.weather.WeatherDataStoreFactory
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.WeatherRepository
import io.reactivex.Observable

class WeatherDataRepository(private val weatherDataStoreFactory: WeatherDataStoreFactory,
                            private val weatherDataListMapper: CloudWeatherDataListMapper)
    : WeatherRepository {

    override fun getWeatherList(latitude: Double, longitude: Double, apiKey: String): Observable<List<WeatherInfo>>
            = weatherDataStoreFactory
                .createCloudWeatherDataStore().getWeatherList(latitude, longitude, apiKey)
                .map(weatherDataListMapper::transform)

}