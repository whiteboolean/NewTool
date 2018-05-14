package com.mtool.toolslib.model.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by admin on 2017/12/21.
 */

class AlarmReceive : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        //循环启动Service
        val i = Intent(context, AlarmService::class.java)
        context.startService(i)
    }
}
