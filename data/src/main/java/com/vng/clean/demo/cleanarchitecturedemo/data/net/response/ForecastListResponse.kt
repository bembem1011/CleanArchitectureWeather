package com.vng.clean.demo.cleanarchitecturedemo.data.net.response

import com.google.gson.annotations.SerializedName
import com.vng.clean.demo.cleanarchitecturedemo.data.model.CloudWeatherInfo

class ForecastListResponse(@SerializedName("cod") val code: Int,
                           @SerializedName("message") val message: String,
                           @SerializedName("list") val list: List<CloudWeatherInfo.Info>)