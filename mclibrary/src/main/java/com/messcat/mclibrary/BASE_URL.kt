@file:JvmName("BaseUrl")
@file:JvmMultifileClass

package com.messcat.mclibrary
import com.messcat.mclibrary.util.MD5Encode

/**
 * http://v.juhe.cn/weather/index?format=2&cityname=广州&key=404df18bfe29fe7b93e520d998489b61
 * 15902090933  ，密码slj820910110
百度地图开发者账号
 * 服务器地址
 */
var BASE_URL: String
    set(value) {
        BASE_URL = value
    }
    get() {
        return "http://211.149.146.200:6060/"
    }

/**
 * 加密
 */
fun getMd5String(randomId:String): String? {
    return MD5Encode("UserId=PalyMusic&Key=Play@2017#Music&Time=" + randomId)
}