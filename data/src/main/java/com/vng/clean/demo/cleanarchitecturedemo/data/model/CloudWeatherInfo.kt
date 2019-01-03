package com.vng.clean.demo.cleanarchitecturedemo.data.model

import com.google.gson.annotations.SerializedName

class CloudWeatherInfo {
    class Info(@SerializedName("time") val dtime: Long,
               @SerializedName("main") val main: Main,
               @SerializedName("weather") val weathers: List<Weather>,
               @SerializedName("wind") val wind: Wind)

    class Main(@SerializedName("temp") val temperature: Double,
               @SerializedName("temp_min") val minTemperature: Double,
               @SerializedName("temp_max") val maxTemperature: Double,
               @SerializedName("humidity") val humidity: Int)

    class Weather(@SerializedName("description") val description: String,
                  @SerializedName("icon") val _icon: String)

    class Wind(@SerializedName("wind") val wind: Double)
}