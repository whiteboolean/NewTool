package com.mtool.toolslib.base.core.ext

import android.os.Handler
import android.os.Looper
import java.util.concurrent.TimeUnit

/**
 * Created by JayCruz on 2017/10/22.
 */

private object ContextHandler {
    val handler = Handler(Looper.getMainLooper())
    val mainThread = Looper.getMainLooper().thread
}
fun runOnUiThread(action: () -> Unit){
    if (ContextHandler.mainThread == Thread.currentThread()) action() else ContextHandler.handler.post { action() }
}
fun runDelayed(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, action: () -> Unit) {
    Handler().postDelayed(action, timeUnit.toMillis(delay))
}
fun runDelayedOnUiThread(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, action: () -> Unit) {
    ContextHandler.handler.postDelayed(action, timeUnit.toMillis(delay))
}
