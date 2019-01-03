package com.vng.clean.demo.cleanarchitecturedemo.domain.usecase

import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.PostExecutionThread
import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.ProcessThreadExecutor
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.GetListInputParams
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.UserRepository
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.WeatherRepository
import io.reactivex.Observable

class WeatherInfoListImpl(private val userRepository: UserRepository,
                      private val weatherRepository: WeatherRepository,
                      processThreadExecutor: ProcessThreadExecutor,
                      postExecutionThread: PostExecutionThread): WeatherInfoList(processThreadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: GetListInputParams): Observable<List<WeatherInfo>> {
        return weatherRepository.getWeatherList(params.latitude, params.longitue, userRepository.getWeatherApiKey())
    }
}