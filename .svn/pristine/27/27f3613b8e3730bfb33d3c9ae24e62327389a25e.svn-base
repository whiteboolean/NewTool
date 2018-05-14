package com.mtool.toolslib.base.app

import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.HttpHeaders

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import java.lang.Exception
import java.util.logging.Level


/**
 * Created by gerry on 2016/8/2.
 */
open class AppNetWork : AppBase() {
    override fun onCreate() {
        super.onCreate()
        context = this
        initOkGO()
    }

    private fun initOkGO() {
        try {
            val TIME_OUT_READ = 30000L
            val TIME_OUT_WRITE = 30000L
            val TIME_OUT_CONNECT = 30000L
            val builder = OkHttpClient.Builder()//全局的读取超时时间
            builder.readTimeout(TIME_OUT_READ, TimeUnit.MILLISECONDS)
            builder.writeTimeout(TIME_OUT_WRITE, TimeUnit.MILLISECONDS)
            builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
            builder.hostnameVerifier { s, sslSession -> true }

            val loggingInterceptor = HttpLoggingInterceptor("OkGo")
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
            loggingInterceptor.setColorLevel(Level.INFO)
            builder.addInterceptor(loggingInterceptor)

            val headers = HttpHeaders().also { it.put("Accept-Language", "zh-cn,zh,en-0,en;") } //header不支持中文

            val instance = OkGo.getInstance()
                    .init(this)
                    .setOkHttpClient(builder.build())//建议设置OkHttpClient，不设置将使用默认的
                    .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)               //全局统一缓存模式，默认不使用缓存，可以不传
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                    .setRetryCount(3)          //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                    .addCommonHeaders(headers)                       //全局公共参数

//            instance.okHttpClient.

//                    .register(GlideUrl::class; InputStream::class; object : OkHttpUrlLoader.Factory(instance.okHttpClient))

//            Glide.get(this).

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
