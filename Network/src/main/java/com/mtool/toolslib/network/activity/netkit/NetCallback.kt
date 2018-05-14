package com.mtool.toolslib.network.activity.netkit

import com.lzy.okgo.model.Progress
import com.lzy.okgo.request.base.Request
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo

/**
 * Created by buck on 2017/10/24.
 */

interface NetCallback<T> {

    fun onSuccess(response: com.lzy.okgo.model.Response<T>, extraInfo: ExtraInfo) = Unit

    fun onFinish(extraInfo: ExtraInfo) = Unit

    fun onError(ex: Throwable, response: com.lzy.okgo.model.Response<T>, extraInfo: ExtraInfo) = Unit

    fun onStart(request: Request<T, out Request<Any, Request<*, *>>>) = Unit

    fun onUploadProgress(progress: Progress) = Unit

    fun onDownloadProgress(progress: Progress) = Unit

    fun onCacheSuccess(response: com.lzy.okgo.model.Response<T>, extraInfo: ExtraInfo) = Unit

}
