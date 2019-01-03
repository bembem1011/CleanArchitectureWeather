package com.vng.clean.demo.cleanarchitecturedemo.data.net;


import android.util.SparseArray;

import com.vng.clean.demo.cleanarchitecturedemo.data.BuildConfig;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class RetrofitFactory {

    private final SparseArray<Retrofit> mMap = new SparseArray<>();

    Retrofit getRetrofit(@EndPoint int type) {
        return mMap.get(type);
    }

    Retrofit createRetrofit(@EndPoint int type, OkHttpClient client,
                            List<Converter.Factory> converterList,
                            List<CallAdapter.Factory> callAdapterList) {
        final Retrofit.Builder builder = new Retrofit.Builder().client(client);

        switch (type) {
            case EndPoint.OPEN_WEATHER:
                builder.baseUrl(BuildConfig.OPEN_WEATHER);
                break;
        }

        for (Converter.Factory factory: converterList) {
            builder.addConverterFactory(factory);
        }

        for (CallAdapter.Factory factory: callAdapterList) {
            builder.addCallAdapterFactory(factory);
        }

        return builder.build();
    }
}

