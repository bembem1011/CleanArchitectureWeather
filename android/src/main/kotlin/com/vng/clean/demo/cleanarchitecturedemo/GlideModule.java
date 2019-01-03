package com.vng.clean.demo.cleanarchitecturedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@com.bumptech.glide.annotation.GlideModule
public class GlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int memoryCacheSizeBytes = 1024 * 1024 * 100; //100MB for memory cache
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));

        // enable print logs when running develop environment
        builder.setLogLevel(Log.DEBUG);
    }
}