package com.mtool.toolslib.network.activity.netkit.converter

import com.alibaba.fastjson.JSON
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import okhttp3.Response
import kotlin.reflect.KClass

/**
 * Created by buck on 2017/10/24.
 */

open class ArrayConvert<T : Any>(val kcls: KClass<T>) : NetKitConvert<List<T>> {
    override fun convertResponse(response: Response, extraInfo: ExtraInfo): List<T>? {
        val json = StringConvert().convertResponse(response, extraInfo)
        return JSON.parseArray(json, kcls.java)
    }
}