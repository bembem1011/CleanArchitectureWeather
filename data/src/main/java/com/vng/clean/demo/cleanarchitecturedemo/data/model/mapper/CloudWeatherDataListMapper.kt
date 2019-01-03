package com.vng.clean.demo.cleanarchitecturedemo.data.model.mapper

import com.vng.clean.demo.cleanarchitecturedemo.data.model.CloudWeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo

class CloudWeatherDataListMapper(private val mapper: Mapper<CloudWeatherInfo.Info, WeatherInfo>)
    : Mapper<List<CloudWeatherInfo.Info>, List<WeatherInfo>> {

    override fun transform(weatherInfos: List<CloudWeatherInfo.Info>): List<WeatherInfo> {
        val resultList = mutableListOf<WeatherInfo>()
        for (weatherInfo in weatherInfos) {
            resultList.add(mapper.transform(weatherInfo))
        }
        return resultList
    }
}