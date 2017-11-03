@file:JvmName("XRecyclerViewUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.content.Context
import android.support.v7.widget.*
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.messcat.mclibrary.widget.SpacesItemDecoration
import com.messcat.mclibrary.widget.SwipeItemLayout


/**
 * XRecyclerView的封装类
 * Created by Administrator on 2017/9/1 0001.
 */

private var isRefresh: Boolean? = null
private var pageNo: Int = 0

/**
 * 初始化XRecyclerView
 * 参数1 XRecyclerView
 * 参数2 滑动操作
 * 参数3 是否固定
 * 参数4 动画
 */
fun initXRecyclerView(xrecyclerView: XRecyclerView?) {
    xrecyclerView?.setNestedScrollingEnabled(false)
    xrecyclerView?.setHasFixedSize(false)
    xrecyclerView?.setItemAnimator(DefaultItemAnimator())
}

/**
 * 实现上拉刷新下拉加载的操作
 *
 * 参数1 XRecyclerView
 * 参数2 刷新的样式
 * 参数3 加载的样式
 * 参数4 回调接口
 * loadMore 加载
 * refresh 刷新
 */
fun refreshXRecyclerView(xrecyclerView: XRecyclerView?, listener: OnLoadingListener?, loadMore: Boolean, refresh: Boolean) {
    xrecyclerView?.setLoadingMoreEnabled(loadMore)
    xrecyclerView?.setPullRefreshEnabled(refresh)
    xrecyclerView?.setLoadingListener(object : XRecyclerView.LoadingListener {
        override fun onRefresh() {
            isRefresh = true
            pageNo = 1
            listener?.onRefresh(isRefresh, pageNo)
        }

        override fun onLoadMore() {
            isRefresh = false
            pageNo++
            listener?.onLoading(isRefresh, pageNo)
        }
    })
    xrecyclerView?.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
    xrecyclerView?.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)
}

/**
 * 设置左滑可以删除
 */
fun setSwipeItemListener(xrecyclerView: XRecyclerView?, listener: SwipeItemLayout.OnSwipeItemTouchListener) {
    xrecyclerView?.addOnItemTouchListener(listener)
}



interface OnLoadingListener {
    fun onRefresh(isRefresh: Boolean?, pageNo: Int?)
    fun onLoading(isRefresh: Boolean?, pageNo: Int?)
}