package com.vng.clean.demo.cleanarchitecturedemo.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}