@file:JvmName("CallUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * 拨打电话
 * Created by Administrator on 2017/10/12 0012.
 */

/**
 * 拨打电话
 */
fun playCall(activity: Activity, phone: String) {
    //用intent启动拨打电话
    val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone))
    activity.startActivity(intent)
}
