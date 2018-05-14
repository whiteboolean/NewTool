package com.mtool.toolslib.utils

import org.jetbrains.anko.*

/**
 * Created by xianshang.liu on 2017/6/2.
 */
object LogUtil : AnkoLogger {

    fun logV(str: Any?) {
        verbose(str)
    }

    fun logD(str: Any?) {
        debug(str)
    }

    fun logI(str: Any?) {
        info(str)
    }

    fun logW(str: Any?) {
        warn(str)
    }

    fun logE(str: Any?) {
        error(str)
    }
}