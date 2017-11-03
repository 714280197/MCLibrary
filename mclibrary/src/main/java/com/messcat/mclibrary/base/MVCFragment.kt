package com.messcat.mclibrary.base

import android.os.Bundle
import android.view.View

/**
 * Created by Administrator on 2017/9/4 0004.
 */
abstract class MVCFragment : BaseFragment() {
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
    }
}