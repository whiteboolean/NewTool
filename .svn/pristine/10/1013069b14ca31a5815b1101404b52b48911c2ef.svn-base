package com.mtool.toolslib.umeng

import android.app.Notification
import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import com.mtool.toolslib.app.AppTools
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.*
import com.umeng.message.entity.UMessage

open class AppUmeng : AppTools() {
    override fun onCreate() {
        super.onCreate()
        context = this


        //统一
        //APP message key
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "858098d532f9aacfa622d9b50da4c177")
        UMConfigure.init(this,"5a375ae4a40fa37f3c00050c","com.wanhaohui.www", UMConfigure.DEVICE_TYPE_PHONE, "858098d532f9aacfa622d9b50da4c177")


        //分析
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
        //推送
        initUmengPush()

        MobclickAgent.openActivityDurationTrack(false)
    }

    private fun initUmengPush() {
        //推送
        val mPushAgent = PushAgent.getInstance(this)
        val messageHandler = object : UmengMessageHandler() {
            //推送消息处理
            override fun getNotification(context: Context?, msg: UMessage?): Notification {
                when (msg!!.builder_id) {
                    1 -> {
                        val builder = Notification.Builder(context)
                        val myNotificationView = RemoteViews(context!!.packageName,
                                R.layout.umeng_push_message_layer)
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title)
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text)
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
                                getLargeIcon(context, msg))
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg))
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true)

                        return builder.notification
                    }
                    else ->
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg)
                }
            }

            //自定义消息处理
            override fun dealWithCustomMessage(context: Context?, msg: UMessage?) {
                Handler(mainLooper).post {
                    // 对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
                    val isClickOrDismissed = true
                    if (isClickOrDismissed) {
                        //自定义消息的点击统计
                        UTrack.getInstance(applicationContext).trackMsgClick(msg)
                    } else {
                        //自定义消息的忽略统计
                        UTrack.getInstance(applicationContext).trackMsgDismissed(msg!!)
                    }
                    //处理返回的消息
                    Toast.makeText(context, msg!!.custom, Toast.LENGTH_LONG).show()
                }
            }
        }
        mPushAgent.messageHandler = messageHandler

        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(s: String) {
                Log.e("友盟推送 注册成功", s)
            }

            override fun onFailure(s: String, s1: String) {
                Log.e("友盟推送 注册失败", s + s1)
            }
        })
    }

    fun onUMEvent(context: Context, id: String, m: HashMap<String, String>, value: Int) {
        m.put("__ct__", value.toString())
        MobclickAgent.onEvent(context, id, m)
        //MobclickAgent.onEventValue(context, id, m, value);
    }

    fun analysisSignIn(){
    }

}