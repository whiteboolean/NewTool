package com.mtool.toolslib.network.activity.netkit.delegate

import com.mtool.toolslib.network.activity.netkit.NetKitPostUrl
import com.mtool.toolslib.network.activity.netkit.converter.NetKitConvert
import com.mtool.toolslib.network.activity.netkit.request.NetKitPostRequest


/**
 *
 * Created by buck on 2017/10/26.
 */
class PostUrlDelegate(url: String, mPath: String, tag: Any? = null, val controller: RequestDelegateController)
    : NetKitPostUrl(url, mPath, tag) {

    override fun <T> convert(convert: NetKitConvert<out T>): NetKitPostRequest<T> {
//        return PostRequestDelegate<T>(url, mPath, convert, controller).tag(tag).defaultFormat(defaultFormat) as NetKitPostRequest<T>
        return PostRequestDelegate<T>(url, mPath, convert, controller).tag(tag) as NetKitPostRequest<T>
    }
}