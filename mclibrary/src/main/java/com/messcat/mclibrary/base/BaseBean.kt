package com.messcat.mclibrary.base

import java.io.Serializable

/**
 * Created by Administrator on 2017/8/30 0030.
 */
open class BaseBean<T>(@JvmField var message: String?, @JvmField var code: Int?, @JvmField var data: T?) : Serializable {

}