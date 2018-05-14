package com.mtool.toolslib.network.activity.netkit.delegate

import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.mtool.toolslib.network.activity.netkit.CallbackIgnore
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import com.mtool.toolslib.network.activity.netkit.converter.NetKitConvert
import com.mtool.toolslib.network.activity.netkit.request.NetKitPostRequest

/**
 *
 * Created by buck on 2017/10/26.
 */
class PostRequestDelegate<T>(
        private val murl: String,
        private val mPath: String,
        private val convert: NetKitConvert<out T>,
        controller: RequestDelegateController? = null)
    : NetKitPostRequest<T>(murl, mPath, convert) {

    override public var onSuccess: ((target: T, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    override public var onStart: ((request: Request<T, out Request<*, *>>, ignore: CallbackIgnore) -> Unit)? = null
    override public var onError: ((ex: Throwable, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    override public var downloadProgress: ((progress: Progress, ignore: CallbackIgnore) -> Unit)? = null
    override public var uploadProgress: ((progress: Progress, ignore: CallbackIgnore) -> Unit)? = null
    override public var onCacheSuccess: ((response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    override public var onFinish: ((extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null

    init {
        controller?.setPostRequestDelegate(this as PostRequestDelegate<Any>)
    }

    override fun end() {

    }

    fun end2() = super.end()

    fun clone(): PostRequestDelegate<T> {
        val n = PostRequestDelegate(murl, mPath, convert)
        n.tag = tag
        n.onSuccess = onSuccess
        n.onStart = onStart
        n.onError = onError
        n.downloadProgress = downloadProgress
        n.uploadProgress = uploadProgress
        n.onCacheSuccess = onCacheSuccess
        n.onFinish = onFinish
        n.callbacks.addAll(callbacks)
        n.jsonParams.putAll(jsonParams)
        n.content = content
        n.mediaType = mediaType
        n.params = params
        n.headers = headers
        return n
    }

//    n.defaultFormat = defaultFormat
}