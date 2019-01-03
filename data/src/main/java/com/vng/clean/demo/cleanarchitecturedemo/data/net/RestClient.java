package com.vng.clean.demo.cleanarchitecturedemo.data.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vng.clean.demo.cleanarchitecturedemo.data.BuildConfig;

import org.apache.http.conn.ssl.StrictHostnameVerifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RestClient {

    private static final String TAG = RestClient.class.getSimpleName();

    private static final boolean DEBUG = true;

    private static final long CACHE_SIZE = 10 * 1024 * 1024;

    private final OkHttpClient mClient;

    private final Gson mGson;

    private final RxJava2CallAdapterFactory mRxJavaCallAdapterFactory;

    private final RetrofitFactory mRetrofitFactory;

    RestClient(Context context) {
        final Cache cache = createCache(context, CACHE_SIZE);

        final HttpLoggingInterceptor httpLoggingInterceptor = defaultLoggingInterceptor();

        mRetrofitFactory = new RetrofitFactory();

        /**
         * Issue #195 - Remove pinned certificate
         *
         * As a result of certificate expiration causes client application failed to request
         * API from server. Therefore, we use default device trust with bundled CAs instead of
         * using pinning certificate. This might introduce security hole for man-in-the-middle
         * attach, we should investigate further on this issue in the near future.
         */
//        SSLContext sslContext = null;
//        try {
//            sslContext = SslUtils.getSslConfig(context, R.raw.qr_zalopay_vn);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mClient = createClient(cache, null, null,
                httpLoggingInterceptor, 60, 60, 60);

        mGson = new GsonBuilder().create();

        mRxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    public <T> T create(@EndPoint int type, Class<T> serviceClass) {
        Retrofit retrofit = mRetrofitFactory.getRetrofit(type);
        if (retrofit == null) {
            synchronized (mRetrofitFactory) {
                retrofit = mRetrofitFactory.getRetrofit(type);
                if (retrofit == null) {
                    final List<Converter.Factory> converterList = new ArrayList<>();
                    converterList.add(GsonConverterFactory.create(mGson));

                    final List<CallAdapter.Factory> adapterList = new ArrayList<>();
                    adapterList.add(mRxJavaCallAdapterFactory);

                    retrofit = mRetrofitFactory.createRetrofit(type, mClient, converterList, adapterList);
                }
            }
        }
        return retrofit.create(serviceClass);
    }

    private Cache createCache(Context context, long size) {
        return new Cache(context.getCacheDir(), size);
    }

    private HttpLoggingInterceptor defaultLoggingInterceptor() {
        HttpLoggingInterceptor.Logger logger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG) {
                    Log.i(TAG, message.replaceAll("%", "%%"));
                }
            }
        };
        HttpLoggingInterceptor.Level level = BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE;

        return createLoggingInterceptor(logger, level);
    }

    private HttpLoggingInterceptor createLoggingInterceptor(HttpLoggingInterceptor.Logger logger,
                                                            HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger);
        httpLoggingInterceptor.setLevel(level);
        return httpLoggingInterceptor;
    }

    private OkHttpClient createClient(Cache cache,
                                      SSLContext sslContext,
                                      Interceptor requestInterceptor,
                                      HttpLoggingInterceptor loggingInterceptor,
                                      long readTimeOut /* seconds */,
                                      long writeTimeout /* seconds */,
                                      long connectionTimeout /* seconds */) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (cache != null) {
            builder.cache(cache);
        }
        if (requestInterceptor != null) {
            builder.addInterceptor(requestInterceptor);
        }
        if (loggingInterceptor != null) {
            builder.addInterceptor(loggingInterceptor);
        }
        if (sslContext != null) {
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new StrictHostnameVerifier());
        }
        builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        builder.connectTimeout(connectionTimeout, TimeUnit.SECONDS);
        return builder.build();
    }

    private Retrofit createRetrofit(String baseUrl,
                                    OkHttpClient client,
                                    Gson gson,
                                    CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }
}
