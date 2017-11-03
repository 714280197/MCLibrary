@file:JvmName("LogUtil")
@file:JvmMultifileClass

package com.messcat.kotlin.utils
import android.util.Log

/**
 * Log日志
 */
var TAGS: String = "Demo"

fun v(msg: String?) = Log.v(TAGS, msg)

fun d(msg: String?) = Log.d(TAGS, msg)

fun i(msg: String?) = Log.i(TAGS, msg)

fun w(msg: String?) = Log.w(TAGS, msg)

fun e(msg: String?) = Log.e(TAGS, msg)

fun wtf(msg: String?) = Log.wtf(TAGS, msg)

