package com.vng.clean.demo.cleanarchitecturedemo.domain.usecase

import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.PostExecutionThread
import com.vng.clean.demo.cleanarchitecturedemo.domain.executor.ProcessThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, in Params>(private val processThreadExecutor: ProcessThreadExecutor,
                                  private val postExecutionThread: PostExecutionThread) {

    private val compositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(processThreadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer)
        compositeDisposable.add(observable)
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}