package com.vng.clean.demo.cleanarchitecturedemo.domain.model

class WeatherInfo(val dtime: Long,
                  val tempreature: Double,
                  val minTemperature: Double,
                  val maxTemperature: Double,
                  val humidity: Int,
                  val _description: String,
                  val iconUrl: String)