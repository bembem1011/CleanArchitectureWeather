package com.vng.clean.demo.cleanarchitecturedemo.data.net;

import android.content.Context;

import com.vng.clean.demo.cleanarchitecturedemo.data.net.service.ForecastService;

public final class ServiceProvider {

    private static volatile RestClient sRestClient = null;
    private static volatile ForecastService sForecastService = null;

    private static RestClient getRestClient(Context context) {
        RestClient restClient = sRestClient;
        if (restClient == null) {
            synchronized (RestClient.class) {
                restClient = sRestClient;
                if (restClient == null) {
                    restClient = sRestClient = new RestClient(context);
                }
            }
        }
        return restClient;
    }

    public static ForecastService getForecastService(Context context) {
        ForecastService forecastService = sForecastService;
        if (forecastService == null) {
            synchronized (ForecastService.class) {
                forecastService = sForecastService;
                if (forecastService == null) {
                    forecastService = sForecastService = getRestClient(context).create(EndPoint.OPEN_WEATHER, ForecastService.class);
                }
            }
        }
        return forecastService;
    }
}
