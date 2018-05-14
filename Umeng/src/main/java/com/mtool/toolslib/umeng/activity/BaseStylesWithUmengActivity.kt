package com.mtool.toolslib.network.activity.activity

import android.os.Bundle
import com.lzy.okgo.OkGo
import com.mtool.toolslib.base.view.activity.ToolBarActivity
import com.mtool.toolslib.network.activity.netkit.delegate.RequestDelegateController
import com.umeng.analytics.MobclickAgent

/**
 * 添加分页列表分页请求的activity
 *
 *
 * Created by buck on 2017/10/1.
 */
abstract class BaseStylesWithUmengActivity : BaseStylesActivity() {
    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}