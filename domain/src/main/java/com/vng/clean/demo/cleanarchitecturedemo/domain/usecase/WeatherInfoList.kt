package com.vng.clean.demo.cleanarchitecturedemo.domain.usecase

import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.PostExecutionThread
import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.ProcessThreadExecutor
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.GetListInputParams

abstract class WeatherInfoList(processThreadExecutor: ProcessThreadExecutor,
                               postExecutionThread: PostExecutionThread)
    : UseCase<List<WeatherInfo>, GetListInputParams>(processThreadExecutor, postExecutionThread)