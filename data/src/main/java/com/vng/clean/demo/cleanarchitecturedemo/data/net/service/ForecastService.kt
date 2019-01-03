package com.vng.clean.demo.cleanarchitecturedemo.data.net.service

import com.vng.clean.demo.cleanarchitecturedemo.data.net.response.ForecastListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("data/2.5/forecast")
    fun fetchForecast5Day3H(@Query("lat") latitude: Double,
                            @Query("lon") longitude: Double,
                            @Query("appid") appId: String): Observable<ForecastListResponse>
}