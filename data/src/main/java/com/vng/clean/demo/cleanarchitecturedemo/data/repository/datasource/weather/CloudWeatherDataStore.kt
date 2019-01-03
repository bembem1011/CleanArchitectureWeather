package com.vng.clean.demo.cleanarchitecturedemo.data.repository.datasource.weather

import com.vng.clean.demo.cleanarchitecturedemo.data.exception.WeatherListException
import com.vng.clean.demo.cleanarchitecturedemo.data.model.CloudWeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.data.net.service.ForecastService
import io.reactivex.Observable

class CloudWeatherDataStore(private val forecastService: ForecastService): WeatherDataStore {

    override fun getWeatherList(latitude: Double, longitude: Double, apiKey: String): Observable<List<CloudWeatherInfo.Info>> {
        return forecastService.fetchForecast5Day3H(latitude, longitude, apiKey).map {
            if (it.code != 200) {
                throw WeatherListException(it.message)
            }
            it.list
        }
    }
}