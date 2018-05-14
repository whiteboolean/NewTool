package com.mtool.toolslib.network.activity.activity

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.lzy.okgo.OkGo
import com.mtool.toolslib.base.view.activity.ToolBarActivity
import com.mtool.toolslib.network.activity.netkit.delegate.RequestDelegateController
import io.reactivex.disposables.CompositeDisposable

/**
 * 添加分页列表分页请求的activity
 *
 *
 * Created by buck on 2017/10/1.
 */
abstract class BaseStylesActivity : ToolBarActivity() {
    val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar()
        setSupportBack()
        setFullScreenMode(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        requestDelegatesData()
    }


    override fun initData() {}

    override fun onDestroy() {
        super.onDestroy()
        OkGo.getInstance().cancelTag(this)
        disposable.clear()
    }

    override fun onStop() {
        super.onStop()
    }

    val requestDelegates = mutableListOf<RequestDelegateController>()
    private fun requestDelegatesData() {
        for (x in requestDelegates) {
            x.requestData()
        }
    }


    private fun initStatusBar() {
        val window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
//            window.navigationBarColor = Color.GRAY
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
//    gitTest
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


//        val lp = llMainTop.getLayoutParams() as LinearLayout.LayoutParams
//        lp.setMargins(0, StatusBarHightUtil.getStatusBarHeight(), 0, 0)
//        llMainTop.setLayoutParams(lp)
    }

    fun useRequestDelegate() = RequestDelegateController().also { requestDelegates.add(it) }
}