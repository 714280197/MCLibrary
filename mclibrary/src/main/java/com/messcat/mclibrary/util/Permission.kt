@file:JvmName("PermissionUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * 6.0动态权限
 * Created by Administrator on 2017/8/31 0031.
 */
//开启定位权限
fun getLocationPermission(activity: Activity?) {

    val WRITE_COARSE_LOCATION_REQUEST_CODE = 1
    //开启位置权限
    if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        //申请WRITE_EXTERNAL_STORAGE权限
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                WRITE_COARSE_LOCATION_REQUEST_CODE)//自定义的code
    }

}

//开启相机
fun getCameraPermission(activity: Activity?): Boolean {
    if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
        return true
    } else {
        ActivityCompat.requestPermissions(activity!!,
                arrayOf(Manifest.permission.CAMERA), 1)
    }
    return false
}

//开启录音权限
fun getRecored(activity: Activity?): Boolean {
    if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1)
        return false
    } else {
        return true
    }
}