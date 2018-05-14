package com.mtool.toolslib.network.activity.netkit.converter

import com.alibaba.fastjson.JSON
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import okhttp3.Response
import kotlin.reflect.KClass

/**
 * 直接转换为指定对象
 * Created by buck on 2017/10/24.
 */
open class ObjectConvert<T : Any>(val kcls: KClass<T>) : NetKitConvert<T> {

    override fun convertResponse(response: Response, extraInfo: ExtraInfo): T? {
        val json = StringConvert().convertResponse(response, extraInfo)
        return JSON.parseObject(json, kcls.java)
    }
}