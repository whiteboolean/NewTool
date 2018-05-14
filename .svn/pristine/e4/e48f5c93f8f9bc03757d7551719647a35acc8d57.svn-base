package com.mtool.toolslib.network.activity.netkit.converter

import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import okhttp3.Response

/**
 * Created by buck on 2017/10/24.
 */

open class ByteArrayConvert : NetKitConvert<ByteArray> {
    override fun convertResponse(response: Response, extraInfo: ExtraInfo): ByteArray? {
        val data = response.body()?.bytes()
        extraInfo.originData(data)
        return data
    }

}