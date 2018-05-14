package com.mtool.toolslib.base.view.activity

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import java.lang.Exception

//import com.umeng.analytics.MobclickAgent

/**
 * Created by gerry on 2017/4/29.
 */

abstract class BaseActivity : AppCompatActivity() {

    val mAct: BaseActivity get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            initView()
            initData()

        } catch (e: Exception) {
            e.printStackTrace()
            finish()
        }
    }

    open protected fun setDefaultOrientation() {
    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onPause() {
        super.onPause()
    }

    protected abstract fun initData()

    protected abstract fun initView()


}
