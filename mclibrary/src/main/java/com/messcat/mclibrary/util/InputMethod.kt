@file:JvmName("InputMethod")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.messcat.mclibrary.base.BaseApplication


/**
 * 键盘控制
 * Created by Administrator on 2017/10/12 0012.
 */

private var imm: InputMethodManager? = BaseApplication.application?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

// 如果软键盘已经显示，则隐藏，反之则显示
fun setToggleSoft() {
    imm?.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
}

// 获取软键盘的显示状态
fun isOpen(): Boolean = imm?.isActive()!!

// 隐藏软键盘
fun setHide(view: IBinder) {
    if (isOpen()) {
        imm?.hideSoftInputFromWindow(view, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

// 强制显示软键盘
fun setShowSoft(view: View) {
    if (!isOpen()) {
        imm?.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
}

// 强制隐藏软键盘
fun setHideSoft(view: View) {
    if (isOpen()) {
        imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}