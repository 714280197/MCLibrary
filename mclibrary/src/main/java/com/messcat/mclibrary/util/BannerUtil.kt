@file:JvmName("BannerUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader

/**
 * Banner
 * Created by Administrator on 2017/9/1 0001.
 */

/**
 * 初始化Banner
 * 参数1 Banner
 * 参数2 Activity
 * 参数3 自动切换时间
 * 参数4 Banner高度
 * 参数5 是否自动轮播
 * 参数6 指示器的位置 BannerConfig.CENTER
 */
fun initBanner(banner: Banner?, time: Int, height: Int, isPlay: Boolean, config: Int) {
    val layoutParams = banner?.getLayoutParams()
    layoutParams?.height = height
    banner?.setLayoutParams(layoutParams)
    //设置banner样式
    banner?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
    //设置自动轮播，默认为true
    banner?.isAutoPlay(isPlay)
    //设置指示器位置（当banner模式中有指示器时）
    banner?.setIndicatorGravity(config)
    //设置滚动时间
    banner?.setDelayTime(time)
    banner?.setImageLoader(
            object : ImageLoader() {
                override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                    Glide.with(context).load(path).into(imageView)//Glide框架
                }
            })
}

/**
 * 设置标题
 */
fun setBannerTitle(banner: Banner?, title: MutableList<String>?) {
    banner?.setBannerTitles(title)
}

/**
 * 启动Banner
 * 参数1 Banner
 * 参数2 图片路径集合
 */
fun stratBanner(banner: Banner?, listUrl: MutableList<String>) {
    banner?.setImages(listUrl)//图片地址集合
    banner?.start()//开始播放
}