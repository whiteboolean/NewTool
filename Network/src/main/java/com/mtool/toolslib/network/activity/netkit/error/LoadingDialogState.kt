package com.mtool.toolslib.network.activity.netkit.error

import android.app.Activity
import android.app.Dialog
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.mtool.toolslib.base.AM
import com.mtool.toolslib.base.core.ext.isNotNull
import com.mtool.toolslib.network.activity.netkit.DialogUtils
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo

/**
 * Created by buck on 2017/10/24.
 */

class LoadingDialogState<T>(
        val cancelable: Boolean = true,
        val msg: String? = null,
        val cancelBlock: (() -> Unit)? = null) : com.mtool.toolslib.network.activity.netkit.NetCallback<T> {

    val act: Activity? = AM.topActivity()
    var dialog: Dialog? = null

    override fun onFinish(extraInfo: ExtraInfo) {
        dialog.isNotNull {
            it.dismiss()
        }
    }

    override fun onError(ex: Throwable, response: Response<T>, extraInfo: ExtraInfo) {
        super.onError(ex, response, extraInfo)
        dialog.isNotNull {
            it.dismiss()
        }
    }

    override fun onStart(request: Request<T, out Request<Any, Request<*, *>>>) {
        if (act == null) return
        val message = msg ?: DEFAULT_MSG
        dialog = DialogUtils.showLoadingDialog(act, message, cancelable, cancelBlock)
    }

    companion object {
        const val DEFAULT_MSG = "数据加载中..."
    }
}