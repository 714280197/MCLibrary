package com.messcat.mclibrary.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hengda.smart.jsyz.m.component.LoadingDialog
import com.messcat.kotlin.utils.LoadingDialogManager

/**
 * Created by Administrator on 2017/8/30 0030.
 */
abstract class BaseFragment : Fragment(), LoadingDialogManager {

    @JvmField
    var mContext: Activity? = null//上下文
    @JvmField
    var mView: View? = null//View
    @JvmField
    var isLoad = false//是否显示

    override val loadingDialog by lazy { LoadingDialog(activity) }//赖初始化LoadingDialog

    override fun onAttach(context: Context?) {
        mContext = activity
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = getLayoutId()
        if (layoutId > 0)
            mView = inflater?.inflate(layoutId, null)
        return mView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {  // 在onCreateView（）执行后立马执行
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 处理预加载
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (!isLoad) {
                //当前fragment可见，执行onVisible
                onVisible()
            } else {
                onInVisible()
            }
        }
    }

    /**
     * 隐藏的时候调用
     */
    protected fun onInVisible() {
        setUserGone()
    }

    /**
     * 显示的时候调用
     */
    protected fun onVisible() {
        if (isLoad) {
            return
        } else {
            initData()
            isLoad = true
        }
    }

    /**
     * 设置是否可见
     */
    protected fun isShowUi(load: Boolean) {
        this.isLoad = load
    }

    /**
     * 加载XML布局
     */
    protected abstract fun getLayoutId(): Int

    //界面隐藏
    abstract fun setUserGone()

    //初始化
    protected abstract fun initView()

    //点击事件
    protected abstract fun initEvent()

    //加载数据
    protected abstract fun initData()
}