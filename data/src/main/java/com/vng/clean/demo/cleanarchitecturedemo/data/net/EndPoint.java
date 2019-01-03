package com.vng.clean.demo.cleanarchitecturedemo.data.net;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({EndPoint.OPEN_WEATHER})
public @interface EndPoint {
    int OPEN_WEATHER = 0;
}