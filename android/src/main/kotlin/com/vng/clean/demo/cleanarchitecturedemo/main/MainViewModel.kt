package com.vng.clean.demo.cleanarchitecturedemo.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.vng.clean.demo.cleanarchitecturedemo.Resource
import com.vng.clean.demo.cleanarchitecturedemo.domain.model.WeatherInfo
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.GetListInputParams
import com.vng.clean.demo.cleanarchitecturedemo.domain.usecase.WeatherInfoList
import com.vng.clean.demo.cleanarchitecturedemo.model.WeatherInfoModel
import com.vng.clean.demo.cleanarchitecturedemo.model.mapper.WeatherInfoListMapper
import io.reactivex.observers.DisposableObserver

class MainViewModel(private val weatherListUseCase: WeatherInfoList,
                    private val weatherInfoListMapper: WeatherInfoListMapper): ViewModel() {

    private val weatherListMutableLiveData = MutableLiveData<Resource<List<WeatherInfoModel>>>()

    fun weatherListLiveData() = weatherListMutableLiveData

    fun fetchWeatherList(latitude: Double, longitude: Double) {
        weatherListUseCase.execute(object: DisposableObserver<List<WeatherInfo>>() {
            override fun onComplete() {
            }
            override fun onNext(resultList: List<WeatherInfo>) {
                val transformedList = weatherInfoListMapper.transform(resultList)
                weatherListMutableLiveData.postValue(Resource.success(transformedList))
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
                weatherListMutableLiveData.postValue(Resource.error(e.message, null))
            }
        }, GetListInputParams(latitude, longitude))
    }

    override fun onCleared() {
        weatherListUseCase.dispose()
        super.onCleared()
    }
}