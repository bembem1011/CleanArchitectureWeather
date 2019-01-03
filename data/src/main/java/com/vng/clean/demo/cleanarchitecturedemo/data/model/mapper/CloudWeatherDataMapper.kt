package com.vng.clean.demo.cleanarchitecturedemo.data.model.mapper

import android.net.Uri
import com.vng.clean.demo.cleanarchitecturedemo.data.BuildConfig
import com.vng.clean.demo.cleanarchitecturedemo.data.model.CloudWeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo

class CloudWeatherDataMapper: Mapper<CloudWeatherInfo.Info, WeatherInfo> {

    companion object {
        private const val ICON_SEGMENT_IMG = "img"
        private const val ICON_SEGMENT_W = "w"
    }

    override fun transform(weatherInfo: CloudWeatherInfo.Info): WeatherInfo {
        val iconUrl = Uri.parse(BuildConfig.OPEN_WEATHER).buildUpon()
                .appendPath(ICON_SEGMENT_IMG)
                .appendPath(ICON_SEGMENT_W)
                .appendPath(weatherInfo.weathers[0]._icon)
                .build()
                .toString()
        return WeatherInfo(weatherInfo.dtime,
                weatherInfo.main.temperature,
                weatherInfo.main.minTemperature,
                weatherInfo.main.maxTemperature,
                weatherInfo.main.humidity,
                weatherInfo.weathers[0].description,
                iconUrl)
    }
}