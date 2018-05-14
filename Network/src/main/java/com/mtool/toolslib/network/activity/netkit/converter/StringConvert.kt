package com.mtool.toolslib.network.activity.netkit.converter

import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import okhttp3.Response

/**
 * 直接转换String，不经过任何处理
 * Created by buck on 2017/10/24.
 */
open class StringConvert : NetKitConvert<String> {
    override fun convertResponse(response: Response, extraInfo: ExtraInfo): String? {
        val data = response.body()?.string()
        extraInfo.originData(data)
        return data
    }
}