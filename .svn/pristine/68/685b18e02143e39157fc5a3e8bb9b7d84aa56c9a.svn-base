package com.mtool.toolslib.base.app

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.media.AudioManager
import android.net.ConnectivityManager
import android.os.Build
import android.support.multidex.MultiDexApplication
import android.telephony.TelephonyManager

import kotlin.properties.Delegates
import com.mtool.toolslib.base.view.activity.ActivityControlManager
import android.content.Intent


/***
 * 重写APP LifeCycle控制
 */
open class AppBase : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        context = this
        registerActivityLifecycleCallbacks(ActivityControlManager.callback)

    }

    override fun onTerminate() {
        super.onTerminate()
        ActivityControlManager.finishAllActivity()
        ActivityControlManager.onTerminate()
    }

    fun killingOwnBackGround(packName: String) {
        try {
            val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

            val startMain = Intent(Intent.ACTION_MAIN)
            startMain.addCategory(Intent.CATEGORY_HOME)
            startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            this.startActivity(startMain)

            am.killBackgroundProcesses(packName)
            am.killBackgroundProcesses(packName + ":channel")
            android.os.Process.killProcess(android.os.Process.myPid())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    companion object {
        var context: Context by Delegates.notNull()

        fun connectivityManager() = ActivityControlManager.topContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        fun audioManager() = ActivityControlManager.topContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //        fun wifiManager() = ActivityControlManager.topContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
        fun telephonyManager() = ActivityControlManager.topContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        fun packageManager() = ActivityControlManager.topContext().packageManager

        fun currentVersion() = packageManager().getPackageInfo(ActivityControlManager.topContext()?.packageName, 0).let { Pair(it.versionCode, it.versionName) }
    }
}
