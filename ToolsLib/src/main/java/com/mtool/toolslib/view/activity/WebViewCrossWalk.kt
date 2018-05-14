package com.mtool.toolslib.view.activity


import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.WebResourceResponse
import android.webkit.WebSettings

import com.afollestad.materialdialogs.MaterialDialog

import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

//import org.xwalk.core.XWalkActivity
//import org.xwalk.core.XWalkJavascriptResult
//import org.xwalk.core.XWalkNavigationHistory
//import org.xwalk.core.XWalkPreferences
//import org.xwalk.core.XWalkResourceClient
//import org.xwalk.core.XWalkUIClient
//import org.xwalk.core.XWalkView
//
//
///**
// * Created by admin on 2017/10/17.
// */
//
//class WebViewCrossWalk : XWalkActivity() {
//
//
//    companion object {
//        val ACTION_HTML = "com.whh.www.html"
//        val INTENT_URL = "url"
//        val INTENT_TITLE = "title"
//        val INTENT_SUB_TITLE = "subTitle"
//        val INTENT_ID = "id"
//
//        fun clearCookies(context: Context) {
//            val cookieSyncMngr = CookieSyncManager.createInstance(context)
//            val cookieManager = CookieManager.getInstance()
//            cookieManager.removeAllCookie()
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
//        setContentView(R.layout.webview_crosswalk)
//        //Don't initXWalkView here!
//    }
//
//    override fun onXWalkReady() {
//        //initXWalkView in onXWalkReady()
//        val title = intent.getStringExtra(INTENT_TITLE)
//        val subTitle = intent.getStringExtra(INTENT_SUB_TITLE)
//        val url = intent.getStringExtra(INTENT_URL)
//
//
//
//        initStatusBar()
//        init()
//        setWebViewClient()
//        loadingUrl(url)
//
//    }
//
//
//
//    private fun init() {
//        webView.requestFocusFromTouch()
//        webView.isHorizontalScrollBarEnabled = false
//        webView.isVerticalScrollBarEnabled = false
//        webView.scrollBarStyle = XWalkView.SCROLLBARS_OUTSIDE_INSET
//        webView.isScrollbarFadingEnabled = true
//        webView.isDrawingCacheEnabled = false//不使用缓存
//        webView.navigationHistory.clear()//清除历史记录
//        webView.isVerticalFadingEdgeEnabled = false
//
//        webView.clearCache(true)//清楚包括磁盘缓存
//
//
//        val settings = webView.settings
//        settings.useWideViewPort = true
//        //缩放开关，仅仅支持双击缩放
//        settings.setSupportZoom(true)
//        //设置是否可缩放，会出现缩放工具
//        settings.builtInZoomControls = true
//        settings.setSupportZoom(true)
//
//        XWalkPreferences.setValue("enable-javascript", true)
//        /***
//         * 如果不加上影片无法正常播放
//         * 这TMD 一定要加
//         */
////        settings.setPluginState(WebSettings.PluginState.ON_DEMAND)
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
////        }
//
//    }
//
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            hideSystemNavigationBar()
//        } else if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            // 加入竖屏要处理的代码
//            showSystemNavigationBar()
//            initStatusBar()
//        }
//    }
//
//    private fun showSystemNavigationBar() {
//        val decorView = window.decorView
//        val uiOptions = View.VISIBLE
//        decorView.systemUiVisibility = uiOptions
//    }
//
//    private fun hideSystemNavigationBar() {
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_IMMERSIVE
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
//    }
//
//
//    private fun setWebViewClient() {
//        webView.setResourceClient(object : XWalkResourceClient(webView) {
//            override fun onReceivedLoadError(view: XWalkView, errorCode: Int, description: String, failingUrl: String) {
//                super.onReceivedLoadError(view, errorCode, description, failingUrl)
//
//                val builder = MaterialDialog.Builder(this@WebViewCrossWalk)
//                builder.title("SSL协议发生问题，请确认您是否继续操作？")
//                builder.cancelable(false)
//                        .autoDismiss(false)
//                builder.content("继续使用")
//                builder.negativeText("稍后")
//                        .negativeColor(resources.getColor(R.color.text_hint))
//                        .onPositive { dialog, which ->
//                            //                                handler.proceed();
//                            dialog.dismiss()
//                        }
//                        .onNegative { dialog, which ->
//                            //                                handler.cancel();
//                            dialog.dismiss()
//                        }
//            }
//
////            override fun shouldInterceptLoadRequest(view: XWalkView, url: String): WebResourceResponse {
////                return super.shouldInterceptLoadRequest(view, url)
////            }
//
//            override fun shouldOverrideUrlLoading(view: XWalkView, url: String?): Boolean {
//                if (url == null) return false
//                try {
//                    if (url.startsWith("weixin://") //微信
//
//                            || url.startsWith("alipays://") //支付宝
//
//                            || url.startsWith("mailto://") //邮件
//
//                            || url.startsWith("tel://")//电话
//
//                            || url.startsWith("dianping://")//大众点评
//
//                            || url.startsWith("mqqapi://")//QQ钱包
//                            ) {
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                        startActivity(intent)
//                        Log.e("cww", "url1" + url)
//                        return true
//                    }//其他自定义的scheme
//                } catch (e: Exception) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
//                    return true//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
//                }
//
//                //处理http和https开头的url
//                Log.e("cww", "url3" + url)
//                if (url.contains("index.php/login")||url.contains("login/index")) {
//                    startActivity<LoginActivity>()
//                    finish()
//                } else {
//                    //                    UmengAnalysisGaming();
//                }
//                return super.shouldOverrideUrlLoading(view, url)
//            }
//
//            override fun onLoadFinished(view: XWalkView, url: String) {
//                super.onLoadFinished(view, url)
//            }
//
//            override fun onLoadStarted(view: XWalkView, url: String) {
//                super.onLoadStarted(view, url)
//            }
//        })
//
//        webView.setUIClient(object : XWalkUIClient(webView) {
//            override fun onJsAlert(view: XWalkView, url: String, message: String, result: XWalkJavascriptResult): Boolean {
//                return super.onJsAlert(view, url, message, result)
//            }
//
//            override fun onPageLoadStarted(view: XWalkView, url: String) {}
//
//            override fun onPageLoadStopped(view: XWalkView, url: String, status: XWalkUIClient.LoadStatus) {}
//
//            override fun onScaleChanged(view: XWalkView, oldScale: Float, newScale: Float) {
//                view?.invalidate()
//                super.onScaleChanged(view, oldScale, newScale)
//            }
//        })
//    }
//
//
//    protected fun initStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val window = window
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.statusBarColor = Color.TRANSPARENT
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        }
//    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (webView.navigationHistory.canGoBack()) {
//                webView.navigationHistory.navigate(XWalkNavigationHistory.Direction.BACKWARD, 1)//返回上一页面
//            } else {
//                finish()
//            }
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//
//
//
//    private fun loadingUrl(url: String?) {
//        if (url!=null && url.isNotEmpty()) {
//            webView.load(url!!, null)
//        } else {
//            toast( "暂未开放")
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        try {
//            MobclickAgent.onPageEnd("WebActivity")
//            if (webView != null) {
//                webView.pauseTimers()
//                webView.onHide()
//            }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        try {
//            MobclickAgent.onPageStart("WebActivity")
//            if (webView != null) {
//                logE("ERROR ==> "+"onResume")
//                webView.resumeTimers()
//                webView.onShow()
//            }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        try {
//            clearCookies(this)
//            if (webView != null) {
//                webView.navigationHistory.clear()
//                webView.removeAllViews()
//                webView.onDestroy()
//            }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        try {
//            if (webView != null) {
//                webView.onActivityResult(requestCode, resultCode, data)
//            }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//
//    }
//
//    override fun onNewIntent(intent: Intent) {
//        try {
//            if (webView != null) {
//                webView.onNewIntent(intent)
//            }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//
//    }
//}
