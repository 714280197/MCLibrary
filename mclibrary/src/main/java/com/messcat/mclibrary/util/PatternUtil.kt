@file:JvmName("PatternUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util
import java.util.regex.Pattern

/**
 * 正则表达式
 * Created by Administrator on 2017/9/20 0020.
 */

fun getEN(mobiles: String): String {
    val p = Pattern
            .compile("[^a-z^A-Z^0-9]")
    val m = p.matcher(mobiles)
    return m.replaceAll("")
}

/**
 * 验证电话号码
 */
fun verifyTel(telPhone: String): Boolean {
    val p = Pattern
            .compile("^(\\d{3,4}-)\\d{7,8}$")
    val m = p.matcher(telPhone)
    return m.find()
}

/**
 * 验证手机号码
 */
fun verifyPhone(telPhone: String): Boolean {
    val p = Pattern
            .compile("^1[3|4|5|7|8][0-9]{9}$")
    val m = p.matcher(telPhone)
    return m.find()
}

/**
 * 验证身份证
 */
fun verifyCard(card: String): Boolean {
    val p = Pattern
            .compile("\\d{14}[[0-9],0-9xX]")
    val m = p.matcher(card)
    return m.find()
}

/**
 * 验证Email
 */
fun verifyEmail(email: String): Boolean {
    val p = Pattern
            .compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    val m = p.matcher(email)
    return m.find()
}

/**
 * 验证邮政编号
 */
fun verifyPostcode(code: String): Boolean {
    val p = Pattern
            .compile("[1-9]\\\\d{5}(?!\\d)")
    val m = p.matcher(code)
    return m.find()
}

/**
 * 验证QQ
 */
fun verifyQQ(qq: String): Boolean {
    val p = Pattern
            .compile("[1-9][0-9]{4,}")
    val m = p.matcher(qq)
    return m.find()
}
