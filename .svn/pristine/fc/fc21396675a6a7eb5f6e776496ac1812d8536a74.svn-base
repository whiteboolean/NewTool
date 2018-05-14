package com.mtool.toolslib.network.activity.netkit

import android.annotation.SuppressLint
import com.mtool.toolslib.base.app.AppBase.Companion.connectivityManager
import com.mtool.toolslib.base.app.AppNetWork


/**
 * Created by buck on 2017/10/25.
 */

object NetUtils {

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager().activeNetworkInfo
        return networkInfo?.isAvailable ?: false
    }
}