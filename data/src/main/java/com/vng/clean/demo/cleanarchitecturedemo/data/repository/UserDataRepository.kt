package com.vng.clean.demo.cleanarchitecturedemo.data.repository

import com.vng.clean.demo.cleanarchitecturedemo.data.BuildConfig
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.UserRepository

class UserDataRepository: UserRepository {
    override fun getWeatherApiKey() = BuildConfig.OPEN_WEATHER_API_KEY
}