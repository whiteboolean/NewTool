package com.mtool.toolslib.view.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.mtool.toolslib.base.R

import java.util.HashMap

/**
 * Created by JayCruz on 2017/9/21.
 */

class View_WebView : WebView {
    internal var context: Context
    private var pb: ProgressBar? = null
    private var errorPage: View? = null
    private var goToLogin: () -> Unit = {
        Log.e("View_WebView", "尚未设置 LOGIN CALL BACK")
    }
    /***
     * 取得当前progress
     * @return
     */
    var currentProgress: Int = 0
        private set

    constructor(context: Context) : super(context) {
        init()
        this.context = context

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
        this.context = context

    }

    /***
     * 设定如果TOKEN 失效后需要做的事情
     * @param errorPage
     */
    fun setLoginCallBack(goToLogin: () -> Unit) {
        this.goToLogin = goToLogin
    }

    /***
     * 设定Activity ErrorPage
     * @param errorPage
     */
    fun setErrorPage(errorPage: View) {
        this.errorPage = errorPage
    }

    /***
     * 设定Activity ProgressBar
     * @param progressBar
     */
    fun setProgressBar(progressBar: ProgressBar) {
        this.pb = progressBar

    }

    /***
     * 读取 新URL
     * @param url
     */
    override fun loadUrl(url: String) {
        super.loadUrl(url)
        currentProgress = 0
    }

    /***
     * Activity完成时 清除所有记录
     */
    fun finish() {
        try {
            this.removeAllViews()
            this.tag = null
            this.clearHistory()
            this.destroy()
            currentProgress = 0
            (context as Activity).finish()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /***
     * 当Activity回退 或 结束时呼叫
     * 如果是游戏类，则 双击退出
     * 如果是一般类，则 单击退出
     */
    fun onBackPressed(isGameMode: Boolean = false) {
        val exit = try {
            val canGoBack = this.canGoBack()
//            this.
            val currentIndex = this.copyBackForwardList().currentIndex
            Log.e("onBackPressed", "canGoBack=${if (canGoBack) "T" else "F"}")
            Log.e("onBackPressed", "currentIndex=${currentIndex}")
            val exit =
                    if (this.canGoBack()) {
                        /***
                         * 如果非第一层，直接回退到 第一层即可
                         */
//                        this.goBackOrForward(-currentIndex)
                        this.goBack()
                        false
                    } else {
                        true
                    }
            exit
        } catch (ex: Exception) {
            true
        }
        if (exit) {
            if (isGameMode) {
                pressTwoTimeExist()
            } else {
                finish()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled", "NewApi")
    private fun init() {
        requestFocusFromTouch()
//        setDownloadListener(MyDownloadListener)
        val cookieManager = CookieManager.getInstance()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.setAcceptFileSchemeCookies(true)
            cookieManager.setAcceptThirdPartyCookies(this, true)
        }
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.javaScriptEnabled = true
        settings.blockNetworkImage = false
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.allowFileAccess = true
        //缩放开关，仅仅支持双击缩放
        settings.setSupportZoom(true)
        //设置是否可缩放，会出现缩放工具
        settings.builtInZoomControls = true
        //隐藏缩放工具
        settings.displayZoomControls = false
        //WebView.setWebContentsDebuggingEnabled(true);
        /***
         * 如果不加上影片无法正常播放
         * 这TMD 一定要加
         */
        settings.pluginState = WebSettings.PluginState.ON
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }


        webViewClient = setWebViewClient()        //WebView.setWebContentsDebuggingEnabled(true);
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                currentProgress = progress
                if (pb != null) {
                    pb!!.visibility = View.VISIBLE
                    pb!!.progress = currentProgress
                    if (currentProgress == 100)
                        pb!!.visibility = View.GONE
                }
            }

            override fun onJsAlert(view: WebView, url: String, message: String,
                                   result: JsResult): Boolean {
                //加这段可以证webview中的alert弹出来
                return super.onJsAlert(view, url, message, result)
            }
        }

    }

    private fun setWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (errorPage != null)
                    errorPage!!.visibility = View.VISIBLE
            }

            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                super.onReceivedHttpError(view, request, errorResponse)
//                if (errorPage != null)
//                    errorPage!!.visibility = View.VISIBLE
            }

            override fun shouldOverrideUrlLoading(wv: WebView, url: String?): Boolean {
                if (url == null) return false
                try {
                    if (url.startsWith("weixin://") //微信
                            || url.startsWith("alipays://") //支付宝
                            || url.startsWith("mailto://") //邮件
                            || url.startsWith("tel://")//电话
                            || url.startsWith("dianping://")//大众点评
                            || url.startsWith("mqqapi://")//QQ钱包
                    ) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                        Log.e("cww", "url1" + url)
                        return true
                    }//其他自定义的scheme
                } catch (e: Exception) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                //处理http和https开头的url
                Log.e("cww", "url3" + url)
                if (url.contains("index.php/login") || url.contains("m/login.php") || url.contains("login/index")) {
                    goToLogin.invoke()
                } else {
//                    view.loadUrl(url)
                }
                return false
            }

            /**
             * 如果不这样做 可能导致 中间网站进行用户资讯窃取
             * @param view
             * @param handler
             * @param error
             */
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                val builder = MaterialDialog.Builder(context)
                builder.title("SSL协议发生问题，请确认您是否继续操作？")
                builder.cancelable(false)
                        .autoDismiss(false)
                builder.content("继续使用")
                builder.negativeText("稍后")
                        .onPositive { dialog, which ->
                            handler.proceed()
                            dialog.dismiss()
                        }
                        .onNegative { dialog, which ->
                            handler.cancel()
                            dialog.dismiss()
                        }
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                view.settings.blockNetworkImage = false
                Log.e("view_web", "onPageFinished=${url}")
//                val currentIndex = view.copyBackForwardList().currentIndex
//                val b = index.containsKey(currentIndex)
//                if (b) {
//                    val i = index.get(currentIndex) + 1
//                    index.put(currentIndex, i)
//                } else {
//                    index.put(currentIndex, 1)
//                }
            }
        }
    }

    private var lastBackTime: Long = 0
    private var lastShowTime: Long = 0

    /***
     * 双击回退
     */
    fun pressTwoTimeExist() {
        val current = System.currentTimeMillis()
        if (current - lastBackTime > 1000) {
            if (current - lastShowTime > 2000) {
                Toast.makeText(context, "双点击回退键退出应用程序", Toast.LENGTH_SHORT).show()
            }
            lastShowTime = current
        } else {
            lastShowTime = 0
            lastBackTime = 0
            finish()
        }
        lastBackTime = current
    }


//    private inner class MyDownloadListener : DownloadListener {
//
//        override fun onDownloadStart(url: String, userAgent: String, contentDisposition: String, mimetype: String, contentLength: Long) {
//            val uri = Uri.parse(url)
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            context.startActivity(intent)
//        }
//    }

}
