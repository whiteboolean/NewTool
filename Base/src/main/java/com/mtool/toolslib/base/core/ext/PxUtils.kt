package com.mtool.toolslib.base.core.ext

import com.mtool.toolslib.base.app.AppBase

/**
 * edit by buck on 2017/10/3.
 */
object PxUtils {
    val DENSITY = AppBase.context.resources.displayMetrics.density
    val SCALE_DENSITY = AppBase.context.resources.displayMetrics.scaledDensity
}


fun Float.dp2px() = (this * PxUtils.DENSITY + 0.5f).toInt()
fun Int.dp2px() = (this * PxUtils.DENSITY + 0.5f).toInt()


fun Float.px2sp() = (this / PxUtils.SCALE_DENSITY + 0.5f).toInt()

fun Float.sp2px() = (this * PxUtils.SCALE_DENSITY + 0.5f).toInt()

fun Float.px2dp() = (this / PxUtils.DENSITY + 0.5f).toInt()
