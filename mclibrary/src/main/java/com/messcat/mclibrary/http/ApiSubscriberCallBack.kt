package com.messcat.mclibrary.http

import android.content.Context
import com.messcat.mclibrary.util.isConnected
import rx.Observer


/**
 * Created by Administrator on 2017/11/1 0001.
 */

abstract class ApiSubscriberCallBack<T>(context: Context?) : Observer<T> {

    private var mContext = context

    override fun onCompleted() {
        _onComplete()
    }

    override fun onError(e: Throwable?) {
//        if (!isConnected(mContext!!)) {
//            _onNetWorkError()//network unConnected
//        } else {
            if (e is ResponseNullException) {
                _onEmpty()
            } else if (e is ServerException) {
                _onServerError((e as ServerException).returnCode, e.message!!)
            } else {
                _onNetWorkError()//UnknownHostException 1：服务器地址错误；2：网络未连接
            }
//        }
    }

    override fun onNext(t: T) {
        _onNext(t)
    }


    abstract fun _onNext(o: T) //onNext()

    abstract fun _onEmpty() //返回的response为空，或者List数组size为0

    abstract fun _onNetWorkError() //网络未连接

    abstract fun _onServerError(returnCode: String, msg: String) //接口调用操作出现异常，比如注册失败（已注册,短信验证码出错,and so on）

    abstract fun _onComplete() //onComplete()
}
