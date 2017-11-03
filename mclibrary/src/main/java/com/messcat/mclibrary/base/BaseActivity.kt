package com.messcat.mclibrary.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hengda.smart.jsyz.m.component.LoadingDialog
import com.messcat.kotlin.utils.LoadingDialogManager
import com.messcat.mclibrary.addActivitys
import com.messcat.mclibrary.finishActivitys
import java.util.concurrent.Executors

/**
 * Created by Administrator on 2017/8/30 0030.
 */
abstract class BaseActivity : AppCompatActivity(), LoadingDialogManager {

    @JvmField protected var mActivity: Activity? = null
    @JvmField protected var mContext: Context? = null
    override val loadingDialog by lazy { LoadingDialog(this) }//弹出Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayout() > 0) {
            setContentView(getLayout())
        }
        mActivity = this
        mContext = this
        addActivitys(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        finishActivitys(this)
    }

    abstract fun getLayout(): Int
    abstract fun initData()
    abstract fun initEvent()
    abstract fun initView()
}