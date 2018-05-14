package com.mtool.toolslib.base.view.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.mtool.toolslib.base.app.AppBase


/***
 * LifeRecycle
 */
object ActivityManager {

    private val activitys = mutableListOf<Activity>()

    fun finishAllActivity() {
        activitys.forEach { it.finish() }
        activitys.clear()
    }

    fun onTerminate() = activitys.clear()

    /**
     * 返回当前界面最顶层的activity，若没有，则返回null
     */
    fun topActivity() = activitys.lastOrNull()

    /**
     * 返回最顶层的activity，若没有，则返回application
     */

    fun topContext() = topActivity() ?: AppBase.context

    val callback =  object :Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityDestroyed(activity: Activity) {
            activitys.remove(activity)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activitys.add(activity)
            AppBase.context = activity
        }
    }
}