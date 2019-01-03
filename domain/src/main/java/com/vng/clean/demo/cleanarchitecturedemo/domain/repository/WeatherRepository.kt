package com.vng.clean.demo.cleanarchitecturedemo.domain.repository

import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import io.reactivex.Observable

interface WeatherRepository {
    fun getWeatherList(latitude: Double, longitude: Double, apiKey: String): Observable<List<WeatherInfo>>
}