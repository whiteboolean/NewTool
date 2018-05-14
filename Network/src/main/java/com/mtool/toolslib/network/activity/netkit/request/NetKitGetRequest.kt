package com.mtool.toolslib.network.activity.netkit.request

import com.alibaba.fastjson.JSONException
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.model.HttpMethod
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.NoBodyRequest
import com.lzy.okgo.request.base.Request
import com.mtool.toolslib.network.activity.netkit.CallbackIgnore
import com.mtool.toolslib.network.activity.netkit.DowloadProgress
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import com.mtool.toolslib.network.activity.netkit.converter.NetKitConvert
import com.mtool.toolslib.network.activity.netkit.error.*
import okhttp3.RequestBody

/**
 * Created by buck on 2017/10/24.
 */


open class NetKitGetRequest<T>(murl: String, mPath: String, convert: NetKitConvert<out T>) : NoBodyRequest<T, NetKitGetRequest<T>>(murl + mPath) {

    protected var onSuccess: ((target: T, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    protected var onStart: ((request: Request<T, out Request<*, *>>, ignore: CallbackIgnore) -> Unit)? = null
    protected var onError: ((ex: Throwable, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    protected var downloadProgress: ((progress: Progress, ignore: CallbackIgnore) -> Unit)? = null
    protected var uploadProgress: ((progress: Progress, ignore: CallbackIgnore) -> Unit)? = null
    protected var onCacheSuccess: ((response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    protected var onFinish: ((extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    protected val callbacks = ArrayList<com.mtool.toolslib.network.activity.netkit.NetCallback<T>>(1)
    protected val extraInfo = ExtraInfo()

    override fun getMethod(): HttpMethod {
        return HttpMethod.GET
    }

    override fun generateRequest(requestBody: RequestBody?): okhttp3.Request {
        val requestBuilder = generateRequestBuilder(requestBody)
        return requestBuilder.get().url(url).tag(tag).build()
    }

    fun loadingDialog(cancelable: Boolean = true, msg: String? = null) = also { addCallback(LoadingDialogState<T>(cancelable, msg)) }

    fun errorHandler(errorHandler: com.mtool.toolslib.network.activity.netkit.NetCallback<T>?) = also { addCallback(errorHandler ?: ErrorHandler<T>()) }


    fun onSuccess(success: (target: T, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onSuccess = success }

    fun onStart(start: (request: Request<T, out Request<Any, Request<*, *>>>, ignore: CallbackIgnore) -> Unit) = also { onStart = start }

    fun onError(error: (ex: Throwable, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onError = error }

    fun onDownloadProgress(progress: (progress: Progress, ignore: CallbackIgnore) -> Unit) = also { downloadProgress = progress }

    fun onUploadProgress(progress: (progress: Progress, ignore: CallbackIgnore) -> Unit) = also { uploadProgress = progress }

    fun onCacheSuccess(cacheSuccess: (response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onCacheSuccess = cacheSuccess }

    fun onFinish(finish: (extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onFinish = finish }

    fun addCallback(cb: com.mtool.toolslib.network.activity.netkit.NetCallback<T>) = also { callbacks.add(cb) }

    fun end() {
        execute(cb)
    }

    private val cb = object : Callback<T> {
        override fun onSuccess(response: Response<T>) {
            try {
                val ignore = CallbackIgnore()
                onSuccess?.let { it(response.body(), response, extraInfo, ignore) }
                if (!ignore.ignore) {
                    for (c in callbacks) {
                        c.onSuccess(response, extraInfo)
                    }
                }
            } catch (ex: Throwable) {
                response.exception = ex
                onError(response)
            }
        }

        override fun convertResponse(response: okhttp3.Response): T {
            return try {
                if (convert is DowloadProgress) {
                    convert.setCallbcak(this)
                }
                convert.convertResponse(response, extraInfo) ?: throw ExceptionForNet("数据错误，请稍后重试")
            } catch (e: ExceptionForResponseCode) {
                throw ExceptionForResponseCode("返回代码非200！", e)
            } catch (e: JSONException) {
                throw ExceptionForWrongType("接口调适错误！", e)
            } catch (e: Exception) {
                throw ExceptionForNet("数据连接错误，请稍后重试！", e)
            }
        }

        override fun onFinish() {
            val ignore = CallbackIgnore()
            onFinish?.let { it(extraInfo, ignore) }
            if (!ignore.ignore) {
                for (c in callbacks) {
                    c.onFinish(extraInfo)
                }
            }
        }

        override fun onError(response: Response<T>) {
            val ignore = CallbackIgnore()
            onError?.let { it(response.exception, response, extraInfo, ignore) }
            if (!ignore.ignore) {
                for (c in callbacks) {
                    c.onError(response.exception, response, extraInfo)
                }
            }
        }

        override fun onStart(request: Request<T, out Request<Any, Request<*, *>>>) {
            val ignore = CallbackIgnore()
            onStart?.let { it(request, ignore) }
            if (!ignore.ignore) {
                for (c in callbacks) {
                    c.onStart(request)
                }
            }
        }

        override fun uploadProgress(progress: Progress) {
            val ignore = CallbackIgnore()
            uploadProgress?.let { it(progress, ignore) }
            if (!ignore.ignore) {
                for (c in callbacks) {
                    c.onUploadProgress(progress)
                }
            }
        }

        override fun downloadProgress(progress: Progress) {
            val ignore = CallbackIgnore()
            downloadProgress?.let { it(progress, ignore) }
            if (!ignore.ignore) {
                for (c in callbacks) {
                    c.onDownloadProgress(progress)
                }
            }
        }

        override fun onCacheSuccess(response: Response<T>) {
            val ignore = CallbackIgnore()
            onCacheSuccess?.let { it(response, extraInfo, ignore) }
            if (!ignore.ignore) {
                for (c in callbacks) {
                    c.onCacheSuccess(response, extraInfo)
                }
            }
        }
    }
}