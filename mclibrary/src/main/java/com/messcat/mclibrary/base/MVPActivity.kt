package com.messcat.mclibrary.base

import android.os.Bundle

/**
 * Created by Administrator on 2017/8/30 0030.
 */
abstract class MVPActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @JvmField var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = initPresenter()
        initView()
        initEvent()
        initData()
//        if (mPresenter != null)
//            mPresenter?.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (mPresenter != null)
//            mPresenter?.detachView()
    }

    protected abstract fun initPresenter(): T
}