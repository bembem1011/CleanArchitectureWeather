package com.vng.clean.demo.cleanarchitecturedemo.model.mapper

import com.vng.clean.demo.cleanarchitecturedemo.DataMapper
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.model.WeatherInfoModel

data class WeatherInfoListMapper(private val weatherInfoMapper: WeatherInfoMapper): DataMapper<List<WeatherInfo>, List<WeatherInfoModel>> {
    override fun transform(inputList: List<WeatherInfo>): List<WeatherInfoModel> {
        val resultList = mutableListOf<WeatherInfoModel>()
        for (weatherInfo in inputList) {
            resultList.add(weatherInfoMapper.transform(weatherInfo))
        }
        return resultList
    }
}