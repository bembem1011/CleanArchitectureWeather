package com.vng.clean.demo.cleanarchitecturedemo.data.repository.datasource.weather

import com.vng.clean.demo.cleanarchitecturedemo.data.model.CloudWeatherInfo
import io.reactivex.Observable

interface WeatherDataStore {
    fun getWeatherList(latitude: Double, longitude: Double, apiKey: String): Observable<List<CloudWeatherInfo.Info>>
}