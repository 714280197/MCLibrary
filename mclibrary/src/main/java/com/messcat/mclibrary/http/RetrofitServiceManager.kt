package com.messcat.kotlin.mchttp

import android.util.Log
import com.messcat.mclibrary.BASE_URL
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.messcat.kotlin.utils.getSystemTime
import com.messcat.mclibrary.base.BaseApplication
import com.messcat.mclibrary.getMd5String
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import okhttp3.RequestBody


/**
 * Created by zhouwei on 16/11/9.
 */

class RetrofitServiceManager private constructor() {
    private val mRetrofit: Retrofit

    init {
        // 创建 OKHttpClient
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//读操作超时时间

        //添加日志拦截器
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        //添加请求头
        builder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response {
                val randomId = getSystemTime() + UUID.randomUUID().toString()
                val original = chain?.request()
                val request = original?.newBuilder()!!
                        .header("UserId", "PalyMusic")
                        .header("Time", randomId)
                        .header("Sign", getMd5String(randomId))
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .method(original.method(), original.body())
                        .build()
                return chain.proceed(request)
            }
        })

        // 创建Retrofit
        mRetrofit = Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(BASE_URL)
                .build()
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitServiceManager()
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * *
     * @param <T>
     * *
     * @return
    </T> */
    fun <T> create(service: Class<T>): T {
        return mRetrofit.create(service)
    }

    companion object {
        private val DEFAULT_TIME_OUT = 10//超时时间 5s
        private val DEFAULT_READ_TIME_OUT = 10

        /**
         * 获取RetrofitServiceManager
         * @return
         */
        @JvmStatic
        val instance: RetrofitServiceManager
            get() = SingletonHolder.INSTANCE
    }
}
