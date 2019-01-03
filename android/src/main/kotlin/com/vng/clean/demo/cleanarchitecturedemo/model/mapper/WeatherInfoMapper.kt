package com.vng.clean.demo.cleanarchitecturedemo.model.mapper

import com.vng.clean.demo.cleanarchitecturedemo.DataMapper
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.model.WeatherInfoModel

public class WeatherInfoMapper: DataMapper<WeatherInfo, WeatherInfoModel> {
    override fun transform(weatherInfo: WeatherInfo): WeatherInfoModel {
        return WeatherInfoModel(weatherInfo.dtime,
                                weatherInfo.tempreature,
                                weatherInfo.minTemperature,
                                weatherInfo.maxTemperature,
                                weatherInfo.humidity,
                                weatherInfo._description,
                                weatherInfo.iconUrl)
    }
}