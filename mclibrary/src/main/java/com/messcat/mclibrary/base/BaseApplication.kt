package com.messcat.mclibrary.base

import android.app.Application

/**
 * Created by Administrator on 2017/8/30 0030.
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        initCreate()
    }

    companion object {
        //单例模式
        var application: BaseApplication? = null
        fun getInstance() = application
    }

    abstract fun initCreate()
}