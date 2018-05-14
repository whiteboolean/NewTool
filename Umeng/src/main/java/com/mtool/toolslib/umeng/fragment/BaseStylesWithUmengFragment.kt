package com.mtool.toolslib.network.activity.fragment

import android.os.Bundle
import android.view.View
import com.lzy.okgo.OkGo
import com.mtool.toolslib.base.view.fragment.ToolBarFragment
import com.mtool.toolslib.network.activity.netkit.delegate.RequestDelegateController
import com.umeng.analytics.MobclickAgent

/**
 * Created by buck on 2017/10/15.
 */

abstract class BaseStylesWithUmengFragment : BaseStylesFragment() {
    override fun onResume() {
        super.onResume()
//        MobclickAgent.onPageStart("SplashScreen");
    }

    override fun onPause() {
        super.onPause()
//        MobclickAgent.onPageEnd("SplashScreen")
    }
}