package com.vng.clean.demo.cleanarchitecturedemo.executor;

import android.support.annotation.NonNull;

import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.ProcessThreadExecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class BackgroundThreadExecutor implements ProcessThreadExecutor {

    private final ThreadPoolExecutor mThreadPoolExecutor;

    public BackgroundThreadExecutor() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS
                , new LinkedBlockingDeque<>(), new BackgroundThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mThreadPoolExecutor.execute(command);
    }

    /**
     * @{link} BackgroundThreadFactory
     */
    private static final class BackgroundThreadFactory implements ThreadFactory {

        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "android_" + counter++);
        }
    }
}
