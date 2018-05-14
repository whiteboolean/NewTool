package com.mtool.toolslib.network.activity.netkit.error

import android.widget.Toast
import com.lzy.okgo.model.Response
import com.mtool.toolslib.base.view.activity.ActivityControlManager
import com.mtool.toolslib.network.activity.netkit.NetUtils
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import java.net.UnknownHostException

/**
 * Created by buck on 2017/10/24.
 */

class ErrorHandler<T> : com.mtool.toolslib.network.activity.netkit.NetCallback<T> {

    override fun onError(ex: Throwable, response: Response<T>, extraInfo: ExtraInfo) {
        when (ex) {
            is UnknownHostException -> {
                if (!NetUtils.isNetworkAvailable()) {
                    Toast.makeText(ActivityControlManager.topContext(), "未连接网络，请检查您的网络链接", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(ActivityControlManager.topContext(), "网络异常，请稍后重试", Toast.LENGTH_LONG).show()
                }
            }
            else -> {
                Toast.makeText(ActivityControlManager.topContext(), "数据异常，请稍后重试", Toast.LENGTH_LONG).show()
            }
        }
    }
}