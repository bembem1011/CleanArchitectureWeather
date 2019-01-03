package com.vng.clean.demo.cleanarchitecturedemo.model

data class WeatherInfoModel(val dtime: Long,
                            val tempreature: Double,
                            val minTemperature: Double,
                            val maxTemperature: Double,
                            val humidity: Int,
                            val description: String,
                            val iconUrl: String)