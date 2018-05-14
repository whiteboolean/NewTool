package com.mtool.toolslib.model.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

/***
 * 背景程序作业
 * 目前任务状态如下
 */
class AlarmService : Service() {

    /**
     * 调用Service都会执行到该方法
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        /***
         * 每次判断是否为最快的ip位置
         */

        Log.e("cww", "背景程序开始作业：")

        doSomething()

        //通过AlarmManager定时启动广播
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val triggerAtTime = SystemClock.elapsedRealtime() + ONE_Minute//从开机到现在的毫秒书（手机睡眠(sleep)的时间也包括在内
        val i = Intent(this, AlarmReceive::class.java)
        val pIntent = PendingIntent.getBroadcast(this, PENDING_REQUEST, i, PENDING_REQUEST)
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pIntent)


        Log.e("cww", "背景程序结束作业：")
        return super.onStartCommand(intent, flags, startId)
    }

    fun doSomething() {

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {

        /**
         * 每5分钟更新一次数据
         */
        //    private static final int ONE_Minute = 10 * 1000;
        private val ONE_Minute = 5 * 60 * 1000
        private val PENDING_REQUEST = 0
    }
}

