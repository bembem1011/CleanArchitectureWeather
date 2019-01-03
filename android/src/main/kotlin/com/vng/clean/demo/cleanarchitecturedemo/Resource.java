package com.vng.clean.demo.cleanarchitecturedemo;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Resource<T> {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Status.LOADING, Status.SUCCESS, Status.ERROR})
    public @interface Status {
        int LOADING = 0;
        int SUCCESS = LOADING + 1;
        int ERROR = SUCCESS + 1;
    }

    @NonNull
    private final @Status int mStatus;
    @Nullable
    private final T mData;
    @Nullable private final String mMessage;

    @Nullable
    public T getData() {
        return mData;
    }

    @Nullable
    public String getMessage() {
        return mMessage;
    }

    @NonNull
    public int getStatus() {
        return mStatus;
    }

    public boolean isLoading() {
        return mStatus == Status.LOADING;
    }

    public boolean isSuccess() {
        return mStatus == Status.SUCCESS;
    }

    public boolean isError() {
        return mStatus == Status.ERROR;
    }

    private Resource(@NonNull @Status int status, @Nullable T data, @Nullable String message) {
        this.mStatus = status;
        this.mData = data;
        this.mMessage = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }
}
