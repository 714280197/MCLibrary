package com.messcat.mclibrary.base

import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 *基于Rx的Presenter封装,控制订阅的生命周期
 * Created by Administrator on 2017/8/11 0011.
 */
open class RxPresenter<V : BaseView> : BasePresenter<V> {

    var mView: V? = null

    fun unSubscribe() {

    }

    fun addSubscribe(subscription: Subscription) {

    }

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

    protected fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}