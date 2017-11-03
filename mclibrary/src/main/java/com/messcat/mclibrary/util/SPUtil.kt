@file:JvmName("SPUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.messcat.mclibrary.KEY_MODE_NIGHT
import com.messcat.mclibrary.MAIN
import com.messcat.mclibrary.MEMBER
import com.messcat.mclibrary.WELCOME
import com.messcat.mclibrary.base.*
import java.util.ArrayList

/**
 * SP保存本地数据
 * Created by Administrator on 2017/9/21 0021.
 */
private val CONFIG = "config"

/**
 * 获取SharedPreferences实例对象
 *
 * @param fileName
 */
private fun getSharedPreference(fileName: String): SharedPreferences {
    return BaseApplication.getInstance()?.getSharedPreferences(fileName, Context.MODE_PRIVATE)!!
}

/**
 * 保存一个String类型的值！
 */
fun putString(key: String, value: String) {
    val editor = getSharedPreference(CONFIG).edit()
    editor.putString(key, value).apply()
}

/**
 * 获取String的value
 */
fun getString(key: String, defValue: String?): String? {
    val sharedPreference = getSharedPreference(CONFIG)
    return sharedPreference.getString(key, defValue)
}

/**
 * 获取String的value
 */
fun getSPString(key: String, defValue: String?): String? {
    val sharedPreference = getSharedPreference(CONFIG)
    return sharedPreference.getString(key, defValue)
}

/**
 * 保存一个Boolean类型的值！
 */
fun putBoolean(key: String, value: Boolean?) {
    val editor = getSharedPreference(CONFIG).edit()
    editor.putBoolean(key, value!!).apply()
}

/**
 * 获取boolean的value
 */
fun getBoolean(key: String, defValue: Boolean?): Boolean {
    val sharedPreference = getSharedPreference(CONFIG)
    return sharedPreference.getBoolean(key, defValue!!)
}

/**
 * 保存一个int类型的值！
 */
fun putInt(key: String, value: Int) {
    val editor = getSharedPreference(CONFIG).edit()
    editor.putInt(key, value).apply()
}

/**
 * 获取int的value
 */
fun getInt(key: String, defValue: Int): Int {
    val sharedPreference = getSharedPreference(CONFIG)
    return sharedPreference.getInt(key, defValue)
}

/**
 * 保存一个float类型的值！
 */
fun putFloat(fileName: String, key: String, value: Float) {
    val editor = getSharedPreference(fileName).edit()
    editor.putFloat(key, value).apply()
}

/**
 * 获取float的value
 */
fun getFloat(key: String, defValue: Float?): Float {
    val sharedPreference = getSharedPreference(CONFIG)
    return sharedPreference.getFloat(key, defValue!!)
}

/**
 * 保存一个long类型的值！
 */
fun putLong(key: String, value: Long) {
    val editor = getSharedPreference(CONFIG).edit()
    editor.putLong(key, value).apply()
}

/**
 * 获取long的value
 */
fun getLong(key: String, defValue: Long): Long {
    val sharedPreference = getSharedPreference(CONFIG)
    return sharedPreference.getLong(key, defValue)
}

/**
 * 取出List<String>
 *
 * @param key List<String> 对应的key
 * @return List<String>
</String></String></String> */
fun getStrListValue(key: String): List<String> {
    val strList = ArrayList<String>()
    val size = getInt(key + "size", 0)
    //Log.d("sp", "" + size);
    for (i in 0..size - 1) {
        strList.add(getString(key + i, null)!!)
    }
    return strList
}

/**
 * 存储List<String>
 *
 * @param key     List<String>对应的key
 * @param strList 对应需要存储的List<String>
</String></String></String> */
fun putStrListValue(key: String, strList: List<String>?) {
    if (null == strList) {
        return
    }
    // 保存之前先清理已经存在的数据，保证数据的唯一性
    removeStrList(key)
    val size = strList.size
    putInt(key + "size", size)
    for (i in 0..size - 1) {
        putString(key + i, strList[i])
    }
}

/**
 * 清空List<String>所有数据
 *
 * @param key List<String>对应的key
</String></String> */
fun removeStrList(key: String) {
    val size = getInt(key + "size", 0)
    if (0 == size) {
        return
    }
    remove(key + "size")
    for (i in 0..size - 1) {
        remove(key + i)
    }
}


/**
 * 清空对应key数据
 */
fun remove(key: String) {
    val editor = getSharedPreference(CONFIG).edit()
    editor.remove(key).apply()
}

fun getNightMode(): Boolean {
    return getBoolean(KEY_MODE_NIGHT, false)
}

fun setNightMode(nightMode: Boolean) {
    putBoolean(KEY_MODE_NIGHT, nightMode)
}

/**
 * 保存登录信息
 *
 * @param member
 */
fun saveMember(member: String?) {
    if (member == null)
        return
    putString(MEMBER, member)
}

/**
 * 获取登录信息
 *
 * @return
 */
fun getMember(): String? = getString(MEMBER, null)

/**
 * 保存个人中心数据
 */
fun saveMain(sel: String?) {
    if (sel == null) {
        return
    }
    putString(MAIN, sel)
}

/**
 * 获取首页数据
 * @return
 */
fun getMain(): String? = getString(MAIN, null)

/**
 * 保存欢迎页面数据
 */
fun saveWelcome(welcome: String?) {
    if (welcome == null) {
        return
    }
    putString(WELCOME, welcome)
}

/**
 * 获取欢迎页面数据
 */
fun getWelcome(): String? = getString(WELCOME, null)


/**
 * 删除登录信息
 */
fun clearSP(key: String) {
    remove(key)
}

/**
 * 退出登陆
 * 清除所有数据
 */
fun clear() {
    val edit = getSharedPreference(CONFIG).edit()
    edit.clear()
    edit.commit()
}