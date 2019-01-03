package com.vng.clean.demo.cleanarchitecturedemo;

import android.content.Context;

import com.vng.clean.demo.cleanarchitecturedemo.data.model.mapper.CloudWeatherDataListMapper;
import com.vng.clean.demo.cleanarchitecturedemo.data.model.mapper.CloudWeatherDataMapper;
import com.vng.clean.demo.cleanarchitecturedemo.data.net.ServiceProvider;
import com.vng.clean.demo.cleanarchitecturedemo.data.net.service.ForecastService;
import com.vng.clean.demo.cleanarchitecturedemo.data.repository.UserDataRepository;
import com.vng.clean.demo.cleanarchitecturedemo.data.repository.WeatherDataRepository;
import com.vng.clean.demo.cleanarchitecturedemo.data.repository.datasource.weather.WeatherDataStoreFactory;
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.UserRepository;
import com.vng.clean.demo.cleanarchitecturedemo.domain.repository.WeatherRepository;
import com.vng.clean.demo.cleanarchitecturedemo.domain.usecase.WeatherInfoList;
import com.vng.clean.demo.cleanarchitecturedemo.domain.usecase.WeatherInfoListImpl;
import com.vng.clean.demo.cleanarchitecturedemo.executor.BackgroundThreadExecutor;
import com.vng.clean.demo.cleanarchitecturedemo.executor.UIThread;
import com.vng.clean.demo.cleanarchitecturedemo.main.MainViewModelFactory;
import com.vng.clean.demo.cleanarchitecturedemo.model.mapper.WeatherInfoListMapper;
import com.vng.clean.demo.cleanarchitecturedemo.model.mapper.WeatherInfoMapper;

public final class Injector {

    private static volatile WeatherDataStoreFactory sWeatherDataStoreFactory;
    private static volatile UserRepository sUserRepository;
    private static volatile WeatherRepository sWeatherRepository;

    private static volatile UIThread sUIThread;
    private static volatile BackgroundThreadExecutor sBackgroundThreadExecutor;

    public static Context getApplicationContext() {
        return MyApplication.getInstance().getApplicationContext();
    }

    public static WeatherInfoMapper getWeatherInfoMapper() {
        return new WeatherInfoMapper();
    }

    public static WeatherInfoListMapper getWeatherInfoListMapper() {
        return new WeatherInfoListMapper(getWeatherInfoMapper());
    }

    public static WeatherInfoList getWeatherInfoList() {
        return new WeatherInfoListImpl(getUserRepository(), getWeatherRepository()
                , getBackgroundThreadExecutor(), getUIThread());
    }

    public static WeatherRepository getWeatherRepository() {
        WeatherRepository weatherRepository = sWeatherRepository;
        if (weatherRepository == null) {
            synchronized (WeatherDataRepository.class) {
                weatherRepository = sWeatherRepository;
                if (weatherRepository == null) {
                    weatherRepository = sWeatherRepository = new WeatherDataRepository(getWeatherDataStoreFactory()
                            , getCloudWeatherDataListMapper());
                }
            }
        }
        return weatherRepository;
    }

    public static UserRepository getUserRepository() {
        UserRepository userRepository = sUserRepository;
        if (userRepository == null) {
            synchronized (UserDataRepository.class) {
                userRepository = sUserRepository;
                if (userRepository == null) {
                    userRepository = new UserDataRepository();
                }
            }
        }
        return userRepository;
    }

    public static UIThread getUIThread() {
        UIThread uiThread = sUIThread;
        if (uiThread == null) {
            synchronized (UIThread.class) {
                uiThread = sUIThread;
                if (uiThread == null) {
                    uiThread = sUIThread = new UIThread();
                }
            }
        }
        return uiThread;
    }

    public static BackgroundThreadExecutor getBackgroundThreadExecutor() {
        BackgroundThreadExecutor backgroundThreadExecutor = sBackgroundThreadExecutor;
        if (backgroundThreadExecutor == null) {
            synchronized (BackgroundThreadExecutor.class) {
                backgroundThreadExecutor = sBackgroundThreadExecutor;
                if (backgroundThreadExecutor == null) {
                    backgroundThreadExecutor = sBackgroundThreadExecutor = new BackgroundThreadExecutor();
                }
            }
        }
        return backgroundThreadExecutor;
    }

    public static CloudWeatherDataMapper getCloudWeatherDataMapper() {
        return new CloudWeatherDataMapper();
    }

    public static CloudWeatherDataListMapper getCloudWeatherDataListMapper() {
        return new CloudWeatherDataListMapper(getCloudWeatherDataMapper());
    }

    public static WeatherDataStoreFactory getWeatherDataStoreFactory() {
        WeatherDataStoreFactory weatherDataStoreFactory = sWeatherDataStoreFactory;
        if (weatherDataStoreFactory == null) {
            synchronized (WeatherDataStoreFactory.class) {
                weatherDataStoreFactory = sWeatherDataStoreFactory;
                if (weatherDataStoreFactory == null) {
                    weatherDataStoreFactory = sWeatherDataStoreFactory = new WeatherDataStoreFactory(getForecastSerivce());
                }
            }
        }
        return weatherDataStoreFactory;
    }

    public static ForecastService getForecastSerivce() {
        return ServiceProvider.getForecastService(getApplicationContext());
    }

    public static MainViewModelFactory getMainViewModelFactory() {
        return new MainViewModelFactory(getWeatherInfoList(), getWeatherInfoListMapper());
    }
}
