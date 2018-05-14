package com.mtool.toolslib.network.activity.netkit.request

import android.util.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONException
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.model.HttpMethod
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.BodyRequest
import com.lzy.okgo.request.base.Request
import com.mtool.toolslib.network.activity.netkit.CallbackIgnore
import com.mtool.toolslib.network.activity.netkit.DowloadProgress
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import com.mtool.toolslib.network.activity.netkit.converter.NetKitConvert
import com.mtool.toolslib.network.activity.netkit.error.*
import okhttp3.RequestBody
import kotlin.collections.ArrayList

/**
 * Created by buck on 2017/10/24.
 */


open class NetKitPostRequest<T>(private var murl: String, private var mPath: String, private val convert: NetKitConvert<out T>) : BodyRequest<T, NetKitPostRequest<T>>(murl + mPath) {

    open protected var onSuccess: ((target: T, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    open protected var onStart: ((request: Request<T, out Request<*, *>>, ignore: CallbackIgnore) -> Unit)? = null
    open protected var onError: ((ex: Throwable, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    open protected var downloadProgress: ((progress: Progress, ignore: CallbackIgnore) -> Unit)? = null
    open protected var uploadProgress: ((progress: Progress, ignore: CallbackIgnore) -> Unit)? = null
    open protected var onCacheSuccess: ((response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null
    open protected var onFinish: ((extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit)? = null

    protected val callbacks = ArrayList<com.mtool.toolslib.network.activity.netkit.NetCallback<T>>(2)
    protected val extraInfo = ExtraInfo()
    protected val jsonParams = mutableMapOf<String, Any>()

    fun loadingDialog(cancelable: Boolean = true, msg: String? = null, cancelBlock: (() -> Unit)? = null) = addCallback(LoadingDialogState<T>(cancelable, msg, cancelBlock))

    fun errorHandler(errorHandler: com.mtool.toolslib.network.activity.netkit.NetCallback<T>?) = also {
        addCallback(errorHandler ?: ErrorHandler<T>())

    }


    override fun getMethod(): HttpMethod {
        return HttpMethod.POST
    }

    override fun generateRequest(requestBody: RequestBody): okhttp3.Request {
        val requestBuilder = generateRequestBuilder(requestBody)
        Log.e("接口请求", "-----------------------------------")
        Log.e("接口请求", "URL===>$url")
        Log.e("接口请求", "-----------------------------------")

        return requestBuilder
                .post(requestBody)
                .url(url)
                .tag(tag)
                .build()
    }

    open fun onSuccess(success: (target: T, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onSuccess = success }

    open fun onStart(start: (request: Request<T, out Request<Any, Request<*, *>>>, ignore: CallbackIgnore) -> Unit) = also { onStart = start }

    open fun onError(error: (ex: Throwable, response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onError = error }

    open fun onDownloadProgress(progress: (progress: Progress, ignore: CallbackIgnore) -> Unit) = also { downloadProgress = progress }

    open fun onUploadProgress(progress: (progress: Progress, ignore: CallbackIgnore) -> Unit) = also { uploadProgress = progress }

    open fun onCacheSuccess(cacheSuccess: (response: Response<T>, extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onCacheSuccess = cacheSuccess }

    open fun onFinish(finish: (extraInfo: ExtraInfo, ignore: CallbackIgnore) -> Unit) = also { onFinish = finish }

    fun addCallback(cb: com.mtool.toolslib.network.activity.netkit.NetCallback<T>) = also { callbacks.add(cb) }

    /****
     * JAVA 后台 接口方式
     * Map 转成 JSON格式输出
     */
    fun upJson(map: Map<String, Any>) = also {
        jsonParams.putAll(map)
        this.mediaType = HttpParams.MEDIA_TYPE_JSON
        this.isMultipart = false
    }

    /****
     * PHP 后台 接口方式
     * MAP 直接表单格式输出
     */
    fun formParams(map: Map<String, Any>) = also {
        this.jsonParams.putAll(map)
        this.isMultipart = true
    }

    fun updatePaging(pageNum: Int, pageSize: Int) = also {
        if (jsonParams["paramData"] != null) {
            val paging = jsonParams["paramData"] as HashMap<String, Any>
            paging.put("pageNum", pageNum)
            paging.put("pageSize", pageSize)
        }
        this.mediaType = HttpParams.MEDIA_TYPE_JSON
        this.isMultipart = false
    }

//    fun defaultFormat(isDefaultFormat: Boolean) = also { defaultFormat = isDefaultFormat }


    override fun generateRequestBody(): RequestBody {
        if (!isMultipart) {
            /***
             * JAVA 格式
             */
            upJson(JSON.toJSONString(jsonParams))
        } else {
            /***
             * PHP 格式
             */
            jsonParams.forEach {
                if (it.value != null) {
                    /***
                     * 依照不同格式去转型
                     */
                    val pValue = it.value
                    when (pValue) {
                        is String -> {
                            params(it.key, pValue)
                        }

                        is Int -> {
                            params(it.key, pValue)
                        }

                        is Double -> {
                            params(it.key, pValue)
                        }

                        is Boolean -> {
                            params(it.key, pValue)
                        }

                        is Long -> {
                            params(it.key, pValue)
                        }
                    }
                } else {
                    params(it.key, "")
                    Log.e("ERROR==> ", "generateRequestBody(NetKitPostRequest:129)----->${it.key} 参数为空！")
                }
            }
        }
        return super.generateRequestBody()
    }

    open fun end() {
        execute(cb)
    }

    private val cb = object : Callback<T> {
        override fun onSuccess(response: com.lzy.okgo.model.Response<T>) {
            try {
                val ignore = CallbackIgnore()
                onSuccess?.let { it(response.body(), response, extraInfo, ignore) }

                if (ignore.ignore) return

                for (c in callbacks) {
                    c.onSuccess(response, extraInfo)
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

            if (ignore.ignore) return

            for (c in callbacks) {
                c.onFinish(extraInfo)
            }
        }

        override fun onError(response: com.lzy.okgo.model.Response<T>) {
            val ignore = CallbackIgnore()
            onError?.let { it(response.exception, response, extraInfo, ignore) }

            if (ignore.ignore) return

            for (c in callbacks) {
                c.onError(response.exception, response, extraInfo)
            }
        }

        override fun onStart(request: Request<T, out Request<Any, Request<*, *>>>) {
            val ignore = CallbackIgnore()
            onStart?.let { it(request, ignore) }

            if (ignore.ignore) return

            for (c in callbacks) {
                c.onStart(request)
            }
        }

        override fun uploadProgress(progress: Progress) {
            val ignore = CallbackIgnore()
            uploadProgress?.let { it(progress, ignore) }

            if (ignore.ignore) return

            for (c in callbacks) {
                c.onUploadProgress(progress)
            }
        }

        override fun downloadProgress(progress: Progress) {
            val ignore = CallbackIgnore()
            downloadProgress?.let { it(progress, ignore) }

            if (ignore.ignore) return

            for (c in callbacks) {
                c.onDownloadProgress(progress)
            }
        }

        override fun onCacheSuccess(response: com.lzy.okgo.model.Response<T>) {
            val ignore = CallbackIgnore()
            onCacheSuccess?.let { it(response, extraInfo, ignore) }

            if (ignore.ignore) return

            for (c in callbacks) {
                c.onCacheSuccess(response, extraInfo)
            }
        }
    }
}
