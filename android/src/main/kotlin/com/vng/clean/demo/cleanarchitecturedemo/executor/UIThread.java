package com.vng.clean.demo.cleanarchitecturedemo.executor;

import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.PostExecutionThread;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public final class UIThread implements PostExecutionThread {

    @NotNull
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
