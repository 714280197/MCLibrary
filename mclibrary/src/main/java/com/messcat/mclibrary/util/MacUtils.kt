@file:JvmName("MacUtil")
@file:JvmMultifileClass

package com.messcat.mclibrary.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import java.io.Reader
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.Enumeration

/**
 * Created by Administrator on 2017/10/26 0026.
 */


/**
 * 获取当前系统连接网络的网卡的mac地址
 *
 * @return
 */
val getMac: String
    @SuppressLint("NewApi")
    get() {
        var mac: ByteArray? = null
        val sb = StringBuffer()
        try {
            val netInterfaces = NetworkInterface.getNetworkInterfaces()
            while (netInterfaces.hasMoreElements()) {
                val ni = netInterfaces.nextElement()
                val address = ni.inetAddresses

                while (address.hasMoreElements()) {
                    val ip = address.nextElement()
                    if (ip.isAnyLocalAddress || ip !is Inet4Address || ip.isLoopbackAddress())
                        continue
                    if (ip.isSiteLocalAddress())
                        mac = ni.hardwareAddress
                    else if (!ip.isLinkLocalAddress()) {
                        mac = ni.hardwareAddress
                        break
                    }
                }
            }
        } catch (e: SocketException) {
            e.printStackTrace()
        }

        if (mac != null) {
            for (i in mac.indices) {
                sb.append(parseByte(mac[i]))
            }
            return sb.substring(0, sb.length - 1)
        } else {
            return "00000000000"
        }
    }

//    获取当前连接网络的网卡的mac地址
private fun parseByte(b: Byte): String {
    val s = "00" + Integer.toHexString(b.toInt()) + ":"
    return s.substring(s.length - 3)
}

